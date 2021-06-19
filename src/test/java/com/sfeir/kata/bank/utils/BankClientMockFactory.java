package com.sfeir.kata.bank.utils;

import com.sfeir.kata.bank.domain.client.ClientOperation;
import com.sfeir.kata.bank.domain.client.ClientOperationContext;
import com.sfeir.kata.bank.domain.printer.StatementPrinter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankClientMockFactory {

	public static ClientOperation create() {

		var account = BankAccountMockFactory.create();

		return ClientOperationContext.of(account, null);
	}

	public static ClientOperation create(StatementPrinter printer) {

		var account = BankAccountMockFactory.create();

		return ClientOperationContext.of(account, printer);
	}
}
