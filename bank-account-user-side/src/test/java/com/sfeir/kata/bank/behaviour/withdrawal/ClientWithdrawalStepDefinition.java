package com.sfeir.kata.bank.behaviour.withdrawal;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;

import com.sfeir.kata.bank.domain.money.IMoneyOperator;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;
import com.sfeir.kata.bank.domain.operation.validation.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.infra.client.IClientOperator;
import com.sfeir.kata.bank.infra.client.factory.BankClientFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientWithdrawalStepDefinition {

		private IClientOperator clientOperation;
		private IMoneyOperator  amount;

		@Before
		public void setUp() {
				clientOperation = BankClientFactory.create();
		}

		@When("^I deposit (\\d+) euros$")
		public void deposit(BigDecimal amount) {

				clientOperation.deposit(BankMoneyFactory.create(amount));

		}

		@Given("^I want to retrieve (\\d+) euros from my account$")
		public void i_want_to_retrieve(BigDecimal amount) {

				this.amount = BankMoneyFactory.create(amount);

		}

		@When("^I withdraw (\\d+) euros$")
		public void withdraw(BigDecimal amount) {

				clientOperation.withdraw(BankMoneyFactory.create(amount));

		}

		@Then("^My balance should be (\\d+)$")
		public void
		    my_balance_should_be(BigDecimal expectedBalance) {

				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(clientOperation.getAccount()
				                                                                                      .getHistory()
				                                                                                      .getOperations())
				                                                           .hasSize(2),
				                                           () -> Assertions.assertThat(clientOperation.getAccount()
				                                                                                      .getHistory()
				                                                                                      .getOperations()
				                                                                                      .get(1)
				                                                                                      .getBalanceResult())
				                                                           .hasFieldOrPropertyWithValue("amount",
				                                                                                        expectedBalance),
				                                           () -> Assertions.assertThat(clientOperation.getAccount()
				                                                                                      .getHistory()
				                                                                                      .getOperations()
				                                                                                      .get(1))
				                                                           .hasFieldOrPropertyWithValue("balanceResult",
				                                                                                        clientOperation.getAccount()
				                                                                                                       .getBalance()));
		}

		@Then("^withdrawal should be unauthorized$")
		public void unauthorized() {
				org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				                                              () -> clientOperation.withdraw(this.amount));
		}
}
