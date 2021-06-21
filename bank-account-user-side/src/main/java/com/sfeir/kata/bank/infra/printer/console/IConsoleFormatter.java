package com.sfeir.kata.bank.infra.printer.console;

import com.sfeir.kata.bank.domain.statement.IAccountStatementLine;

import io.vavr.Function1;

public interface IConsoleFormatter {

	default Function1<IAccountStatementLine, String> format() {
		
		return statementLine -> String.format("|%s|%s|%s|%s|",
												statementLine.getDate(),
								                statementLine.getType(),
								                statementLine.getAmount(),
								                statementLine.getBalance());
	}
}
