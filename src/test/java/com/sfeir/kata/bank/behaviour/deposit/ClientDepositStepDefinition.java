package com.sfeir.kata.bank.behaviour.deposit;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;

import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.utils.BankClientFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientDepositStepDefinition {

	private ClientOperation clientOperation;
	private Money amount;

	@Before
	public void setUp() {
		clientOperation = BankClientFactory.create();
	}

	@When("^I deposit (\\d+) euros$")
	public void deposit(BigDecimal amount) {
		
		this.amount = Money.of(amount);
		
		clientOperation.deposit(this.amount);
		
	}

	@Then("^My balance should be (\\d+)")
	public void my_balance_should_be(BigDecimal expectedBalance) {
		Assertions.assertThat(clientOperation.getAccount().getBalance()).isEqualTo(amount);
	}
}