package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;

import com.sfeir.kata.bank.exception.TransactionException;

public interface BankTransactionValidator {

	default boolean isAmountGreaterThanZero(BigDecimal amount) {
		return amount.signum() > 0;
	}

	default boolean isLimitRespected(BigDecimal result, BigDecimal limit) {
		return result.compareTo(limit) >= 0;
	}

	void validate() throws TransactionException;

}
