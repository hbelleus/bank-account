package com.sfeir.kata.bank.domain.simple.printer;

import com.sfeir.kata.bank.domain.simple.account.AccountSpecification;

public interface AccountStatementPrintingSpecification {

		AccountStatementPrinterSpecification printer();

		default void
		    printAllOperationsOf(AccountSpecification account) {

				this.printer().print(account.getStatement());
		}
}