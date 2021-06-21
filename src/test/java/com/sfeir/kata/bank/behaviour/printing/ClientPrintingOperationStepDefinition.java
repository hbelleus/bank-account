package com.sfeir.kata.bank.behaviour.printing;

import java.io.PrintStream;
import java.math.BigDecimal;

import org.mockito.Mockito;

import com.sfeir.kata.bank.domain.client.IClientOperatior;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;
import com.sfeir.kata.bank.infra.printer.ConsolePrinter;
import com.sfeir.kata.bank.utils.BankClientMockFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientPrintingOperationStepDefinition {

	private PrintStream printStream;
	private IStatementPrinter printer;
	private IClientOperatior client;

	@Before
	public void setUp() {
		printStream = Mockito.mock(PrintStream.class);
		printer = new ConsolePrinter(printStream);
		client = BankClientMockFactory.create(printer);
	}

	@Given("^I deposit (\\d+) euros$")
	public void deposit(BigDecimal amount) {

		client.deposit(Money.of(amount));

	}

	@Given("^I withdraw (\\d+) euros$")
	public void withdraw(BigDecimal amount) {

		client.withdraw(Money.of(amount));

	}

	@When("^I print the operations$")
	public void i_print_the_operations() {

		client.printOperationHistory();

	}

	@Then("it should print {string}")
	public void it_should_print(String message) {
		org.junit.jupiter.api.Assertions.assertAll(() -> Mockito.verify(printStream).println(message),
				() -> Mockito.verifyNoMoreInteractions(printStream));
	}

	@Then("^the printer should be called (\\d+) times$")
	public void printer_is_called_3_times(int value) {
		Mockito.verify(printStream, Mockito.times(value)).println(Mockito.anyString());
	}
}
