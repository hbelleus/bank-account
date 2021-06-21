package com.sfeir.kata.bank.domain.operation.validation;

import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.validation.exception.UnauthorizedOperationException;

public interface OperationValidator {

		static void validateWithdrawal(Money incomingAmount,
		                               Money initialBalance) {

				var isOperationUnauthorized = incomingAmount.isLargerThan(initialBalance);

				if (isOperationUnauthorized)
						throw new UnauthorizedOperationException("Unauthorized operation. Contact your bank.");
		}
}
