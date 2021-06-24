package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.factory.AccountFactory;
import com.sfeir.kata.bank.domain.client.account.operation.factory.Operation;
import com.sfeir.kata.bank.domain.client.account.operation.specification.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

import io.vavr.Function0;

@RunWith(JUnitPlatform.class)
class OperationWithdrawalTest {

		private AccountService account;

		@BeforeEach
		public void init() {

				account = AccountFactory.createAccount().apply();

				var initDeposit = BankMoneyFactory.create(1000);

				account.deposit().apply(initDeposit);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawal_thenOperationIsSaved() {

				// GIVEN
				var amount = BankMoneyFactory.create(BigDecimal.valueOf(100));

				// WHEN
				var result = account.withdraw().apply(amount);

				// THEN

				Condition<Operation> savedOperation = new Condition<>((operation) -> operation.getAmount()
				                                                                              .equals(amount.toNegative()
				                                                                                            .apply()), "checking if saved operation has the correct amount", amount);

				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(result)
				                                                           .isTrue(),
				                                           () -> Assertions.assertThat(account.getHistory()
				                                                                              .getOperations())
				                                                           .isNotEmpty()
				                                                           .has(savedOperation,
				                                                                Index.atIndex(1)));

		}

		@Test
		void givenAnyPositiveAmount_WhenWithdrawal_thenAccountBalanceIsCorrectlyUpdated() {

				// GIVEN
				var amount = BankMoneyFactory.create(BigDecimal.valueOf(100));

				var expectedValue = BankMoneyFactory.create(BigDecimal.valueOf(900));

				var initialBalance = BankMoneyFactory.create(1000);

				Assumptions.assumeThat(account.getBalance()
				                              .apply()
				                              .equals(initialBalance));

				// WHEN
				account.withdraw().apply(amount);

				// THEN
				Condition<MoneyService> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                               expectedValue));

				Assertions.assertThat(account.getBalance().apply())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawalTwice_thenAccountBalanceIsCorrectlyUpdated() {

				// GIVEN
				var amount = BankMoneyFactory.create(100);

				var expectedValue = BankMoneyFactory.create(800);

				// WHEN
				final var resultOperationSave1 = account.withdraw()
				                                        .apply(amount);
				final var resultOperationSave2 = account.withdraw()
				                                        .apply(amount);

				// THEN
				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(resultOperationSave1)
				                                                           .isTrue(),
				                                           () -> Assertions.assertThat(resultOperationSave2)
				                                                           .isTrue());

				Condition<Operation> savedOperation = new Condition<>((operation) -> operation.getBalance()
				                                                                              .equals(expectedValue), "with correct amount of", expectedValue);

				Assertions.assertThat(account.getHistory()
				                             .getOperations())
				          .isNotEmpty()
				          .has(savedOperation, Index.atIndex(2));

				Condition<MoneyService> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                               expectedValue));

				Assertions.assertThat(account.getBalance().apply())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenAnyPositiveAmountGreaterThanBalance_WhenWithdrawal_thenThrowsException() {

				// GIVEN
				var amount = BankMoneyFactory.create(1500);

				// WHEN
				Function0<Boolean> withdrawal = () -> account.withdraw()
				                                             .apply(amount);

				// THEN
				org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				                                              withdrawal::apply);
		}
}
