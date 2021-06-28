package com.sfeir.kata.bank.behaviour.printing.steps;

import java.io.PrintStream;
import java.math.BigDecimal;

import org.mockito.Mockito;

import com.sfeir.kata.bank.behaviour.printing.state.ClientPrintingContext;
import com.sfeir.kata.bank.domain.client.factory.ClientFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetupSteps {

	@NonNull
	private final ClientPrintingContext clientContext;

	@Before("@printing")
	public void setupForPrinting() {

		var printStream = Mockito.mock(PrintStream.class);
		clientContext.setPrinter(printStream);
		StatementPrinterService printer = statement -> clientContext.getPrinter().print(statement);

		clientContext.setClient(ClientFactory.createClient(printer));
	}

	@Given("^I firstly deposit (\\d+) euros$")
	public void given_a_deposit(BigDecimal amount) {

		clientContext.getClient().deposit().accept(MoneyFactory.create(amount));

	}

	@Given("^I secondly withdraw (\\d+) euros$")
	public void given_a_withdraw(BigDecimal amount) {

		clientContext.getClient().withdraw().accept(MoneyFactory.create(amount));

	}
}
