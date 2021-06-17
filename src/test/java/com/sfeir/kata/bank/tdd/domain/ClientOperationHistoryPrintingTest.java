package com.sfeir.kata.bank.tdd.domain;

import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sfeir.kata.bank.domain.client.ClientOperation;
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
	void givenEmptyHistory_whenPrintOperationHistory_thenPrintHeaderOnly() {

		// GIVEN
		String header = "|OPERATION|AMOUNT|INITIAL BALANCE|BALANCE RESULT|";

		// WHEN
		client.printOperationHistory();

		// THEN
		org.junit.jupiter.api.Assertions.assertAll(() -> Mockito.verify(printer).println(header),
				() -> Mockito.verifyNoMoreInteractions(printer));
	}

}
