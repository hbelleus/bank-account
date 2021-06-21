package com.sfeir.kata.bank.behaviour.deposit;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;

import com.sfeir.kata.bank.domain.client.IClientOperator;
import com.sfeir.kata.bank.domain.client.factory.BankClientFactory;
import com.sfeir.kata.bank.domain.money.IMoneyOperator;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientDepositStepDefinition {

		private IClientOperator clientOperation;
		private IMoneyOperator  amount;

		@Before
		public void setUp() {
				clientOperation = BankClientFactory.create();
		}

		@When("^I deposit (\\d+) euros$")
		public void deposit(BigDecimal amount) {

				this.amount = BankMoneyFactory.create(amount);

				clientOperation.deposit(this.amount);

		}

		@Then("^My balance should be (\\d+)")
		public void
		    my_balance_should_be(BigDecimal expectedBalance) {
				Assertions.assertThat(clientOperation.getAccount()
				                                     .getBalance())
				          .isEqualTo(amount);
		}
}
