package com.sfeir.kata.bank.domain.operation.validation;

import com.sfeir.kata.bank.domain.money.IMoneyOperator;
import com.sfeir.kata.bank.domain.operation.validation.exception.UnauthorizedOperationException;

public interface OperationValidator {

		static void
		    validateWithdrawal(IMoneyOperator incomingAmount,
		                       IMoneyOperator initialBalance) {

				var isOperationUnauthorized = Boolean.TRUE == incomingAmount.isLargerThan()
				                                                            .apply(initialBalance);

				if (isOperationUnauthorized)
						throw new UnauthorizedOperationException("Unauthorized operation. Contact your bank.");
		}
}
