package com.sfeir.kata.bank;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.deposit.DepositFunctionalCase;
import com.sfeir.kata.bank.deposit.DepositTestDefinition;
import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.money.Money;
import com.sfeir.kata.bank.utils.BankClientFactory;

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

}
