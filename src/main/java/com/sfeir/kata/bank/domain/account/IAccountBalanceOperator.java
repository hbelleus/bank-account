package com.sfeir.kata.bank.domain.account;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.factory.OperationHistory;

import io.vavr.control.Try;

public interface IAccountBalanceOperator {

		OperationHistory getHistory();

		default Money getBalance() {

				var numberOfOperations = this.getHistory()
				                             .getOperations()
				                             .size();

				var lastOperationIndex = numberOfOperations - 1;

				var lastOperation = Try.of(() -> this.getHistory()
				                                     .getOperations()
				                                     .get(lastOperationIndex));

				return lastOperation.map(Operation::getBalanceResult)
				                    .getOrElse(Money.of(BigDecimal.ZERO));
		}
}
