package com.sfeir.kata.bank.infra.printer.console;

import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.Mockito;

import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;
import com.sfeir.kata.bank.domain.simple.ClientService;
import com.sfeir.kata.bank.domain.simple.factory.ClientFactory;
import com.sfeir.kata.bank.domain.simple.printer.AccountStatementPrinterService;

@TestMethodOrder(OrderAnnotation.class)
class OperationHistoryPrintingTest {

		private ClientService           client;
		private PrintStream             encapsulatedPrinter;
		private AccountStatementPrinterService printer;

		@BeforeEach
		public void init() {

				encapsulatedPrinter = Mockito.mock(PrintStream.class);

				printer = new ConsolePrinter(encapsulatedPrinter);

				client = ClientFactory.createClientForPrinting()
				                      .apply(printer);
		}

		@Test
		@Order(1)
		void givenEmptyHistory_whenPrintOperationHistory_thenPrintMessage() {

				// GIVEN
				var message = IConsoleFormatter.STATEMENT_HEADER;

				// WHEN
				client.printOperationHistory();

				// THEN
				org.junit.jupiter.api.Assertions.assertAll(() -> Mockito.verify(encapsulatedPrinter)
				                                                        .println(message),
				                                           () -> Mockito.verifyNoMoreInteractions(encapsulatedPrinter));
		}

		@Test
		@Order(2)
		void givenNotEmptyHistory_whenPrintOperationHistory_thenPrinterCalledTwice() {

				// GIVEN
				var amount = MoneyFactory.create(200);

				client.deposit().accept(amount);

				var statement = client.getAccount()
				                      .generateStatement()
				                      .apply();

				// WHEN
				printer.print(statement);

				// THEN
				Mockito.verify(encapsulatedPrinter,
				               Mockito.times(2))
				       .println(Mockito.anyString());
		}

		@Test
		@Order(3)
		void givenNotEmptyHistory_whenPrintOperationHistory_thenPrintTwoOperations() {

				// GIVEN
				var amount = MoneyFactory.create(200);

				client.deposit()
				      .andThen(client.deposit())
				      .accept(amount);

				var statement = client.getAccount()
				                      .generateStatement()
				                      .apply();

				// WHEN
				printer.print(statement);

				// THEN
				Mockito.verify(encapsulatedPrinter,
				               Mockito.times(3))
				       .println(Mockito.anyString());
		}
}
