package com.sfeir.kata.bank.domain.simple.account;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.simple.account.statement.AccountStatement;
import com.sfeir.kata.bank.domain.simple.printer.AccountStatementPrinterSpecification;

public interface AccountSpecification {

		Money getBalance() throws IllegalAccessException;

		void deposit(Money amount)
		    throws IllegalAccessException;

		void withdraw(Money amount)
		    throws IllegalAccessException;

		AccountStatement getStatement();

		default void
		    printStatement(AccountStatementPrinterSpecification printer) {
				printer.print(this.getStatement());
		}
}
