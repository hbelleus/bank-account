package com.sfeir.kata.bank.tdd.domain;

import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.utils.BankClientMockFactory;

@RunWith(MockitoJUnitRunner.class)
class ClientOperationHistoryPrintingTest {

	private ClientOperation client;

	@BeforeEach
	public void init() {

		PrintStream printer = Mockito.mock(PrintStream.class);

		client = BankClientMockFactory.create(printer);

	}

	@Test
	void givenEmptyHistory_whenPrintOperationHistory_thenThrowsException() {

		Assertions.assertThrows(UnsupportedOperationException.class, () -> client.printOperationHistory());
	}

}
