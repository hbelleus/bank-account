package com.sfeir.kata.bank.infra.printer.console;

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

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.factory.AccountFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

@RunWith(JUnitPlatform.class)
@TestMethodOrder(OrderAnnotation.class)
class OperationHistoryPrintingTest {

		private AccountService          account;
		private PrintStream             encapsulatedPrinter;
		private StatementPrinterService printer;

		@BeforeEach
		public void init() {

				account = AccountFactory.createAccount().apply();

				encapsulatedPrinter = Mockito.mock(PrintStream.class);

				printer = new ConsolePrinter(encapsulatedPrinter);
		}

		@Test
		@Order(1)
		void givenEmptyHistory_whenPrintOperationHistory_thenPrintMessage() {

				// GIVEN
				var message = IConsoleFormatter.STATEMENT_HEADER;

				var statement = account.generateStatement().apply();

				// WHEN
				printer.print(statement);

				// THEN
				org.junit.jupiter.api.Assertions.assertAll(() -> Mockito.verify(encapsulatedPrinter)
				                                                        .println(message),
				                                           () -> Mockito.verifyNoMoreInteractions(encapsulatedPrinter));
		}

		@Test
		@Order(2)
		void givenNotEmptyHistory_whenPrintOperationHistory_thenPrinterCalledTwice() {

				// GIVEN
				var amount = BankMoneyFactory.create(200);

				Assumptions.assumeThat(account.deposit()
				                              .apply(amount))
				           .isTrue();

				var statement = account.generateStatement().apply();

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
				var amount = BankMoneyFactory.create(200);

				Assumptions.assumeThat(account.deposit()
				                              .apply(amount))
				           .isTrue();
				Assumptions.assumeThat(account.deposit()
				                              .apply(amount))
				           .isTrue();

				var statement = account.generateStatement().apply();

				// WHEN
				printer.print(statement);

				// THEN
				Mockito.verify(encapsulatedPrinter,
				               Mockito.times(3))
				       .println(Mockito.anyString());
		}
}
