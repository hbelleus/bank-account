package com.sfeir.kata.bank.domain.printer;

import com.sfeir.kata.bank.domain.statement.AccountStatement;

public interface StatementPrinter {

	static final String STATEMENT_HEADER = "|DATE|OPERATION|AMOUNT|BALANCE|";

	void print(AccountStatement statement);

	void print(String message);
}
