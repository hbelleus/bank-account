package com.sfeir.kata.bank.behaviour.deposit;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;

import com.sfeir.kata.bank.domain.client.ClientOperationService;
import com.sfeir.kata.bank.domain.client.factory.ClientFactory;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientDepositStepDefinition {

		private ClientOperationService client;

		@Before
		public void init() {
				client = ClientFactory.createClient();
		}

		@When("^I deposit (\\d+) euros$")
		public void deposit(BigDecimal amount) {

				client.deposit().accept(MoneyFactory.create(amount));
		}

		@Then("^My balance should be (\\d+)")
		public void
		    my_balance_should_be(BigDecimal expectedBalance) {
			
			Condition<MoneyService> asExpected = new Condition<MoneyService>((money) -> money.getAmount().equals(expectedBalance), expectedBalance.toPlainString());
			
			var resultBalance = client.getAccount()
				                      .getBalance()
				                      .apply();
			
			Assertions.assertThat(resultBalance).is(asExpected);
		}
}
