package com.sfeir.kata.bank.tdd.domain;

import java.io.PrintStream;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.utils.BankClientMockFactory;

@RunWith(MockitoJUnitRunner.class)
class ClientOperationHistoryPrintingTest {

	private static final PrintStream printer = Mockito.mock(PrintStream.class);
	private ClientOperation client;

	@BeforeEach
	public void init() {

		client = BankClientMockFactory.create(printer);
	}

	@Test
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
	void givenNotEmptyHistory_whenPrintOperationHistory_thenPrintTwoOperations() {

		// GIVEN
		var amount = 200;
		client.deposit(Money.of(BigDecimal.valueOf(amount)));

		String header = "|DATE|OPERATION|AMOUNT|BALANCE|";
		String operation = client.getAccount().getHistory().getOperations().get(0).toString();

		// WHEN
		client.printOperationHistory();

		// THEN
		Mockito.verify(printer, Mockito.times(3)).println(Mockito.anyString());
	}
}
