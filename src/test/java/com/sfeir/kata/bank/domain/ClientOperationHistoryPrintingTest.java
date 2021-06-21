package com.sfeir.kata.bank.domain;

import java.io.PrintStream;

import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.sfeir.kata.bank.domain.client.IClientOperator;
import com.sfeir.kata.bank.domain.client.factory.BankClientFactory;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;
<<<<<<< Upstream, based on branch 'main' of https://github.com/hbelleus/bank-account.git
import com.sfeir.kata.bank.infra.printer.ConsolePrinter;
import com.sfeir.kata.bank.infra.printer.IConsolePrinter;
import com.sfeir.kata.bank.utils.BankClientMockFactory;
=======
import com.sfeir.kata.bank.infra.printer.console.ConsolePrinter;
>>>>>>> 4379d62 Implementing SOLID / clean code principles

@RunWith(JUnitPlatform.class)
@TestMethodOrder(OrderAnnotation.class)
class ClientOperationHistoryPrintingTest {

		private PrintStream       printStream;
		private IStatementPrinter printer;
		private IClientOperator   client;

		@BeforeEach
		public void init() {

				printStream = Mockito.mock(PrintStream.class);
				printer     = new ConsolePrinter(printStream);
				client      = BankClientFactory.create(printer);
		}

		@Test
		@Order(1)
		void givenEmptyHistory_whenPrintOperationHistory_thenPrintMessage() {

				// GIVEN
<<<<<<< Upstream, based on branch 'main' of https://github.com/hbelleus/bank-account.git
				String message = IConsolePrinter.STATEMENT_HEADER;
=======
				var message = IStatementPrinter.STATEMENT_HEADER;
>>>>>>> 4379d62 Implementing SOLID / clean code principles

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
				var amount = BankMoneyFactory.create(200);

				Assumptions.assumeThat(client.deposit(amount))
				           .isTrue();

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
				var amount = BankMoneyFactory.create(200);

				Assumptions.assumeThat(client.deposit(amount))
				           .isTrue();
				Assumptions.assumeThat(client.deposit(amount))
				           .isTrue();

				// WHEN
				client.printOperationHistory();

				// THEN
				Mockito.verify(printStream, Mockito.times(3))
				       .println(Mockito.anyString());
		}
}
