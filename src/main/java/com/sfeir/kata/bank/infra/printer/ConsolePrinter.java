package com.sfeir.kata.bank.infra.printer;

import java.io.PrintStream;

import com.sfeir.kata.bank.domain.statement.AccountStatement;
import com.sfeir.kata.bank.domain.statement.AccountStatementLine;

import lombok.Value;

@Value
public class ConsolePrinter implements IConsolePrinter {

		PrintStream printer;

		@Override
		public void print(AccountStatement statement) {

				this.consolePrint().accept(STATEMENT_HEADER);

				statement.getLines()
				         .stream()
				         .map(AccountStatementLine::getValue)
				         .forEach(consolePrint());
		}

		@Override
		public void print(String message) {
				this.consolePrint().accept(message);
		}

}
