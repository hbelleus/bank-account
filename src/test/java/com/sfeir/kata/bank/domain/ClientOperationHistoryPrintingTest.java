package com.sfeir.kata.bank.domain;

import java.io.PrintStream;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.sfeir.kata.bank.domain.client.IClientOperator;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;
import com.sfeir.kata.bank.infra.printer.ConsolePrinter;
import com.sfeir.kata.bank.infra.printer.IConsolePrinter;
import com.sfeir.kata.bank.utils.BankClientMockFactory;

@RunWith(JUnitPlatform.class)
@TestMethodOrder(OrderAnnotation.class)
class ClientOperationHistoryPrintingTest {

		private PrintStream       printStream;
		private IStatementPrinter printer;
		private IClientOperator  client;

		@BeforeEach
		public void init() {

				printStream = Mockito.mock(PrintStream.class);
				printer     = new ConsolePrinter(printStream);
				client      = BankClientMockFactory.create(printer);
		}

		@Test
		@Order(1)
		void givenEmptyHistory_whenPrintOperationHistory_thenPrintMessage() {

				// GIVEN
				String message = IConsolePrinter.STATEMENT_HEADER;

				// WHEN
				client.printOperationHistory();

				// THEN
				org.junit.jupiter.api.Assertions.assertAll(() -> Mockito.verify(printStream)
				                                                        .println(message),
				                                           () -> Mockito.verifyNoMoreInteractions(printStream));
		}

		@Test
		@Order(2)
		void givenNotEmptyHistory_whenPrintOperationHistory_thenPrinterCalledTwice() {

				// GIVEN
				var amount = 200;
				client.deposit(Money.of(BigDecimal.valueOf(amount)));

				// WHEN
				client.printOperationHistory();

				// THEN
				Mockito.verify(printStream, Mockito.times(2))
				       .println(Mockito.anyString());
		}

		@Test
		@Order(3)
		void givenNotEmptyHistory_whenPrintOperationHistory_thenPrintTwoOperations() {

				// GIVEN
				var amount = 200;
				client.deposit(Money.of(BigDecimal.valueOf(amount)));
				client.deposit(Money.of(BigDecimal.valueOf(amount)));

				// WHEN
				client.printOperationHistory();

				// THEN
				Mockito.verify(printStream, Mockito.times(3))
				       .println(Mockito.anyString());
		}
}
