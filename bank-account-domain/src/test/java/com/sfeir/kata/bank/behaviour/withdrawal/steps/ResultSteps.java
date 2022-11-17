package com.sfeir.kata.bank.behaviour.withdrawal.steps;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;

import com.sfeir.kata.bank.behaviour.withdrawal.state.AccountWithdrawalContext;
import com.sfeir.kata.bank.domain.common.money.Money;

import io.cucumber.java.en.Then;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResultSteps {

		@NonNull
		private final AccountWithdrawalContext clientContext;

		@Then("^My balance after withdrawal should be (\\d+)$")
		public void
		    my_balance_after_withdrawal_should_be(BigDecimal expectedBalance) {

				var resultBalance = clientContext.getAccountSpecification()
				                                 .getBalance()
				                                 .apply();

				var resultOperations = clientContext.getAccountSpecification()
				                                    .getOperations();

				Condition<Money> asExpected = new Condition<Money>((money) -> money.getAmount()
				                                                                   .equals(expectedBalance), expectedBalance.toPlainString());

				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(resultOperations)
				                                                           .hasSize(3),
				                                           () -> Assertions.assertThat(resultBalance)
				                                                           .is(asExpected));
		}
}
