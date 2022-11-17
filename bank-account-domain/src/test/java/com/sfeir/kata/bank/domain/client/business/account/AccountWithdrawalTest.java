package com.sfeir.kata.bank.domain.client.business.account;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.Account;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.OperationService;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.specification.exception.UnauthorizedOperationException;

class AccountWithdrawalTest {

		private Account account;

		@BeforeEach
		public void init() throws IllegalAccessException {

				account = new Account();

				var initDeposit = Money.of("1000");

				account.deposit().accept(initDeposit);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawal_thenOperationIsSaved()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("100");

				// WHEN
				account.withdraw().accept(amount);

				// THEN

				Condition<OperationService> savedOperation = new Condition<>((operation) -> operation.getAmount()
				                                                                                     .equals(amount.toNegative()
				                                                                                                   .apply()), "checking if saved operation has the correct amount", amount);

				Assertions.assertThat(account.getHistory()
				                             .getOperations())
				          .isNotEmpty()
				          .haveAtLeastOne(savedOperation);

		}

		@Test
		void givenAnyPositiveAmount_WhenWithdrawal_thenAccountBalanceIsCorrectlyUpdated()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("100");

				var expectedValue = Money.of("900");

				var initialBalance = Money.of("1000");

				Assumptions.assumeThat(account.getBalance()
				                              .apply()
				                              .equals(initialBalance));

				// WHEN
				account.withdraw().accept(amount);

				// THEN
				Condition<Money> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                        expectedValue));

				Assertions.assertThat(account.getBalance().apply())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawalTwice_thenAccountBalanceIsCorrectlyUpdated()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("100");

				var expectedValue = Money.of("800");

				// WHEN
				account.withdraw().accept(amount);
				account.withdraw().accept(amount);

				// THEN
				Condition<OperationService> savedOperation = new Condition<>((operation) -> operation.getBalance()
				                                                                                     .equals(expectedValue), "with correct amount of", expectedValue);

				Assertions.assertThat(account.getHistory()
				                             .getOperations())
				          .isNotEmpty()
				          .haveAtLeastOne(savedOperation);

				Condition<Money> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                        expectedValue));

				Assertions.assertThat(account.getBalance().apply())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenAnyPositiveAmountGreaterThanBalance_WhenWithdrawal_thenThrowsException() {

				// GIVEN
				var amount = Money.of("1500");

				// WHEN
				Executable withdrawal = () -> account.withdraw()
				                                     .accept(amount);

				// THEN
				org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				                                              withdrawal::execute);
		}
}
