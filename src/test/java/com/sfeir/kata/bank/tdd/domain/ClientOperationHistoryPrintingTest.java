package com.sfeir.kata.bank.tdd.domain;

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

import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.utils.BankClientMockFactory;

@RunWith(JUnitPlatform.class)
@TestMethodOrder(OrderAnnotation.class)
class ClientOperationHistoryPrintingTest {

	private static final PrintStream printer = Mockito.mock(PrintStream.class);
	private ClientOperation client;

	@BeforeEach
	public void init() {

		client = BankClientMockFactory.create(printer);
	}

	@Test
	@Order(1)
	void givenEmptyHistory_whenPrintOperationHistory_thenPrintMessage() {

		// GIVEN
		String message = "No Operation";

		// WHEN
		client.printOperationHistory();

		// THEN
		org.junit.jupiter.api.Assertions.assertAll(() -> Mockito.verify(printer).println(message),
				() -> Mockito.verifyNoMoreInteractions(printer));
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
		Mockito.verify(printer, Mockito.times(2)).println(Mockito.anyString());
	}

	@Test
	@Order(3)
	void givenNotEmptyHistory_whenPrintOperationHistory_thenPrintTwoOperations() {

		// GIVEN
		var amount = 200;
		client.deposit(Money.of(BigDecimal.valueOf(amount)));

		// WHEN
		client.printOperationHistory();

		// THEN
		Mockito.verify(printer, Mockito.times(3)).println(Mockito.anyString());
	}
}
