package com.sfeir.kata.bank.domain.bddfriendly.printer;

import com.sfeir.kata.bank.domain.bddfriendly.account.statement.AccountStatementSpecification;

public interface AccountStatementPrinterSpecification {

		void print(AccountStatementSpecification statement);
}
