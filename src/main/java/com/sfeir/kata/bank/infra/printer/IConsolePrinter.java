package com.sfeir.kata.bank.infra.printer;

import java.io.PrintStream;
import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

public interface IConsolePrinter extends IStatementPrinter {

		PrintStream getPrinter();

		@SuppressWarnings("resource")
		default Consumer<String> consolePrint() {
				return this.getPrinter()::println;
		}
}
