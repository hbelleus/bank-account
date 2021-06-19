package com.sfeir.kata.bank.domain.account;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationHistory;

import io.vavr.Function0;

public interface AccountBalance {

	OperationHistory getHistory();

	default Function0<Money> getBalance() {
		return () -> this.getHistory().getOperations().stream().sorted().findFirst().map(Operation::getBalanceResult)
				.orElse(Money.of(BigDecimal.ZERO));
	}
}
