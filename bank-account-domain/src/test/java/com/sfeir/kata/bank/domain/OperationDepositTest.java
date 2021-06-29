package com.sfeir.kata.bank.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.factory.AccountFactory;
import com.sfeir.kata.bank.domain.client.account.operation.OperationService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

@RunWith(JUnitPlatform.class)
class OperationDepositTest {

		private AccountService account;

		@BeforeEach
		public void init() {

				account = AccountFactory.createAccount().apply();
		}

		@Test
		void givenAnyPositiveAmount_whenDeposit_thenOperationIsSaved() {

				// GIVEN
				var amount = MoneyFactory.create(100);

				// WHEN
				account.deposit().accept(amount);

				// THEN

				Condition<OperationService> savedOperation = new Condition<>((operation) -> operation.getAmount()
				                                                                                     .equals(amount), "checking if saved operation has the correct amount", amount);
				Assertions.assertThat(account.getHistory()
				                             .getOperations())
				          .isNotEmpty()
				          .has(savedOperation, Index.atIndex(0));
		}

		@Test
		void givenAnyPositiveAmount_whenDeposit_thenAccountBalanceIsUpdated() {

				// GIVEN
				var amount = MoneyFactory.create(100);

				// WHEN
				account.deposit().accept(amount);

				// THEN
				Condition<AccountService> accountBalanceIsUpdated = new Condition<>((account) -> account.getBalance()
				                                                                                        .apply()
				                                                                                        .equals(amount), "checking if account balance has been updated", amount);

				Assertions.assertThat(account)
				          .is(accountBalanceIsUpdated);
		}

}
