package com.sfeir.kata.bank.infra.printer.console;

import com.sfeir.kata.bank.domain.client.account.statement.line.AccountStatementLineService;

import io.vavr.Function1;

public interface IConsoleFormatter {

		static final String STATEMENT_HEADER = "|DATE|OPERATION|AMOUNT|BALANCE|";

		default Function1<AccountStatementLineService, String>
		    format() {

				return statementLine -> String.format("|%s|%s|%s|%s|",
				                                      statementLine.getDate(),
				                                      statementLine.getType(),
				                                      statementLine.getAmount(),
				                                      statementLine.getBalance());
		}
}
