package com.sfeir.kata.bank.domain.account;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.money.IMoneyOperator;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.statement.IAccountStatement;

import io.vavr.control.Try;

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

		default IAccountStatement getStatement() {
				return generateStatement().apply(this.getHistory());
		}

		default IMoneyOperator getBalance() {

				var numberOfOperations = this.getHistory()
				                             .getOperations()
				                             .size();

				var lastOperationIndex = numberOfOperations - 1;

				var lastOperation = Try.of(() -> this.getHistory()
				                                     .getOperations()
				                                     .get(lastOperationIndex));

				return lastOperation.map(Operation::getBalanceResult)
				                    .getOrElse(BankMoneyFactory.create(BigDecimal.ZERO));
		}

}
