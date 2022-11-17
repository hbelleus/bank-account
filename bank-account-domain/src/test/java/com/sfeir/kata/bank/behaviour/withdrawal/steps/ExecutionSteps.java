package com.sfeir.kata.bank.behaviour.withdrawal.steps;

import java.math.BigDecimal;

import com.sfeir.kata.bank.behaviour.withdrawal.state.AccountWithdrawalContext;
import com.sfeir.kata.bank.domain.common.money.Money;

import io.cucumber.java.en.When;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExecutionSteps {

		@NonNull
		private final AccountWithdrawalContext accountWithdrawalContext;

		@When("^I withdraw (\\d+) euros$")
		public void withdraw(BigDecimal amount) {

				accountWithdrawalContext.getWithdrawalFixtureSpecification()
				                        .withdraw(Money.of(amount),
				                                  accountWithdrawalContext.getAccountSpecification());

		}
}
