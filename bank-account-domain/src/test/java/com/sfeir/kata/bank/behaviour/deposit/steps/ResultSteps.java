package com.sfeir.kata.bank.behaviour.deposit.steps;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;

import com.sfeir.kata.bank.behaviour.deposit.state.ClientDepositContext;
import com.sfeir.kata.bank.domain.client.account.operation.history.OperationHistoryService;
import com.sfeir.kata.bank.domain.money.MoneyService;

import io.cucumber.java.en.Then;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResultSteps {

		@NonNull
		private final ClientDepositContext clientContext;

		@Then("^My balance after deposit should be (\\d+)$")
		public void
		    my_balance_after_deposit_should_be(BigDecimal expectedBalance) {

				var resultBalance = clientContext.getClient()
				                                 .getAccount()
				                                 .getBalance()
				                                 .apply();
				
				var resultOperations = clientContext.getClient()
				                                    .getAccount()
				                                    .getHistory();

				Condition<MoneyService> asExpected = new Condition<MoneyService>((money) -> money.getAmount()
				                                                                                 .equals(expectedBalance), expectedBalance.toPlainString());

				Condition<OperationHistoryService> twoOperations = new Condition<OperationHistoryService>((history) -> history.size()
				                                                                                                              .apply() == 1, "containing more than one element");

				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(resultOperations)
				                                                           .has(twoOperations),
				                                           () -> Assertions.assertThat(resultBalance)
				                                                           .is(asExpected));
		}
}
