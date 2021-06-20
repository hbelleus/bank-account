package com.sfeir.kata.bank.utils;

import com.sfeir.kata.bank.domain.client.IClientOperation;
import com.sfeir.kata.bank.domain.client.ClientOperationContext;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankClientMockFactory {

	public static IClientOperation create() {

		var account = BankAccountMockFactory.create();

		return ClientOperationContext.of(account, null);
	}

	public static IClientOperation create(IStatementPrinter printer) {

		var account = BankAccountMockFactory.create();

		return ClientOperationContext.of(account, printer);
	}
}
