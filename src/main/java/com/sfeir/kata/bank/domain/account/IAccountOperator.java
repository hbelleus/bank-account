package com.sfeir.kata.bank.domain.account;

import com.sfeir.kata.bank.domain.money.IMoneyOperator;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

public interface IAccountOperator
    extends IAccountOperationDecomposition {

		default boolean deposit(IMoneyOperator amount) {
				return deposit().andThen(saveOperation())
				                .apply(amount, this.getBalance());
		}

		default boolean withdraw(IMoneyOperator amount) {
				return withdrawal().andThen(saveOperation())
				                   .apply(amount,
				                          this.getBalance());
		}

		default void
		    printOperationHistory(IStatementPrinter printer) {

				var statement = generateStatement().apply(this.getHistory());
				printStatement(printer).accept(statement);
		}

}
