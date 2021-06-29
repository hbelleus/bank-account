package com.sfeir.kata.bank.behaviour.withdrawal.steps;

import java.math.BigDecimal;

import com.sfeir.kata.bank.behaviour.withdrawal.state.ClientWithdrawalContext;
import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.factory.ClientFactory;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetupSteps {

		@NonNull
		private final ClientWithdrawalContext clientContext;

		@Before("@withdrawal")
		public void setupForWithdrawal() {
				clientContext.setClient((ClientService) ClientFactory.createClientForOperation());
		}

		@Given("^I have (\\d+) euros in my account$")
		public void
		    i_have_euros_in_my_account(BigDecimal amount) {

				clientContext.getClient()
				             .deposit()
				             .accept(MoneyFactory.create(amount));
		}

		@Given("^I want to retrieve (\\d+) euros from my account$")
		public void i_want_to_retrieve(BigDecimal amount) {

				clientContext.setWithdrawalAmount(MoneyFactory.create(amount));

		}
}
