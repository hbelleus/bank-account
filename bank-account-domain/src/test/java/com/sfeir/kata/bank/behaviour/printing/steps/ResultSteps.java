package com.sfeir.kata.bank.behaviour.printing.steps;

import org.mockito.Mockito;

import com.sfeir.kata.bank.behaviour.printing.state.AccountStatementPrintingContext;
import com.sfeir.kata.bank.domain.behaviour.account.statement.AccountStatementSpecification;

import io.cucumber.java.en.Then;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResultSteps {

		@NonNull
		private final AccountStatementPrintingContext clientContext;

		@Then("it should print the statement")
		public void it_should_print() {
				org.junit.jupiter.api.Assertions.assertAll(() -> Mockito.verify(clientContext.getAccountStatementPrinterSpecification())
				                                                        .print(Mockito.isA(AccountStatementSpecification.class)),
				                                           () -> Mockito.verifyNoMoreInteractions(clientContext.getAccountStatementPrinterSpecification()));
		}

		@Then("^the printer should be called (\\d+) time$")
		public void printer_is_called_1_time(int value) {
				Mockito.verify(clientContext.getAccountStatementPrinterSpecification(),
				               Mockito.times(value))
				       .print(Mockito.isA(AccountStatementSpecification.class));
		}
}
