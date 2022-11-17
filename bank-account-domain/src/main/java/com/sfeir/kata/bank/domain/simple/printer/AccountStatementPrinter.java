package com.sfeir.kata.bank.domain.simple.printer;

import com.sfeir.kata.bank.domain.simple.account.statement.AccountStatement;

public class AccountStatementPrinter
    implements AccountStatementPrinterSpecification {

		@Override
		public void print(AccountStatement statement) {
				System.out.print(statement);

		}
}
