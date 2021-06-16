package com.sfeir.kata.bank;

import java.lang.reflect.InvocationTargetException;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.deposit.DepositiFunctionalCase;
import com.sfeir.kata.bank.domain.BankAccount;
import com.sfeir.kata.bank.domain.BankClient;
import com.sfeir.kata.bank.service.BankTransactionService;
import com.sfeir.kata.bank.service.BankTransactionServiceImpl;
import com.sfeir.kata.bank.utils.BankClientFactory;
import com.sfeir.kata.bank.utils.BankTransactionServiceFactory;
import com.sfeir.kata.bank.withdrawal.WithdrawalFunctionalCase;

@RunWith(JUnitPlatform.class)
class BankClientAccountShould {

	private BankClient client;
	private BankTransactionService transactionService;

	@BeforeEach
	public void init() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {

		client = BankClientFactory.create();

		transactionService = BankTransactionServiceFactory.createImplementation(BankTransactionServiceImpl.class);
	}

	@Nested
	public class DepositShould implements DepositiFunctionalCase {

		@Override
		@ParameterizedTest
		@MethodSource
		public void givenPositiveAmount_whenDeposit_thenAccountBalanceIsUpdated(String amount, String expectedValue) {

			// GIVEN
			BankAccount account = client.getAccount();

			final var initialBalance = account.getBalance();

			Assumptions.assumeTrue(initialBalance == "500");

			// WHEN
			final var result = transactionService.deposit(amount, account);

			// THEN
			Assertions.assertAll(() -> Assertions.assertTrue(result),
					() -> MatcherAssert.assertThat(account.getBalance(), CoreMatchers.is(expectedValue)));

		}

		@Override
		@ParameterizedTest
		@ValueSource(strings = { "100", "58.99" })
		public void givenPositiveAmount_whenDeposit_thenTransactionIsSaved(String amount) {

			// GIVEN
			BankAccount account = client.getAccount();

			// WHEN
			transactionService.deposit(amount, account);

			// THEN
			Assertions.assertAll(() -> Assertions.assertFalse(account.getHistory().isEmpty()),
					() -> MatcherAssert.assertThat(account.getHistory().get(0).getAmount(), CoreMatchers.is(amount)));

		}

		@Override
		@ParameterizedTest
		@MethodSource
		public void givenNegativeOrNullAmount_whenDeposit_thenAccountBalanceIsNotUpdated(String amount,
				String expectedValue) {

			// GIVEN
			BankAccount account = client.getAccount();

			final var initialBalance = account.getBalance();

			Assumptions.assumeTrue(initialBalance == expectedValue);

			// WHEN
			final var result = transactionService.deposit(amount, account);

			// THEN
			Assertions.assertAll(() -> Assertions.assertFalse(result),
					() -> MatcherAssert.assertThat(account.getBalance(), CoreMatchers.is(initialBalance)));

		}
	}

	@Nested
	public class WithdrawalShould implements WithdrawalFunctionalCase {

		@Override
		@ParameterizedTest
		@MethodSource
		public void givenPositiveAmountInLimit_whenWithdraw_thenAccountBalanceIsUpdated(String amount,
				String expectedValue) {

			// GIVEN
			BankAccount account = client.getAccount();

			final var initialBalance = account.getBalance();

			Assumptions.assumeTrue(initialBalance == "500");

			// WHEN
			final var result = transactionService.withdrawal(amount, account);

			// THEN
			Assertions.assertAll(() -> Assertions.assertTrue(result),
					() -> MatcherAssert.assertThat(account.getBalance(), CoreMatchers.is(expectedValue)));

		}

		@Override
		@ParameterizedTest
		@MethodSource
		public void givenPositiveAmountOutOfLimit_whenWithdraw_thenTransactionFailed(String amount,
				String expectedValue) {

			// GIVEN
			BankAccount account = client.getAccount();

			final var initialBalance = account.getBalance();

			Assumptions.assumeTrue(initialBalance == expectedValue);

			// WHEN
			final var result = transactionService.withdrawal(amount, account);

			// THEN
			Assertions.assertAll(() -> Assertions.assertFalse(result),
					() -> MatcherAssert.assertThat(account.getBalance(), CoreMatchers.is(initialBalance)));

		}

		@Override
		@ParameterizedTest
		@ValueSource(strings = { "100", "58.99" })
		public void givenPositiveAmountInLimit_whenWithdraw_thenAccountBalanceIsSaved(String amount) {

			// GIVEN
			BankAccount account = client.getAccount();

			// WHEN
			transactionService.withdrawal(amount, account);

			// THEN
			Assertions.assertAll(() -> Assertions.assertFalse(account.getHistory().isEmpty()),
					() -> MatcherAssert.assertThat(account.getHistory().get(0).getAmount(), CoreMatchers.is(amount)));

		}

	}

}
