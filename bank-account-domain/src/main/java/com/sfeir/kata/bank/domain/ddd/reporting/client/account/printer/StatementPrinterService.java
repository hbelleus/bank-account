package com.sfeir.kata.bank.domain.ddd.reporting.client.account.printer;

import com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement.AccountStatementService;

public interface StatementPrinterService {

		void print(AccountStatementService statement);
}
