package com.sfeir.kata.bank.utils;

import com.sfeir.kata.bank.domain.client.IClientOperatior;
import com.sfeir.kata.bank.domain.client.ClientOperationContext;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankClientMockFactory {

	public static IClientOperatior create() {

		var account = BankAccountMockFactory.create();

		return ClientOperationContext.of(account, null);
	}

	public static IClientOperatior create(IStatementPrinter printer) {

		var account = BankAccountMockFactory.create();

		return ClientOperationContext.of(account, printer);
	}
}
