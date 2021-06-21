package com.sfeir.kata.bank.infra.client;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.money.IMoneyOperator;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

public interface IClientOperator {

		IAccountOperator getAccount();

		IStatementPrinter getPrinter();

		default boolean deposit(IMoneyOperator amount) {
				return this.getAccount().deposit(amount);
		}

		default boolean withdraw(IMoneyOperator amount) {
				return this.getAccount().withdraw(amount);
		}

		default void printOperationHistory() {

				this.getPrinter()
				    .print(this.getAccount().getStatement());
		}
}