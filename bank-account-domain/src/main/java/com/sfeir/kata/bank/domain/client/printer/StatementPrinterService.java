package com.sfeir.kata.bank.domain.client.printer;

import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;

public interface StatementPrinterService {

		void print(AccountStatementService statement);
}
