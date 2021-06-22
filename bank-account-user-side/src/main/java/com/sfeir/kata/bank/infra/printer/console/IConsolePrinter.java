package com.sfeir.kata.bank.infra.printer.console;

import java.io.PrintStream;
import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;

public interface IConsolePrinter extends StatementPrinterService {

		static final String STATEMENT_HEADER = "|DATE|OPERATION|AMOUNT|BALANCE|";

		PrintStream getPrinter();

		@SuppressWarnings("resource")
		default Consumer<String> consolePrint() {
				return this.getPrinter()::println;
		}

}
