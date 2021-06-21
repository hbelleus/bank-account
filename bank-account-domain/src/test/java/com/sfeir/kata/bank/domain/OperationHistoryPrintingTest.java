package com.sfeir.kata.bank.domain;

import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.account.factory.BankAccountFactory;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

@RunWith(JUnitPlatform.class)
@TestMethodOrder(OrderAnnotation.class)
class OperationHistoryPrintingTest {

		private IAccountOperator account;

		@BeforeEach
		public void init() {

				account = BankAccountFactory.create();
		}

		@Test
		@Order(1)
		void givenEmptyHistory_whenPrintOperationHistory_thenPrintMessage() {

				// GIVEN
				var message = IStatementPrinter.STATEMENT_HEADER;

				// WHEN
				account.printOperationHistory();

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

				Assumptions.assumeThat(account.deposit(amount))
				           .isTrue();

				// WHEN
				account.printOperationHistory();

				// THEN
				Mockito.verify(printStream, Mockito.times(2))
				       .println(Mockito.anyString());
		}

		@Test
		@Order(3)
		void givenNotEmptyHistory_whenPrintOperationHistory_thenPrintTwoOperations() {

				// GIVEN
				var amount = BankMoneyFactory.create(200);

				Assumptions.assumeThat(account.deposit(amount))
				           .isTrue();
				Assumptions.assumeThat(account.deposit(amount))
				           .isTrue();

				// WHEN
				account.printOperationHistory();

				// THEN
				Mockito.verify(printStream, Mockito.times(3))
				       .println(Mockito.anyString());
		}
}
