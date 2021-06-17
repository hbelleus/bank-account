package com.sfeir.kata.bank.utils;

import java.io.PrintStream;

import com.sfeir.kata.bank.domain.client.Client;
import com.sfeir.kata.bank.domain.client.ClientOperation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankClientMockFactory {

	public static ClientOperation create() {

		var account = BankAccountMockFactory.create();

		return Client.builder().account(account).build();
	}

	public static ClientOperation create(PrintStream printer) {

		var account = BankAccountMockFactory.create();

		return Client.builder().account(account).printer(printer).build();
	}
}
