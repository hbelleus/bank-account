package com.sfeir.kata.bank.domain.statement;

public interface StatementPrinter {

	static final String STATEMENT_HEADER = "|DATE|OPERATION|AMOUNT|BALANCE|";

	void print(AccountStatement statement);
	
	void print(String message);
}
