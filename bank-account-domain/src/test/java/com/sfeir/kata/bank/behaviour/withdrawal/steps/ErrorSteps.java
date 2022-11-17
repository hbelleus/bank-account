package com.sfeir.kata.bank.behaviour.withdrawal.steps;

import org.assertj.core.api.Assertions;

import com.sfeir.kata.bank.behaviour.withdrawal.state.AccountWithdrawalContext;
import com.sfeir.kata.bank.domain.behaviour.account.operation.exception.UnauthorizedOperationException;

import io.cucumber.java.en.Then;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ErrorSteps {

		@NonNull
		private final AccountWithdrawalContext clientContext;

		@Then("^withdrawal should be unauthorized$")
		public void unauthorized() {

				var unAuthorizedAmount = clientContext.getWithdrawalAmount();

				Assertions.assertThatExceptionOfType(UnauthorizedOperationException.class)
				          .isThrownBy(() -> clientContext.getWithdrawalFixtureSpecification()
				                                         .withdraw(unAuthorizedAmount,
				                                                   clientContext.getAccountSpecification()))
				          .withMessageContaining("Trying to withdraw an amount greater the balance account.");
		}
}
