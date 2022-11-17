package com.sfeir.kata.bank.domain.client.business.account;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.simple.account.Account;
import com.sfeir.kata.bank.domain.simple.account.statement.AccountStatementLine;

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
				account.deposit(amount);

				// THEN

				Condition<AccountStatementLine> savedLine = new Condition<>((statementLine) -> statementLine.getAmount()
				                                                                                            .equals(amount), "checking if saved operation has the correct amount", amount);
				Assertions.assertThat(account.getStatement()
				                             .getLines())
				          .isNotEmpty()
				          .have(savedLine);
		}

		@Test
		void givenAnyPositiveAmount_whenDeposit_thenAccountBalanceIsUpdated()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("100");

				// WHEN
				account.deposit(amount);

				// THEN
				Condition<Account> accountBalanceIsUpdated = new Condition<>((account) -> {
						try {
								return account.getBalance().equals(amount);
						} catch (IllegalAccessException e) {
								return false;
						}
				}, "checking if account balance has been updated", amount);

				Assertions.assertThat(account)
				          .is(accountBalanceIsUpdated);
		}

}
