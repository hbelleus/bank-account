package com.sfeir.kata.bank.behaviour.printing.steps;

import java.io.PrintStream;
import java.math.BigDecimal;

import org.mockito.Mockito;

import com.sfeir.kata.bank.behaviour.printing.state.ClientPrintingContext;
import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.Account;
import com.sfeir.kata.bank.domain.simple.printer.AccountStatementPrinterSpecification;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetupSteps {

		@NonNull
		private final ClientPrintingContext clientContext;

		AccountStatementPrinterSpecification printer;

		@Before("@printing")
		public void setupForPrinting() {

				var printStream = Mockito.mock(PrintStream.class);
				clientContext.setPrinter(printStream);

				printer = statement -> clientContext.getPrinter()
				                                    .print(statement);

				clientContext.setAccount(new Account());
		}

		@Given("^I firstly deposit (\\d+) euros$")
		public void given_a_deposit(BigDecimal amount) {

				clientContext.getAccount()
				             .deposit()
				             .accept(Money.of(amount));

		}

		@Given("^I secondly withdraw (\\d+) euros$")
		public void given_a_withdraw(BigDecimal amount) {

				clientContext.getAccount()
				             .withdraw()
				             .accept(Money.of(amount));

		}
}
