package com.sfeir.kata.bank.domain.ddd.reporting.client;

import com.sfeir.kata.bank.domain.ddd.reporting.client.account.AccountReportingService;
import com.sfeir.kata.bank.domain.ddd.reporting.client.account.printer.StatementPrinterService;

public interface ClientReportingService {

		AccountReportingService getAccount();

		StatementPrinterService getPrinter();

		default void printOperationHistory() {
				this.getAccount()
				    .printStatement()
				    .accept(this.getPrinter());
		}
}