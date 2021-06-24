package com.sfeir.kata.bank.behaviour.deposit;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.factory.BankClientFactory;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientDepositStepDefinition {

		private ClientService client;
		private MoneyService  amount;

		@Before
		public void init() {
				client = BankClientFactory.createClient();
		}

		@When("^I deposit (\\d+) euros$")
		public void deposit(BigDecimal amount) {

				this.amount = BankMoneyFactory.create(amount);

				client.deposit().apply(this.amount);

		}

		@Then("^My balance should be (\\d+)")
		public void
		    my_balance_should_be(BigDecimal expectedBalance) {
				Assertions.assertThat(client.getAccount()
				                            .getBalance()
				                            .apply())
				          .isEqualTo(amount);
		}
}
