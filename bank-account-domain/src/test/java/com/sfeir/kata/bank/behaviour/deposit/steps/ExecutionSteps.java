package com.sfeir.kata.bank.behaviour.deposit.steps;

import java.math.BigDecimal;

import com.sfeir.kata.bank.behaviour.deposit.state.AccountDepositContext;
import com.sfeir.kata.bank.domain.common.money.Money;

import io.cucumber.java.en.When;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExecutionSteps {

		@NonNull
		private final AccountDepositContext accountContext;

		@When("^I deposit (\\d+) euros$")
		public void deposit(BigDecimal amount) {

				accountContext.getDepositFixtureSpecification()
				              .deposit(Money.of(amount),
				                       accountContext.getAccountSpecification());
		}
}
