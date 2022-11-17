package com.sfeir.kata.bank.behaviour.withdrawal.steps;

import java.math.BigDecimal;

import com.sfeir.kata.bank.behaviour.withdrawal.state.AccountWithdrawalContext;
import com.sfeir.kata.bank.domain.bddfriendly.account.Account;
import com.sfeir.kata.bank.domain.bddfriendly.service.DepositService;
import com.sfeir.kata.bank.domain.bddfriendly.service.WithdrawalService;
import com.sfeir.kata.bank.domain.common.money.Money;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetupSteps {

		@NonNull
		private final AccountWithdrawalContext clientContext;

		@Before("@withdrawal")
		public void setupForWithdrawal() {

				clientContext.setDepositFixtureSpecification(new DepositService());
				clientContext.setWithdrawalFixtureSpecification(new WithdrawalService());
				clientContext.setAccountSpecification(new Account());
		}

		@Given("^I have (\\d+) euros in my account$")
		public void
		    i_have_euros_in_my_account(BigDecimal amount) {

				clientContext.getDepositFixtureSpecification()
				             .deposit(Money.of(amount),
				                      clientContext.getAccountSpecification());
		}

		@Given("^I want to retrieve (\\d+) euros from my account$")
		public void i_want_to_retrieve(BigDecimal amount) {

				clientContext.setWithdrawalAmount(Money.of(amount));

		}
}
