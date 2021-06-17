package com.sfeir.kata.bank;

import java.math.BigDecimal;

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
import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.operation.Money;
import com.sfeir.kata.bank.utils.BankClientFactory;
import com.sfeir.kata.bank.withdrawal.WithdrawalFunctionalCase;

@RunWith(JUnitPlatform.class)
class BankClientAccountShould {

	private ClientOperation client;

	@BeforeEach
	public void init() {

		client = BankClientFactory.create();
	}

	@Nested
	public class DepositShould implements DepositiFunctionalCase {

		@Override
		@ParameterizedTest
		@MethodSource("givenPositiveAmount")
		public void givenPositiveAmount_whenDeposit_thenAccountIsUpdated(Money amount) {

			// GIVEN input amount

			// WHEN
			final var result = client.deposit(amount);

			// THEN
			Assertions.assertAll(() -> Assertions.assertTrue(result),
					() -> org.assertj.core.api.Assertions.assertThat(client.getAccount().getHistory().getOperations())
							.isEmpty(),
					() -> org.assertj.core.api.Assertions.assertThat(client.getAccount().getBalance().getAmount())
							.isEqualTo(amount.getAmount()));

		}

		@Override
		@ParameterizedTest
		@MethodSource("givenNegativeAmount")
		public void givenNegativeAmount_whenDeposit_thenAccountBalanceNotUpdated(Money amount) {

			// GIVEN input amount

			// WHEN
			final var result = client.deposit(amount);

			// THEN
			Assertions.assertAll(() -> Assertions.assertFalse(result),
					() -> org.assertj.core.api.Assertions.assertThat(client.getAccount().getHistory().getOperations())
							.isEmpty(),
					() -> org.assertj.core.api.Assertions.assertThat(client.getAccount().getBalance().getAmount())
							.isEqualTo(BigDecimal.ZERO));

		}
	}

}
