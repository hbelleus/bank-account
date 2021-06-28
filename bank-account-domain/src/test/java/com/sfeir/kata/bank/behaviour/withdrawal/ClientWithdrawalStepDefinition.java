package com.sfeir.kata.bank.behaviour.withdrawal;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;

import com.sfeir.kata.bank.domain.client.ClientOperationService;
import com.sfeir.kata.bank.domain.client.account.operation.history.OperationHistoryService;
import com.sfeir.kata.bank.domain.client.account.operation.specification.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.client.factory.ClientFactory;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientWithdrawalStepDefinition {

	private ClientOperationService client;
	private MoneyService amount;

	@Before
	public void init() {
		client = ClientFactory.createClient();
	}

	@Given("^I want to retrieve (\\d+) euros from my account$")
	public void i_want_to_retrieve(BigDecimal amount) {

		this.amount = MoneyFactory.create(amount);

	}

	@When("^I deposit (\\d+) euros$")
	public void deposit(BigDecimal amount) {

		client.deposit().accept(MoneyFactory.create(amount));

	}

	@When("^I withdraw (\\d+) euros$")
	public void withdraw(BigDecimal amount) {

		client.withdraw().accept(MoneyFactory.create(amount));

	}

	@Then("^My balance should be (\\d+)$")
	public void my_balance_should_be(BigDecimal expectedBalance) {

		var resultBalance = client.getAccount().getBalance().apply();
		var resultOperations = client.getAccount().getHistory();

		Condition<MoneyService> asExpected = new Condition<MoneyService>(
				(money) -> money.getAmount().equals(expectedBalance), "");

		Condition<OperationHistoryService> twoOperations = new Condition<OperationHistoryService>(
				(history) -> history.size().apply() == 2, "");

		org.junit.jupiter.api.Assertions.assertAll(() -> Assertions.assertThat(resultOperations).has(twoOperations),
				() -> Assertions.assertThat(resultBalance).is(asExpected));
	}

	@Then("^withdrawal should be unauthorized$")
	public void unauthorized() {

		org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				() -> client.withdraw().accept(this.amount));
	}
}
