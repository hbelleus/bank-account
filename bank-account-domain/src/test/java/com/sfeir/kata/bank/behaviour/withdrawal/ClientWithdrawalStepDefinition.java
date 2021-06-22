package com.sfeir.kata.bank.behaviour.withdrawal;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.operation.specification.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.client.factory.BankClientFactory;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.vavr.Function0;

public class ClientWithdrawalStepDefinition {

		private ClientService client;
		private MoneyService  amount;

		@Before
		public void init() {
				client = BankClientFactory.create();
		}

		@When("^I deposit (\\d+) euros$")
		public void deposit(BigDecimal amount) {

				client.deposit()
				      .apply(BankMoneyFactory.create(amount));

		}

		@Given("^I want to retrieve (\\d+) euros from my account$")
		public void i_want_to_retrieve(BigDecimal amount) {

				this.amount = BankMoneyFactory.create(amount);

		}

		@When("^I withdraw (\\d+) euros$")
		public void withdraw(BigDecimal amount) {

				client.withdraw()
				      .apply(BankMoneyFactory.create(amount));

		}

		@Then("^My balance should be (\\d+)$")
		public void
		    my_balance_should_be(BigDecimal expectedBalance) {

				org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(client.getAccount()
				                                                                             .getHistory()
				                                                                             .getOperations())
				                                                           .hasSize(2),
				                                           () -> Assertions.assertThat(client.getAccount()
				                                                                             .getHistory()
				                                                                             .getOperations()
				                                                                             .get(1)
				                                                                             .getBalanceResult())
				                                                           .hasFieldOrPropertyWithValue("amount",
				                                                                                        expectedBalance),
				                                           () -> Assertions.assertThat(client.getAccount()
				                                                                             .getHistory()
				                                                                             .getOperations()
				                                                                             .get(1))
				                                                           .hasFieldOrPropertyWithValue("balanceResult",
				                                                                                        client.getAccount()
				                                                                                              .getBalance()
				                                                                                              .apply()));
		}

		@Then("^withdrawal should be unauthorized$")
		public void unauthorized() {

				Function0<Boolean> withdrawal = () -> client.withdraw()
				                                            .apply(this.amount);

				org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				                                              () -> withdrawal.apply());
		}
}
