package com.sfeir.kata.bank.domain.client.business.account;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.simple.account.Account;
import com.sfeir.kata.bank.domain.simple.account.operation.specification.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.simple.account.statement.AccountStatementLine;

class AccountWithdrawalTest {

		private Account account;

		@BeforeEach
		public void init() throws IllegalAccessException {

				account = new Account();

				var initDeposit = Money.of("1000");

				account.deposit(initDeposit);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawal_thenOperationIsSaved()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("100");

				// WHEN
				account.withdraw(amount);

				// THEN

				Condition<AccountStatementLine> savedOperation = new Condition<>((line) -> line.getAmount()
				                                                                               .equals(amount.toNegative()
				                                                                                             .apply()
				                                                                                             .toString()), "checking if saved operation has the correct amount", amount);

				Assertions.assertThat(account.getStatement()
				                             .getLines())
				          .isNotEmpty()
				          .have(savedOperation);

		}

		@Test
		void givenAnyPositiveAmount_WhenWithdrawal_thenAccountBalanceIsCorrectlyUpdated()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("100");

				var expectedValue = Money.of("900");

				var initialBalance = Money.of("1000");

				Assumptions.assumeThat(account.getBalance()
				                              .equals(initialBalance));

				// WHEN
				account.withdraw(amount);

				// THEN
				Condition<Money> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                        expectedValue));

				Assertions.assertThat(account.getBalance())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawalTwice_thenAccountBalanceIsCorrectlyUpdated()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("100");

				var expectedValue = Money.of("800");

				// WHEN
				account.withdraw(amount);
				account.withdraw(amount);

				// THEN
				Condition<AccountStatementLine> savedOperation = new Condition<>((operation) -> operation.getBalance()
				                                                                                         .equals(expectedValue.toString()), "with correct amount of", expectedValue);

				Assertions.assertThat(account.getStatement()
				                             .getLines())
				          .isNotEmpty()
				          .have(savedOperation);

				Condition<Money> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                        expectedValue));

				Assertions.assertThat(account.getBalance())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenAnyPositiveAmountGreaterThanBalance_WhenWithdrawal_thenThrowsException() {

				// GIVEN
				var amount = Money.of("1500");

				// WHEN
				Executable withdrawal = () -> account.withdraw(amount);

				// THEN
				org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				                                              withdrawal::execute);
		}
}
