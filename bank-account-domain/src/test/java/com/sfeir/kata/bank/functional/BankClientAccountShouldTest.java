package com.sfeir.kata.bank.functional;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.simple.account.Account;
import com.sfeir.kata.bank.domain.simple.account.operation.specification.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.simple.account.statement.AccountStatementLine;
import com.sfeir.kata.bank.domain.simple.printer.AccountStatementPrinterSpecification;
import com.sfeir.kata.bank.functional.deposit.DepositTestDefinition;
import com.sfeir.kata.bank.functional.deposit.DepositTestSource;
import com.sfeir.kata.bank.functional.printing.PrintingOperationsTestDefinition;
import com.sfeir.kata.bank.functional.withdrawal.WithdrawalTestDefinition;
import com.sfeir.kata.bank.functional.withdrawal.WithdrawalTestSource;

@ExtendWith(MockitoExtension.class)
@TestMethodOrder(OrderAnnotation.class)
class BankClientAccountShouldTest {

		private Account account;

		@BeforeEach
		public void init() {

				account = new Account();
		}

		@Nested
		@DisplayName("Testing deposit functionality")
		public class DepositShould implements DepositTestSource,
		    DepositTestDefinition {

				@Override
				@ParameterizedTest
				@MethodSource("generatePositiveAmount")
				public void make_a_deposit(Money amount)
				    throws IllegalAccessException {

						// GIVEN input amount

						// WHEN
						account.deposit(amount);

						// THEN

						Condition<Account> accountWithSavedOperation = new Condition<>((account) -> account.getStatement()
						                                                                                   .getLines()
						                                                                                   .isEmpty(), "checking if account has not empty history", amount);

						Condition<AccountStatementLine> operationWithCorrectAmount = new Condition<>((operation) -> operation.getAmount()
						                                                                                                     .equals(amount.toString()), "checking if saved operation has the correct amount");

						org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(account)
						                                                           .is(accountWithSavedOperation),
						                                           () -> Assertions.assertThat(account.getStatement()
						                                                                              .getLines())
						                                                           .have(operationWithCorrectAmount),
						                                           () -> Assertions.assertThat(account.getBalance())
						                                                           .isEqualTo(amount));

				}
		}

		@Nested
		@DisplayName("Testing withdrawal functionality")
		@TestInstance(Lifecycle.PER_CLASS)
		public class WithdrawalShould implements
		    WithdrawalTestSource, WithdrawalTestDefinition {

				@BeforeEach
				public void init() throws IllegalAccessException {

						var initMoney = Money.of("500");

						account.deposit(initMoney);

						Assumptions.assumeThat(account.getBalance())
						           .isEqualTo(initMoney);
				}

				@Override
				@ParameterizedTest
				@MethodSource("givenAuthorizedAmount")
				public void
				    make_a_withdrawal_with_success(Money amount)
				        throws IllegalAccessException {

						// GIVEN an earlier deposit of 500 and input amount
						var initMoney = Money.of("500");

						var expectedBalance = initMoney.putMoney()
						                               .apply(amount.toNegative()
						                                            .apply());

						// WHEN
						account.withdraw(amount);

						// THEN

						Condition<Account> accountWithSavedOperation = new Condition<>((account) -> !account.getStatement()
						                                                                                    .getLines()
						                                                                                    .isEmpty(), "checking if account has not empty history", amount);

						Condition<AccountStatementLine> operationWithCorrectAmount = new Condition<>((operation) -> operation.getAmount()
						                                                                                                     .equals(amount.toNegative()
						                                                                                                                   .apply()
						                                                                                                                   .toString()), "checking if saved operation has the correct amount");

						Condition<AccountStatementLine> operationWithCorrectBalanceResult = new Condition<>((operation) -> operation.getBalance()
						                                                                                                            .equals(expectedBalance), "checking if saved operation has the correct balance result");

						org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(account)
						                                                           .is(accountWithSavedOperation),
						                                           () -> Assertions.assertThat(account.getStatement()
						                                                                              .getLines())
						                                                           .have(operationWithCorrectAmount),
						                                           () -> Assertions.assertThat(account.getStatement()
						                                                                              .getLines())
						                                                           .have(operationWithCorrectBalanceResult),
						                                           () -> Assertions.assertThat(account.getBalance())
						                                                           .isEqualTo(expectedBalance));

				}

				@Override
				@ParameterizedTest
				@MethodSource("givenUnauthorizedAmount")
				public void
				    make_an_unauthorized_withdrawal(Money amount) {

						// GIVEN input amount

						// WHEN
						Executable result = () -> account.withdraw(amount);
						// THEN

						org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
						                                              () -> result.execute());

				}
		}

		@Nested
		@DisplayName("Testing printing operations fuunctionality")
		@TestInstance(Lifecycle.PER_CLASS)
		public class PrintOperationsShould
		    implements PrintingOperationsTestDefinition {

				private ByteArrayOutputStream        output;
				AccountStatementPrinterSpecification localPrinter;

				@BeforeEach
				void setUp() {

						output       = new ByteArrayOutputStream();
						localPrinter = new PrintStream(output)::print;

				}

				@Override
				@Test
				public void print_statement_of_empty_history() {

						// GIVEN
						var expectedStatement = new Account().getStatement();

						// WHEN
						localPrinter.print(expectedStatement);

						// THEN
						Assertions.assertThat(output)
						          .hasToString(expectedStatement.toString());

				}

				@Override
				@Test
				public void print_non_empty_statement()
				    throws IllegalAccessException {

						// GIVEN
						var account = new Account();
						account.deposit(Money.of("10000"));

						var operation = account.getStatement()
						                       .getLines()
						                       .getFirst();

						var expectedStatement = AccountStatementLine.builder()
						                                            .amount("10000")
						                                            .balance("10000")
						                                            .date(operation.getDate())
						                                            .type(operation.getType())
						                                            .build();

						// WHEN
						localPrinter.print(account.getStatement());

						// THEN
						Assertions.assertThat(output)
						          .hasToString(expectedStatement.toString());
				}

		}

}
