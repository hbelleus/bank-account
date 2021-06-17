package com.sfeir.kata.bank.functional;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.operation.money.Money;
import com.sfeir.kata.bank.functional.deposit.DepositFunctionalCase;
import com.sfeir.kata.bank.functional.deposit.DepositTestDefinition;
import com.sfeir.kata.bank.functional.withdrawal.WithdrawalFunctionalCase;
import com.sfeir.kata.bank.functional.withdrawal.WithdrawalFunctionalTest;
import com.sfeir.kata.bank.utils.BankClientFactory;

import io.vavr.Function0;

@RunWith(JUnitPlatform.class)
class BankClientAccountShould {

	private ClientOperation client;

	@BeforeEach
	public void init() {

		client = BankClientFactory.create();
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

}
