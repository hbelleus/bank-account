package com.sfeir.kata.bank.domain.operation.utils;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.operation.OperationType;

public interface OperationValidator {

	static void validate(BigDecimal amount, BigDecimal initialAmount, OperationType type) {

		var isOperationUnauthorized = amount.abs().compareTo(initialAmount) > 0;

		if (OperationType.WITHDRAWAL == type && isOperationUnauthorized)
			throw new IllegalArgumentException("Amount should be positive");
	}
}
