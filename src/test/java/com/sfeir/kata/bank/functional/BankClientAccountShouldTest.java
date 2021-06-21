package com.sfeir.kata.bank.functional;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.client.IClientOperator;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.validation.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;
import com.sfeir.kata.bank.functional.deposit.DepositTestDefinition;
import com.sfeir.kata.bank.functional.deposit.DepositTestSource;
import com.sfeir.kata.bank.functional.printing.PrintingOperationsTest;
import com.sfeir.kata.bank.functional.printing.PrintingOperationsTestDefinition;
import com.sfeir.kata.bank.functional.withdrawal.WithdrawalTestDefinition;
import com.sfeir.kata.bank.functional.withdrawal.WithdrawalTestSource;
import com.sfeir.kata.bank.infra.printer.ConsolePrinter;
import com.sfeir.kata.bank.utils.BankClientMockFactory;

import io.vavr.Function0;

@RunWith(JUnitPlatform.class)
@TestMethodOrder(OrderAnnotation.class)
class BankClientAccountShouldTest {

		private IClientOperator client;

		@BeforeEach
		public void init() {

				client = BankClientMockFactory.create();
		}

		@Nested
		@DisplayName("Testing deposit functionality")
		public class DepositShould implements DepositTestSource,
		    DepositTestDefinition {

				@Override
				@ParameterizedTest
				@MethodSource("generatePositiveAmount")
				public void make_a_deposit(Money amount) {

						// GIVEN input amount

						// WHEN
						final var result = client.deposit(amount);

						// THEN

						Condition<IAccountOperator> accountWithSavedOperation = new Condition<>((account) -> !account.getHistory()
						                                                                                             .isEmpty(), "checking if account has not empty history", amount);

						Condition<Operation> operationWithCorrectAmount = new Condition<>((operation) -> operation.getAmount()
						                                                                                          .equals(amount), "checking if saved operation has the correct amount");

						org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(result)
						                                                           .isTrue(),
						                                           () -> Assertions.assertThat(client.getAccount())
						                                                           .is(accountWithSavedOperation),
						                                           () -> Assertions.assertThat(client.getAccount()
						                                                                             .getHistory()
						                                                                             .getOperations())
						                                                           .has(operationWithCorrectAmount,
						                                                                Index.atIndex(0)),
						                                           () -> Assertions.assertThat(client.getAccount()
						                                                                             .getBalance())
						                                                           .isEqualTo(amount));

				}
		}

		@Nested
		@DisplayName("Testing withdrawal functionality")
		@TestInstance(Lifecycle.PER_CLASS)
		public class WithdrawalShould implements
		    WithdrawalTestSource, WithdrawalTestDefinition {

				@BeforeEach
				public void init() {

						var initMoney = Money.of(BigDecimal.valueOf(500));

						client.deposit(initMoney);

						Assumptions.assumeThat(client.getAccount()
						                             .getBalance())
						           .isEqualTo(initMoney);
				}

				@Override
				@ParameterizedTest
				@MethodSource("givenAuthorizedAmount")
				public void
				    make_a_withdrawal_with_success(Money amount) {

						// GIVEN an earlier deposit of 500 and input amount
						var initMoney = Money.of(BigDecimal.valueOf(500));

						var expectedBalance = initMoney.add()
						                               .apply(amount.toNegative()
						                                            .apply());

						// WHEN
						final var result = client.withdraw(amount);

						// THEN

						Condition<IAccountOperator> accountWithSavedOperation = new Condition<>((account) -> !account.getHistory()
						                                                                                             .isEmpty(), "checking if account has not empty history", amount);

						Condition<Operation> operationWithCorrectAmount = new Condition<>((operation) -> operation.getAmount()
						                                                                                          .equals(amount.toNegative()
						                                                                                                        .apply()), "checking if saved operation has the correct amount");

						Condition<Operation> operationWithCorrectBalanceResult = new Condition<>((operation) -> operation.getBalanceResult()
						                                                                                                 .equals(expectedBalance), "checking if saved operation has the correct balance result");

						org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(result)
						                                                           .isTrue(),
						                                           () -> Assertions.assertThat(client.getAccount())
						                                                           .is(accountWithSavedOperation),
						                                           () -> Assertions.assertThat(client.getAccount()
						                                                                             .getHistory()
						                                                                             .getOperations())
						                                                           .has(operationWithCorrectAmount,
						                                                                Index.atIndex(1)),
						                                           () -> Assertions.assertThat(client.getAccount()
						                                                                             .getHistory()
						                                                                             .getOperations())
						                                                           .has(operationWithCorrectBalanceResult,
						                                                                Index.atIndex(1)),
						                                           () -> Assertions.assertThat(client.getAccount()
						                                                                             .getBalance())
						                                                           .isEqualTo(expectedBalance));

				}

				@Override
				@ParameterizedTest
				@MethodSource("givenUnauthorizedAmount")
				public void
				    make_an_unauthorized_withdrawal(Money amount) {

						// GIVEN input amount

						// WHEN
						Function0<Boolean> withdrawal = () -> client.withdraw(amount);

						// THEN

						org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
						                                              () -> withdrawal.apply());

				}
		}

		@Nested
		@DisplayName("Testing printing operations fuunctionality")
		@TestInstance(Lifecycle.PER_CLASS)
		public class PrintOperationsShould
		    implements PrintingOperationsTestDefinition,
		    PrintingOperationsTest {

				final ByteArrayOutputStream outputContent  = new ByteArrayOutputStream();
				final IStatementPrinter     printer        = new ConsolePrinter(new PrintStream(outputContent));
				final IClientOperator       internalClient = BankClientMockFactory.create(printer);

				@AfterEach
				public void tearDown() {
						outputContent.reset();
				}

				@Override
				@ParameterizedTest
				@MethodSource
				public void
				    print_statement_of_empty_history(String expectedValue) {

						// GIVEN
						// WHEN
						internalClient.printOperationHistory();

						// THEN
						this.validateThatOutputIs(expectedValue.concat(System.lineSeparator()));

				}

				@Override
				@ParameterizedTest
				@MethodSource
				public void
				    print_non_empty_statement(String expectedFormat) {

						// GIVEN
						internalClient.deposit(Money.of(BigDecimal.valueOf(10000)));

						var operation = internalClient.getAccount()
						                              .getHistory()
						                              .getOperations()
						                              .get(0);

						var formattedDate = operation.getDate()
						                             .toString();

						var expectedResult = String.format(expectedFormat,
						                                   formattedDate,
						                                   "DEPOSIT",
						                                   "10000",
						                                   "10000");

						// WHEN
						internalClient.printOperationHistory();

						// THEN
						this.validateThatOutputIs(expectedResult);
				}

				private void
				    validateThatOutputIs(String expectedResult) {
						Assertions.assertThat(outputContent)
						          .hasToString(expectedResult);
				}

		}

}
