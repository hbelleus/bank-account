package com.sfeir.kata.bank.behaviour.printing;

import java.io.PrintStream;
import java.math.BigDecimal;

import org.assertj.core.api.Assumptions;
import org.mockito.Mockito;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.factory.BankClientFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

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
				printer     = printStream::print;
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

		@Then("it should print the statement")
		public void it_should_print() {
				org.junit.jupiter.api.Assertions.assertAll(() -> Mockito.verify(printStream)
				                                                        .print(Mockito.isA(AccountStatementService.class)),
				                                           () -> Mockito.verifyNoMoreInteractions(printStream));
		}

		@Then("^the printer should be called (\\d+) time$")
		public void printer_is_called_1_time(int value) {
				Mockito.verify(printStream, Mockito.times(value))
				       .print(Mockito.isA(AccountStatementService.class));
		}
}
