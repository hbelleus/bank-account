package com.sfeir.kata.bank.behaviour.withdrawal.steps;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;

import com.sfeir.kata.bank.behaviour.withdrawal.state.ClientWithdrawalContext;
import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.history.OperationHistoryService;

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

				var resultBalance = clientContext.getAccount()
				                                 .getBalance()
				                                 .apply();

				var resultOperations = clientContext.getAccount()
				                                    .getHistory();

				Condition<Money> asExpected = new Condition<Money>((money) -> money.getAmount()
				                                                                   .equals(expectedBalance), expectedBalance.toPlainString());

				Condition<OperationHistoryService> twoOperations = new Condition<OperationHistoryService>((history) -> history.size()
				                                                                                                              .apply() == 2, "containing two elements");

				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(resultOperations)
				                                                           .has(twoOperations),
				                                           () -> Assertions.assertThat(resultBalance)
				                                                           .is(asExpected));
		}
}
