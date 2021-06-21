package com.sfeir.kata.bank.infra.printer.console;

import java.io.PrintStream;

import com.sfeir.kata.bank.domain.statement.IAccountStatement;

import lombok.Value;

@Value
public class ConsolePrinter 
	implements IConsolePrinter, IConsoleFormatter {

		PrintStream printer;

		@Override
		public void print(IAccountStatement statement) {

				this.consolePrint().accept(STATEMENT_HEADER);

				statement.getLines()
				         .stream()
				         .map(this.format())
				         .forEach(consolePrint());
		}

}
