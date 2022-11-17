package com.sfeir.kata.bank.behaviour.printing.steps;

import com.sfeir.kata.bank.behaviour.printing.state.AccountStatementPrintingContext;

import io.cucumber.java.en.When;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ExecutionSteps {

		@NonNull
		private final AccountStatementPrintingContext clientContext;

		@When("^I print the operations$")
		public void i_print_the_operations() {

				clientContext.getAccountStatementPrinterSpecification()
				             .print(clientContext.getAccountSpecification()
				                                 .getStatement());

		}
}
