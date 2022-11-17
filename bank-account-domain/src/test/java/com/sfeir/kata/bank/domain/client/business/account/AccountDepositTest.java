package com.sfeir.kata.bank.domain.client.business.account;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.Account;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.OperationService;

class AccountDepositTest {

		private Account account;

		@BeforeEach
		public void init() {

				account = new Account();
		}

		@Test
		void givenAnyPositiveAmount_whenDeposit_thenOperationIsSaved()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("100");

				// WHEN
				account.deposit().accept(amount);
				;

				// THEN

				Condition<OperationService> savedLine = new Condition<>((operation) -> operation.getAmount()
				                                                                                .equals(amount), "checking if saved operation has the correct amount", amount);
				Assertions.assertThat(account.getHistory()
				                             .getOperations())
				          .isNotEmpty()
				          .have(savedLine);
		}

		@Test
		void givenAnyPositiveAmount_whenDeposit_thenAccountBalanceIsUpdated()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("100");

				// WHEN
				account.deposit().accept(amount);
				;

				// THEN
				Condition<Account> accountBalanceIsUpdated = new Condition<>((account) -> account.getBalance()
				                                                                                 .apply()
				                                                                                 .equals(amount), "checking if account balance has been updated", amount);

				Assertions.assertThat(account)
				          .is(accountBalanceIsUpdated);
		}

}
