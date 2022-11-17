package com.sfeir.kata.bank.domain.bddfriendly.printer;

import com.sfeir.kata.bank.domain.bddfriendly.account.AccountSpecification;

public interface AccountStatementPrintingSpecification {

		AccountStatementPrinterSpecification printer();

		default void
		    printAllOperationsOf(AccountSpecification account) {

				this.printer().print(account.getStatement());
		}
}