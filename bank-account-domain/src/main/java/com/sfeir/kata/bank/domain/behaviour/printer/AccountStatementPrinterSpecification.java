package com.sfeir.kata.bank.domain.behaviour.printer;

import com.sfeir.kata.bank.domain.behaviour.account.statement.AccountStatementSpecification;

public interface AccountStatementPrinterSpecification {

		void print(AccountStatementSpecification statement);
}
