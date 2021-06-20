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
import com.sfeir.kata.bank.domain.client.IClientOperation;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.utils.BankClientMockFactory;

@RunWith(JUnitPlatform.class)
class ClientOperationDepositTest {

		private IClientOperation client;

		@BeforeEach
		public void init() {

				client = BankClientMockFactory.create();
		}

		@Test()
		void givenAnyAmount_whenDeposit_thenResultIsBoolean() {

				// GIVEN
				Money amount = Money.of(BigDecimal.valueOf(100));

				// WHEN
				final var result = client.deposit(amount);

				// THEN
				Assertions.assertThat(result)
				          .isInstanceOf(Boolean.class);
		}

		@Test()
		void givenAnyPositiveAmount_whenDeposit_thenOperationIsSaved() {

				// GIVEN
				Money amount = Money.of(BigDecimal.valueOf(100));

				// WHEN
				final var result = client.deposit(amount);

				// THEN

				Condition<Operation> savedOperation = new Condition<>((operation) -> operation.getAmount()
				                                                                              .equals(amount), "checking if saved operation has the correct amount", amount);

				Assertions.assertThat(result).isTrue();
				Assertions.assertThat(client.getAccount()
				                            .getHistory()
				                            .getOperations())
				          .isNotEmpty()
				          .has(savedOperation, Index.atIndex(0));
		}

		@Test()
		void givenAnyPositiveAmount_whenDeposit_thenAccountBalanceIsUpdated() {

				// GIVEN
				Money amount = Money.of(BigDecimal.valueOf(100));

				// WHEN
				client.deposit(amount);

				// THEN
				Condition<Account> accountBalanceIsUpdated = new Condition<>((account) -> account.getBalance()
				                                                                                 .equals(amount), "checking if account balance has been updated", amount);

				Assertions.assertThat(client.getAccount())
				          .is(accountBalanceIsUpdated);
		}

}