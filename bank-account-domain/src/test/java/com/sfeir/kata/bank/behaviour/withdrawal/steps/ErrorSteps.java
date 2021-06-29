package com.sfeir.kata.bank.behaviour.withdrawal.steps;

import com.sfeir.kata.bank.behaviour.withdrawal.state.ClientWithdrawalContext;
import com.sfeir.kata.bank.domain.client.account.operation.specification.exception.UnauthorizedOperationException;

import io.cucumber.java.en.Then;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorSteps {

		@NonNull
		private final ClientWithdrawalContext clientContext;

		@Then("^withdrawal should be unauthorized$")
		public void unauthorized() {

				var withdrawal = clientContext.getClient()
				                              .withdraw();

				var unAuthorizedAmount = clientContext.getWithdrawalAmount();

				org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				                                              () -> withdrawal.accept(unAuthorizedAmount));
		}
}
