package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.operation.money.Money;
import com.sfeir.kata.bank.utils.BankClientFactory;

@RunWith(JUnitPlatform.class)
public class ClientOperationWithdrawalTest {

	private ClientOperation client;

	@BeforeEach
	public void init() {

		client = BankClientFactory.create();

		var initDeposit = Money.of(BigDecimal.valueOf(1000));

		client.deposit(initDeposit);
	}

	@Test
	void givenAnyAmount_WhenWithdrawal_thenReturnBoolean() {

		// GIVEN
		var amount = Money.of(BigDecimal.valueOf(100));

		// WHEN
		var result = client.withdrawal(amount);

		// THEN
		Assertions.assertThat(result).isInstanceOf(Boolean.class);
	}
}
