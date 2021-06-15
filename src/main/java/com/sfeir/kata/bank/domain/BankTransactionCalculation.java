package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;

import io.vavr.Function1;
import io.vavr.Function2;

public interface BankTransactionCalculation {

	public abstract Function2<BigDecimal, BigDecimal, BigDecimal> getOperation();

	default Function2<String, String, String> balanceCalculation() {

		Function1<String, BigDecimal> toBigDecimal = BigDecimal::new;

		return (x, y) -> this.getOperation().apply(toBigDecimal.apply(x), toBigDecimal.apply(y)).toPlainString();
	}
}
