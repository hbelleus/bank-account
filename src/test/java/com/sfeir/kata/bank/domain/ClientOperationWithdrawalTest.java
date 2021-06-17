package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.money.Money;
import com.sfeir.kata.bank.utils.BankClientFactory;

@RunWith(JUnitPlatform.class)
class ClientOperationWithdrawalTest {

	private ClientOperation client;

	@BeforeEach
	public void init() {

		client = BankClientFactory.create();

		var initDeposit = Money.of(BigDecimal.valueOf(1000));

		client.deposit(initDeposit);
	}

	@Test
	void givenAnyAmount_WhenWithdrawal_thenReturnBoolean() {

		// GIVEN
		var amount = Money.of(BigDecimal.valueOf(100));

		// WHEN
		var result = client.withdrawal(amount);

		// THEN
		Assertions.assertThat(result).isInstanceOf(Boolean.class);
	}

	@Test
	void givenPositiveAmount_WhenWithdrawal_thenOperationIsSaved() {

		// GIVEN
		var amount = Money.of(BigDecimal.valueOf(100));

		// WHEN
		var result = client.withdrawal(amount);

		// THEN

		Condition<Operation> savedOperation = new Condition<>(
				(operation) -> operation.getAmount().equals(amount.toNegative()),
				"checking if saved operation has the correct amount", amount);

		org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(result).isTrue(),
				() -> Assertions.assertThat(client.getAccount().getHistory().getOperations()).isNotEmpty()
						.has(savedOperation, Index.atIndex(1)));

	}

	@Test
	void givenAnyPositiveAmount_WhenWithdrawal_thenAccountBalanceIsUpdated() {

		// GIVEN
		Money amount = Money.of(BigDecimal.valueOf(100));

		Money expectedValue = Money.of(BigDecimal.valueOf(900));

		// WHEN
		client.withdrawal(amount);

		// THEN
		Condition<Account> accountBalanceIsUpdated = new Condition<>(
				(account) -> account.getBalance().equals(expectedValue),
				"checking if account balance has been updated correctly");

		Assertions.assertThat(client.getAccount()).is(accountBalanceIsUpdated);
	}
}
