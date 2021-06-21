package com.sfeir.kata.bank.infra.printer;

import java.io.PrintStream;
import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

public interface IConsolePrinter extends IStatementPrinter {

		static final String STATEMENT_HEADER = "|DATE|OPERATION|AMOUNT|BALANCE|";
	
		PrintStream getPrinter();

		@SuppressWarnings("resource")
		default Consumer<String> consolePrint() {
				return this.getPrinter()::println;
		}
}
