package com.sfeir.kata.bank.behaviour.deposit.steps;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;

import com.sfeir.kata.bank.behaviour.deposit.state.AccountDepositContext;
import com.sfeir.kata.bank.domain.common.money.Money;

import io.cucumber.java.en.Then;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResultSteps {

		@NonNull
		private final AccountDepositContext accountContext;

		@Then("^My balance after deposit should be (\\d+)$")
		public void
		    my_balance_after_deposit_should_be(BigDecimal expectedBalance) {

				var resultBalance = accountContext.getAccountSpecification()
				                                  .getBalance()
				                                  .apply();

				var resultOperations = accountContext.getAccountSpecification()
				                                     .getOperations();

				Condition<Money> asExpected = new Condition<Money>((money) -> money.getAmount()
				                                                                   .equals(expectedBalance), expectedBalance.toPlainString());

				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(resultOperations)
				                                                           .hasSize(2),
				                                           () -> Assertions.assertThat(resultBalance)
				                                                           .is(asExpected));
		}
}
