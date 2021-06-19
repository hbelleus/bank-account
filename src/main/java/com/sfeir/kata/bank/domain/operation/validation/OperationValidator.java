package com.sfeir.kata.bank.domain.operation.validation;

import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.exception.UnauthorizedOperationException;

public interface OperationValidator {

	static void validateWithdrawal(Money incomingAmount, Money initialAmount) {

		var isOperationUnauthorized = incomingAmount.isLargerThan(initialAmount);

		if (isOperationUnauthorized)
			throw new UnauthorizedOperationException("Unauthorized operation. Contact your bank.");
	}
}
