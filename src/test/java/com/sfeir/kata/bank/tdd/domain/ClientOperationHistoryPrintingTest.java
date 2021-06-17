package com.sfeir.kata.bank.tdd.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.utils.BankClientFactory;

@RunWith(MockitoJUnitRunner.class)
class ClientOperationHistoryPrintingTest {

	private ClientOperation client;

	@BeforeEach
	public void init() {

		client = BankClientFactory.create();
		
	}

	@Test
	void givenEmptyHistory_whenPrintOperationHistory_thenThrowsException() {

		
		Assertions.assertThrows(UnsupportedOperationException.class, () -> client.printOperationHistory());
	}

}
