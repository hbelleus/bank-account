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

import com.sfeir.kata.bank.domain.client.IClientOperator;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.validation.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.utils.BankClientMockFactory;

import io.vavr.Function0;

@RunWith(JUnitPlatform.class)
class ClientOperationWithdrawalTest {

		private IClientOperator client;

		@BeforeEach
		public void init() {

				client = BankClientMockFactory.create();

				var initDeposit = Money.of(BigDecimal.valueOf(1000));

				client.deposit(initDeposit);
		}

		@Test
		void givenAnyAmount_WhenWithdrawal_thenReturnBoolean() {

				// GIVEN
				var amount = Money.of(BigDecimal.valueOf(100));

				// WHEN
				var result = client.withdraw(amount);

				// THEN
				Assertions.assertThat(result)
				          .isInstanceOf(Boolean.class);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawal_thenOperationIsSaved() {

				// GIVEN
				var amount = Money.of(BigDecimal.valueOf(100));

				// WHEN
				var result = client.withdraw(amount);

				// THEN

				Condition<Operation> savedOperation = new Condition<>((operation) -> operation.getAmount()
				                                                                              .equals(amount.toNegative()
				                                                                                            .apply()), "checking if saved operation has the correct amount", amount);

				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(result)
				                                                           .isTrue(),
				                                           () -> Assertions.assertThat(client.getAccount()
				                                                                             .getHistory()
				                                                                             .getOperations())
				                                                           .isNotEmpty()
				                                                           .has(savedOperation,
				                                                                Index.atIndex(1)));

		}

		@Test
		void givenAnyPositiveAmount_WhenWithdrawal_thenAccountBalanceIsUpdated() {

				// GIVEN
				Money amount = Money.of(BigDecimal.valueOf(100));

				Money expectedValue = Money.of(BigDecimal.valueOf(900));

				Assumptions.assumeThat(client.getAccount()
				                             .getBalance()
				                             .equals(Money.of(BigDecimal.valueOf(1000))));

				// WHEN
				client.withdraw(amount);

				// THEN
				Condition<Money> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                        expectedValue));

				Assertions.assertThat(client.getAccount()
				                            .getBalance())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenPositiveAmount_WhenWithdrawalTwice_thenAccountBalanceIsUpdated() {

				// GIVEN
				Money amount = Money.of(BigDecimal.valueOf(100));

				Money expectedValue = Money.of(BigDecimal.valueOf(800));

				// WHEN
				final var resultOperationSave1 = client.withdraw(amount);
				final var resultOperationSave2 = client.withdraw(amount);

				// THEN
				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(resultOperationSave1)
				                                                           .isTrue(),
				                                           () -> Assertions.assertThat(resultOperationSave2)
				                                                           .isTrue());

				Condition<Operation> savedOperation = new Condition<>((operation) -> operation.getBalanceResult()
				                                                                              .equals(expectedValue), "with correct amount of", expectedValue);

				Assertions.assertThat(client.getAccount()
				                            .getHistory()
				                            .getOperations())
				          .isNotEmpty()
				          .has(savedOperation, Index.atIndex(2));

				Condition<Money> hasBeenUpdated = new Condition<>((money) -> money.equals(expectedValue), String.format("matching expected balance of %s",
				                                                                                                        expectedValue));

				Assertions.assertThat(client.getAccount()
				                            .getBalance())
				          .is(hasBeenUpdated);
		}

		@Test
		void givenAnyPositiveAmountGreaterThanBalance_WhenWithdrawal_thenThrowsException() {

				// GIVEN
				Money amount = Money.of(BigDecimal.valueOf(1500));

				// WHEN
				Function0<Boolean> withdrawal = () -> client.withdraw(amount);

				// THEN
				org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				                                              withdrawal::apply);
		}
}
