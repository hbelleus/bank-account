package com.sfeir.kata.bank.domain.printer;

import java.io.PrintStream;
import java.util.function.Consumer;

public interface IConsolePrinter extends IStatementPrinter {

		PrintStream getPrinter();

		@SuppressWarnings("resource")
		default Consumer<String> consolePrint() {
				return this.getPrinter()::println;
		}
}
