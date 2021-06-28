package com.sfeir.kata.bank.functional;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.eclipse.collections.impl.list.mutable.MutableListFactoryImpl;
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
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sfeir.kata.bank.domain.client.ClientOperationService;
import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.operation.OperationService;
import com.sfeir.kata.bank.domain.client.account.operation.history.factory.OperationHistoryFactory;
import com.sfeir.kata.bank.domain.client.account.operation.specification.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.client.account.statement.factory.AccountStatementFactory;
import com.sfeir.kata.bank.domain.client.factory.ClientFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;
import com.sfeir.kata.bank.functional.deposit.DepositTestDefinition;
import com.sfeir.kata.bank.functional.deposit.DepositTestSource;
import com.sfeir.kata.bank.functional.printing.PrintingOperationsTestDefinition;
import com.sfeir.kata.bank.functional.withdrawal.WithdrawalTestDefinition;
import com.sfeir.kata.bank.functional.withdrawal.WithdrawalTestSource;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
@TestMethodOrder(OrderAnnotation.class)
class BankClientAccountShouldTest {

	private ClientOperationService client;

	@BeforeEach
	public void init() {

		client = ClientFactory.createClient();
	}

	@Nested
	@DisplayName("Testing deposit functionality")
	public class DepositShould implements DepositTestSource, DepositTestDefinition {

		@Override
		@ParameterizedTest
		@MethodSource("generatePositiveAmount")
		public void make_a_deposit(MoneyService amount) {

			// GIVEN input amount

			// WHEN
			client.deposit().accept(amount);

			// THEN

			Condition<AccountService> accountWithSavedOperation = new Condition<>(
					(account) -> !account.getHistory().isEmpty().apply(), "checking if account has not empty history",
					amount);

			Condition<OperationService> operationWithCorrectAmount = new Condition<>(
					(operation) -> operation.getAmount().equals(amount),
					"checking if saved operation has the correct amount");

			org.junit.jupiter.api.Assertions.assertAll(
					() -> Assertions.assertThat(client.getAccount()).is(accountWithSavedOperation),
					() -> Assertions.assertThat(client.getAccount().getHistory().getOperations())
							.has(operationWithCorrectAmount, Index.atIndex(0)),
					() -> Assertions.assertThat(client.getAccount().getBalance().apply()).isEqualTo(amount));

		}
	}

	@Nested
	@DisplayName("Testing withdrawal functionality")
	@TestInstance(Lifecycle.PER_CLASS)
	public class WithdrawalShould implements WithdrawalTestSource, WithdrawalTestDefinition {

		@BeforeEach
		public void init() {

			var initMoney = MoneyFactory.create(BigDecimal.valueOf(500));

			client.deposit().accept(initMoney);

			Assumptions.assumeThat(client.getAccount().getBalance().apply()).isEqualTo(initMoney);
		}

		@Override
		@ParameterizedTest
		@MethodSource("givenAuthorizedAmount")
		public void make_a_withdrawal_with_success(MoneyService amount) {

			// GIVEN an earlier deposit of 500 and input amount
			var initMoney = MoneyFactory.create(500);

			var expectedBalance = initMoney.putMoney().apply(amount.toNegative().apply());

			// WHEN
			client.withdraw().accept(amount);

			// THEN

			Condition<AccountService> accountWithSavedOperation = new Condition<>(
					(account) -> !account.getHistory().isEmpty().apply(), "checking if account has not empty history",
					amount);

			Condition<OperationService> operationWithCorrectAmount = new Condition<>(
					(operation) -> operation.getAmount().equals(amount.toNegative().apply()),
					"checking if saved operation has the correct amount");

			Condition<OperationService> operationWithCorrectBalanceResult = new Condition<>(
					(operation) -> operation.getBalance().equals(expectedBalance),
					"checking if saved operation has the correct balance result");

			org.junit.jupiter.api.Assertions.assertAll(
					() -> Assertions.assertThat(client.getAccount()).is(accountWithSavedOperation),
					() -> Assertions.assertThat(client.getAccount().getHistory().getOperations())
							.has(operationWithCorrectAmount, Index.atIndex(0)),
					() -> Assertions.assertThat(client.getAccount().getHistory().getOperations())
							.has(operationWithCorrectBalanceResult, Index.atIndex(0)),
					() -> Assertions.assertThat(client.getAccount().getBalance().apply()).isEqualTo(expectedBalance));

		}

		@Override
		@ParameterizedTest
		@MethodSource("givenUnauthorizedAmount")
		public void make_an_unauthorized_withdrawal(MoneyService amount) {

			// GIVEN input amount

			// WHEN
			Executable result = () -> client.withdraw().accept(amount);
			// THEN

			org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class, () -> result.execute());

		}
	}

	@Nested
	@DisplayName("Testing printing operations fuunctionality")
	@TestInstance(Lifecycle.PER_CLASS)
	public class PrintOperationsShould implements PrintingOperationsTestDefinition {

		private ClientService internalClient;
		private ByteArrayOutputStream output;

		@BeforeEach
		void setUp() {

			output = new ByteArrayOutputStream();
			StatementPrinterService localPrinter = new PrintStream(output)::print;
			internalClient = ClientFactory.createClient(localPrinter);

		}

		@Override
		@Test
		public void print_statement_of_empty_history() {

			// GIVEN
			var withEmptyHistory = OperationHistoryFactory.initializeHistory().apply();
			var expectedStatement = AccountStatementFactory.createStatement().apply(withEmptyHistory);

			// WHEN
			internalClient.printOperationHistory();

			// THEN
			Assertions.assertThat(output).hasToString(expectedStatement.toString());

		}

		@Override
		@Test
		public void print_non_empty_statement() {

			// GIVEN
			internalClient.deposit().accept(MoneyFactory.create(10000));

			var operation = internalClient.getAccount().getHistory().getOperations().getFirst();

			var historyContainingOneOperation = OperationHistoryFactory.populateHistory()
					.apply(MutableListFactoryImpl.INSTANCE.of(operation));

			var expectedStatement = AccountStatementFactory.createStatement().apply(historyContainingOneOperation);

			// WHEN
			internalClient.printOperationHistory();

			// THEN
			Assertions.assertThat(output).hasToString(expectedStatement.toString());
		}

	}

}
