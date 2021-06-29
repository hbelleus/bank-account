package com.sfeir.kata.bank.behaviour.deposit.steps;

import com.sfeir.kata.bank.behaviour.deposit.state.ClientDepositContext;
import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.factory.ClientFactory;

import io.cucumber.java.Before;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetupSteps {

		@NonNull
		private final ClientDepositContext clientContext;

		@Before("@deposit")
		public void setupForDeposit() {
				clientContext.setClient((ClientService) ClientFactory.createClient());
		}
}
