package com.sfeir.kata.bank.functional;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.operation.utils.DateFormatter;
import com.sfeir.kata.bank.domain.statement.ConsolePrinter;
import com.sfeir.kata.bank.domain.statement.StatementPrinter;
import com.sfeir.kata.bank.functional.deposit.DepositFunctionalCase;
import com.sfeir.kata.bank.functional.deposit.DepositTestDefinition;
import com.sfeir.kata.bank.functional.printing.PrintingOperationsFunctionalCase;
import com.sfeir.kata.bank.functional.printing.PrintingOperationsFunctionalTest;
import com.sfeir.kata.bank.functional.withdrawal.WithdrawalFunctionalCase;
import com.sfeir.kata.bank.functional.withdrawal.WithdrawalFunctionalTest;
import com.sfeir.kata.bank.utils.BankClientMockFactory;

import io.vavr.Function0;

@RunWith(JUnitPlatform.class)
@TestMethodOrder(OrderAnnotation.class)
class BankClientAccountShould {

	private ClientOperation client;

	@BeforeEach
	public void init() {

		client = BankClientMockFactory.create();
	}

	@Nested
	public class DepositShould implements DepositFunctionalCase, DepositTestDefinition {

		@Override
		@ParameterizedTest
		@MethodSource("generatePositiveAmount")
		public void make_a_deposit(Money amount) {

			// GIVEN input amount

			// WHEN
			final var result = client.deposit(amount);

			// THEN

			Condition<Account> accountWithSavedOperation = new Condition<>((account) -> !account.getHistory().isEmpty(),
					"checking if account has not empty history", amount);

			Condition<Operation> operationWithCorrectAmount = new Condition<>(
					(operation) -> operation.getAmount().equals(amount),
					"checking if saved operation has the correct amount");

			org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(result).isTrue(),
					() -> Assertions.assertThat(client.getAccount()).is(accountWithSavedOperation),
					() -> Assertions.assertThat(client.getAccount().getHistory().getOperations())
							.has(operationWithCorrectAmount, Index.atIndex(0)),
					() -> Assertions.assertThat(client.getAccount().getBalance()).isEqualTo(amount));

		}
	}

	@Nested
	public class WithdrawalShould implements WithdrawalFunctionalCase, WithdrawalFunctionalTest {

		@Override
		@ParameterizedTest
		@MethodSource("givenAuthorizedAmount")
		public void make_a_withdrawal_with_success(Money amount) {

			// GIVEN an earlier deposit of 1000 and input amount
			client.deposit(Money.of(BigDecimal.valueOf(500)));

			var expectedBalance = client.getAccount().getBalance().add(amount.toNegative());

			// WHEN
			final var result = client.withdrawal(amount);

			// THEN

			Condition<Account> accountWithSavedOperation = new Condition<>((account) -> !account.getHistory().isEmpty(),
					"checking if account has not empty history", amount);

			Condition<Operation> operationWithCorrectAmount = new Condition<>(
					(operation) -> operation.getAmount().equals(amount.toNegative()),
					"checking if saved operation has the correct amount");

			org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(result).isTrue(),
					() -> Assertions.assertThat(client.getAccount()).is(accountWithSavedOperation),
					() -> Assertions.assertThat(client.getAccount().getHistory().getOperations())
							.has(operationWithCorrectAmount, Index.atIndex(1)),
					() -> Assertions.assertThat(client.getAccount().getBalance()).isEqualTo(expectedBalance));

		}

		@Override
		@ParameterizedTest
		@MethodSource("givenUnauthorizedAmount")
		public void make_an_unauthorized_withdrawal(Money amount) {

			// GIVEN input amount

			// WHEN
			Function0<Boolean> withdrawal = () -> client.withdrawal(amount);

			// THEN

			org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
					() -> withdrawal.apply());

		}
	}

	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	public class PrintOperationsShould implements PrintingOperationsFunctionalTest, PrintingOperationsFunctionalCase {

		final ByteArrayOutputStream outputContent = new ByteArrayOutputStream();
		final StatementPrinter printer = new ConsolePrinter(new PrintStream(outputContent));
		final ClientOperation internalClient = BankClientMockFactory.create(printer);

		@Override
		@ParameterizedTest
		@MethodSource
		@Order(1)
		public void print_statement_of_empty_history(String expectedValue) {

			// GIVEN
			// WHEN
			internalClient.printOperationHistory();

			// THEN
			this.validate(expectedValue.concat(System.lineSeparator()));
			
			outputContent.reset();

		}

		@Override
		@ParameterizedTest
		@MethodSource
		@Order(2)
		public void print_non_empty_statement(String expectedFormat) {

			// GIVEN
			internalClient.deposit(Money.of(BigDecimal.valueOf(10000)));

			var operation = internalClient.getAccount().getHistory().getOperations().get(0);

			var formattedDate = DateFormatter.format(operation.getDate());

			var expectedResult = String.format(expectedFormat, formattedDate, "DEPOSIT", "10000", "10000");

			// WHEN
			internalClient.printOperationHistory();

			// THEN
			this.validate(expectedResult);
		}

		private void validate(String expectedResult) {
			Assertions.assertThat(outputContent).hasToString(expectedResult);
		}

	}

}
