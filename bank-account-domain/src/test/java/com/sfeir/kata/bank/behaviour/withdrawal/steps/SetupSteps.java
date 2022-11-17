package com.sfeir.kata.bank.behaviour.withdrawal.steps;

import java.math.BigDecimal;

import com.sfeir.kata.bank.behaviour.withdrawal.state.ClientWithdrawalContext;
import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.Account;

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

				clientContext.setAccount(new Account());
		}

		@Given("^I have (\\d+) euros in my account$")
		public void
		    i_have_euros_in_my_account(BigDecimal amount) {

				clientContext.getAccount()
				             .deposit()
				             .accept(Money.of(amount));
		}

		@Given("^I want to retrieve (\\d+) euros from my account$")
		public void i_want_to_retrieve(BigDecimal amount) {

				clientContext.setWithdrawalAmount(Money.of(amount));

		}
}
