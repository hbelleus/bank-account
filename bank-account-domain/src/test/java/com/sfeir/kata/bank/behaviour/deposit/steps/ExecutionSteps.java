package com.sfeir.kata.bank.behaviour.deposit.steps;

import java.math.BigDecimal;

import com.sfeir.kata.bank.behaviour.deposit.state.ClientDepositContext;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import io.cucumber.java.en.When;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExecutionSteps {

	@NonNull
	private final ClientDepositContext clientContext;

	@When("^I deposit (\\d+) euros$")
	public void deposit(BigDecimal amount) {

		clientContext.getClient().deposit().accept(MoneyFactory.create(amount));
	}
}
