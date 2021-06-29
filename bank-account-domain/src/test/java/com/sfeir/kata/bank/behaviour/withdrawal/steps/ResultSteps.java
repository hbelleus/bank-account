package com.sfeir.kata.bank.behaviour.withdrawal.steps;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;

import com.sfeir.kata.bank.behaviour.withdrawal.state.ClientWithdrawalContext;
import com.sfeir.kata.bank.domain.client.account.operation.history.OperationHistoryService;
import com.sfeir.kata.bank.domain.money.MoneyService;

import io.cucumber.java.en.Then;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResultSteps {

		@NonNull
		private final ClientWithdrawalContext clientContext;

		@Then("^My balance after withdrawal should be (\\d+)$")
		public void
		    my_balance_after_withdrawal_should_be(BigDecimal expectedBalance) {

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
				                                                                                                              .apply() == 2, "containing two elements");

				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(resultOperations)
				                                                           .has(twoOperations),
				                                           () -> Assertions.assertThat(resultBalance)
				                                                           .is(asExpected));
		}
}
