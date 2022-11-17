package com.sfeir.kata.bank.domain.bddfriendly.service;

import com.sfeir.kata.bank.domain.bddfriendly.account.AccountSpecification;
import com.sfeir.kata.bank.domain.bddfriendly.printer.AccountStatementPrinterSpecification;

public class AccountStatementPrintingService {

		public void
		    print(AccountSpecification account,
		          AccountStatementPrinterSpecification printer) {

				printer.print(account.getStatement());
		}
}
