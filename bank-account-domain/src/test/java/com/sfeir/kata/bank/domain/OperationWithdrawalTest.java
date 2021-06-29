package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.factory.AccountFactory;
import com.sfeir.kata.bank.domain.client.account.operation.OperationService;
import com.sfeir.kata.bank.domain.client.account.operation.specification.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

@RunWith(JUnitPlatform.class)
class OperationWithdrawalTest {

		private AccountService account;

		@BeforeEach
		public void init() {

				account = AccountFactory.createAccount().apply();

				var initDeposit = MoneyFactory.create(1000);

				account.deposit().accept(initDeposit);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawal_thenOperationIsSaved() {

				// GIVEN
				var amount = MoneyFactory.create(BigDecimal.valueOf(100));

				// WHEN
				account.withdraw().accept(amount);

				// THEN

				Condition<OperationService> savedOperation = new Condition<>((operation) -> operation.getAmount()
				                                                                                     .equals(amount.toNegative()
				                                                                                                   .apply()), "checking if saved operation has the correct amount", amount);

				Assertions.assertThat(account.getHistory()
				                             .getOperations())
				          .isNotEmpty()
				          .has(savedOperation, Index.atIndex(0));

		}

		@Test
		void givenAnyPositiveAmount_WhenWithdrawal_thenAccountBalanceIsCorrectlyUpdated() {

				// GIVEN
				var amount = MoneyFactory.create(BigDecimal.valueOf(100));

				var expectedValue = MoneyFactory.create(BigDecimal.valueOf(900));

				var initialBalance = MoneyFactory.create(1000);

				Assumptions.assumeThat(account.getBalance()
				                              .apply()
				                              .equals(initialBalance));

				// WHEN
				account.withdraw().accept(amount);

				// THEN
				Condition<MoneyService> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                               expectedValue));

				Assertions.assertThat(account.getBalance().apply())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawalTwice_thenAccountBalanceIsCorrectlyUpdated() {

				// GIVEN
				var amount = MoneyFactory.create(100);

				var expectedValue = MoneyFactory.create(800);

				// WHEN
				account.withdraw()
				       .andThen(account.withdraw())
				       .accept(amount);

				// THEN
				Condition<OperationService> savedOperation = new Condition<>((operation) -> operation.getBalance()
				                                                                                     .equals(expectedValue), "with correct amount of", expectedValue);

				Assertions.assertThat(account.getHistory()
				                             .getOperations())
				          .isNotEmpty()
				          .has(savedOperation, Index.atIndex(0));

				Condition<MoneyService> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                               expectedValue));

				Assertions.assertThat(account.getBalance().apply())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenAnyPositiveAmountGreaterThanBalance_WhenWithdrawal_thenThrowsException() {

				// GIVEN
				var amount = MoneyFactory.create(1500);

				// WHEN
				Executable withdrawal = () -> account.withdraw()
				                                     .accept(amount);

				// THEN
				org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				                                              withdrawal::execute);
		}
}
