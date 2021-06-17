package com.sfeir.kata.bank.domain.operation.validation;

import com.sfeir.kata.bank.domain.operation.OperationType;
import com.sfeir.kata.bank.domain.operation.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.operation.money.Money;

public interface OperationValidator {

	static void validate(Money amount, Money initialAmount, OperationType type) {

		var isOperationUnauthorized = Boolean.TRUE.equals(amount.isLargerThan(initialAmount));

		if (OperationType.WITHDRAWAL == type && isOperationUnauthorized)
			throw new UnauthorizedOperationException("Unauthorized operation. Contact your bank.");
	}
}
