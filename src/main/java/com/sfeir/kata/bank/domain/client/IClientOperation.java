package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.money.Money;

public interface IClientOperation
    extends IClientOperationDecomposition {

		default boolean deposit(Money amount) {
				return deposit().andThen(saveOperation())
				                .apply(amount, this.getAccount());
		}

		default boolean withdraw(Money amount) {
				return withdrawal().andThen(saveOperation())
				                   .apply(amount,
				                          this.getAccount());
		}

		default void printOperationHistory() {

				var statement = generateStatement().apply(this.getAccount()
				                                              .getHistory());
				printStatement().accept(statement);
		}
}
