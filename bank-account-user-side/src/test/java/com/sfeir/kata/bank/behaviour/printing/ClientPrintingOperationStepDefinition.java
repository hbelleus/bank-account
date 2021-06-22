package com.sfeir.kata.bank.behaviour.printing;

import java.io.PrintStream;
import java.math.BigDecimal;

import org.assertj.core.api.Assumptions;
import org.mockito.Mockito;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.factory.BankClientFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;
import com.sfeir.kata.bank.infra.printer.console.ConsolePrinter;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ClientPrintingOperationStepDefinition {

		private PrintStream             printStream;
		private StatementPrinterService printer;
		private ClientService           client;

		@Before
		public void init() {
				printStream = Mockito.mock(PrintStream.class);
				printer     = new ConsolePrinter(printStream);
				client      = BankClientFactory.create(printer);
		}

		@Given("^I deposit (\\d+) euros$")
		public void deposit(BigDecimal amount) {

				Assumptions.assumeThat(client.deposit()
				                             .apply(BankMoneyFactory.create(amount)))
				           .isTrue();

		}

		@Given("^I withdraw (\\d+) euros$")
		public void withdraw(BigDecimal amount) {

				Assumptions.assumeThat(client.withdraw()
				                             .apply(BankMoneyFactory.create(amount)))
				           .isTrue();

		}

		@When("^I print the operations$")
		public void i_print_the_operations() {

				client.printOperationHistory();

		}

		@Then("it should print {string}")
		public void it_should_print(String message) {
				org.junit.jupiter.api.Assertions.assertAll(() -> Mockito.verify(printStream)
				                                                        .println(message),
				                                           () -> Mockito.verifyNoMoreInteractions(printStream));
		}

		@Then("^the printer should be called (\\d+) times$")
		public void printer_is_called_3_times(int value) {
				Mockito.verify(printStream, Mockito.times(value))
				       .println(Mockito.anyString());
		}
}
