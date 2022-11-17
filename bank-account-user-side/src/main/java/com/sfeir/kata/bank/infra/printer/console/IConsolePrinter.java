package com.sfeir.kata.bank.infra.printer.console;

import java.io.PrintStream;
import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.simple.printer.AccountStatementPrinterService;

public interface IConsolePrinter
    extends AccountStatementPrinterService {

		PrintStream getPrinter();

		@SuppressWarnings("resource")
		default Consumer<String> consolePrint() {
				return this.getPrinter()::println;
		}

}
