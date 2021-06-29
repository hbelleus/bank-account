package com.sfeir.kata.bank.behaviour.withdrawal.steps;

import java.math.BigDecimal;

import com.sfeir.kata.bank.behaviour.withdrawal.state.ClientWithdrawalContext;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import io.cucumber.java.en.When;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExecutionSteps {

		@NonNull
		private final ClientWithdrawalContext clientContext;

		@When("^I withdraw (\\d+) euros$")
		public void withdraw(BigDecimal amount) {

				clientContext.getClient()
				             .withdraw()
				             .accept(MoneyFactory.create(amount));

		}
}
