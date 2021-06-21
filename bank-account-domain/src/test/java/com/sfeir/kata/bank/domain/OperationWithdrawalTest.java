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

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.account.factory.BankAccountFactory;
import com.sfeir.kata.bank.domain.money.IMoneyOperator;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.validation.exception.UnauthorizedOperationException;

import io.vavr.Function0;

@RunWith(JUnitPlatform.class)
class OperationWithdrawalTest {

		private IAccountOperator account;

		@BeforeEach
		public void init() {

				account = BankAccountFactory.create();

				var initDeposit = BankMoneyFactory.create(1000);

				account.deposit(initDeposit);
		}

		@Test
		void givenAnyAmount_WhenWithdrawal_thenReturnBoolean() {

				// GIVEN
				var amount = BankMoneyFactory.create(BigDecimal.valueOf(100));

				// WHEN
				var result = account.withdraw(amount);

				// THEN
				Assertions.assertThat(result)
				          .isInstanceOf(Boolean.class);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawal_thenOperationIsSaved() {

				// GIVEN
				var amount = BankMoneyFactory.create(BigDecimal.valueOf(100));

				// WHEN
				var result = account.withdraw(amount);

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
		void givenAnyPositiveAmount_WhenWithdrawal_thenAccountBalanceIsUpdated() {

				// GIVEN
				var amount = BankMoneyFactory.create(BigDecimal.valueOf(100));

				var expectedValue = BankMoneyFactory.create(BigDecimal.valueOf(900));

				var initialBalance = BankMoneyFactory.create(1000);

				Assumptions.assumeThat(account.getBalance()
				                              .equals(initialBalance));

				// WHEN
				account.withdraw(amount);

				// THEN
				Condition<IMoneyOperator> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                                 expectedValue));

				Assertions.assertThat(account.getBalance())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawalTwice_thenAccountBalanceIsUpdated() {

				// GIVEN
				var amount = BankMoneyFactory.create(100);

				var expectedValue = BankMoneyFactory.create(800);

				// WHEN
				final var resultOperationSave1 = account.withdraw(amount);
				final var resultOperationSave2 = account.withdraw(amount);

				// THEN
				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(resultOperationSave1)
				                                                           .isTrue(),
				                                           () -> Assertions.assertThat(resultOperationSave2)
				                                                           .isTrue());

				Condition<Operation> savedOperation = new Condition<>((operation) -> operation.getBalanceResult()
				                                                                              .equals(expectedValue), "with correct amount of", expectedValue);

				Assertions.assertThat(account.getHistory()
				                             .getOperations())
				          .isNotEmpty()
				          .has(savedOperation, Index.atIndex(2));

				Condition<IMoneyOperator> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                                 expectedValue));

				Assertions.assertThat(account.getBalance())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenAnyPositiveAmountGreaterThanBalance_WhenWithdrawal_thenThrowsException() {

				// GIVEN
				var amount = BankMoneyFactory.create(1500);

				// WHEN
				Function0<Boolean> withdrawal = () -> account.withdraw(amount);

				// THEN
				org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				                                              withdrawal::apply);
		}
}