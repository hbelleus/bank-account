package com.sfeir.kata.bank.domain.behaviour.fixture;

import com.sfeir.kata.bank.domain.behaviour.account.AccountSpecification;
import com.sfeir.kata.bank.domain.behaviour.printer.AccountStatementPrinterSpecification;

public interface AccountStatementPrintingSpecification {

		default void
		    print(AccountSpecification account,
		          AccountStatementPrinterSpecification printer) {
				printer.print(account.getStatement());
		}
}