package com.sfeir.kata.bank.behaviour.printing.steps;

import org.mockito.Mockito;

import com.sfeir.kata.bank.behaviour.printing.state.ClientPrintingContext;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;

import io.cucumber.java.en.Then;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ResultSteps {

	@NonNull
	private final ClientPrintingContext clientContext;
	
	@Then("it should print the statement")
	public void it_should_print() {
		org.junit.jupiter.api.Assertions.assertAll(
				() -> Mockito.verify(clientContext.getPrinter()).print(Mockito.isA(AccountStatementService.class)),
				() -> Mockito.verifyNoMoreInteractions(clientContext.getPrinter()));
	}

	@Then("^the printer should be called (\\d+) time$")
	public void printer_is_called_1_time(int value) {
		Mockito.verify(clientContext.getPrinter(), Mockito.times(value)).print(Mockito.isA(AccountStatementService.class));
	}
}
