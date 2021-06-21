package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

public interface IClientOperatior {

		IAccountOperator getAccount();

		IStatementPrinter getPrinter();

		default boolean deposit(Money amount) {
				return this.getAccount().deposit(amount);
		}

		default boolean withdraw(Money amount) {
				return this.getAccount().withdraw(amount);
		}

		default void printOperationHistory() {

				this.getAccount()
				    .printOperationHistory(this.getPrinter());
		}
}
