package com.sfeir.kata.bank.behaviour.deposit.steps;

import com.sfeir.kata.bank.behaviour.deposit.state.AccountDepositContext;
import com.sfeir.kata.bank.domain.behaviour.account.Account;
import com.sfeir.kata.bank.domain.behaviour.fixture.DepositService;

import io.cucumber.java.Before;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetupSteps {

		@NonNull
		private final AccountDepositContext clientContext;

		@Before("@deposit")
		public void setupForDeposit() {

				clientContext.setDepositFixtureSpecification(new DepositService());
				clientContext.setAccountSpecification(new Account());
		}
}
