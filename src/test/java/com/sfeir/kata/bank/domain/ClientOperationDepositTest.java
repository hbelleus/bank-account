package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.operation.Money;
import com.sfeir.kata.bank.utils.BankClientFactory;

@RunWith(JUnitPlatform.class)
class ClientOperationDepositTest {

	private ClientOperation client;

	@BeforeEach
	public void init() {

		client = BankClientFactory.create();
	}

	@Test()
	void givenAnyAmount_whenDeposit_thenResultIsBoolean() {

		// GIVEN
		Money amount = Money.of(BigDecimal.valueOf(100));

		// WHEN
		final var result = client.deposit(amount);

		// THEN
		Assertions.assertThat(result).isInstanceOf(Boolean.class);
	}

	@Test()
	void givenAnyPositiveAmount_whenDeposit_thenOperationIsSaved() {

		// GIVEN
		Money amount = Money.of(BigDecimal.valueOf(100));

		// WHEN
		final var result = client.deposit(amount);

		// THEN
		Assertions.assertThat(result).isTrue();
		Assertions.assertThat(client.getAccount().getHistory().getOperations()).isNotEmpty();
	}

}
