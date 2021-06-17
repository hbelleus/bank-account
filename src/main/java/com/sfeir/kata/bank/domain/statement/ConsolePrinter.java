package com.sfeir.kata.bank.domain.statement;

import java.io.PrintStream;
import java.util.function.Consumer;

import lombok.Value;

@Value
public class ConsolePrinter implements StatementPrinter {

	PrintStream printer;

	@Override
	public void print(AccountStatement statement) {

		this.consolePrint().accept(STATEMENT_HEADER);

		statement.getLines().stream().map(AccountStatementLine::getValue).forEach(consolePrint());
	}

	@Override
	public void print(String message) {
		this.consolePrint().accept(message);

	}

	private Consumer<String> consolePrint() {
		return this.printer::println;
	}

}
