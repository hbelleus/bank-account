package com.sfeir.kata.bank.domain.client.account.operation.specification;

import java.util.function.BiConsumer;

import com.sfeir.kata.bank.domain.client.account.operation.specification.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.money.MoneyService;

public interface AuthorizedOperationSpecification {

		static BiConsumer<MoneyService, MoneyService>
		    validateWithdrawal() {

				return (incomingAmount, initialBalance) -> {

						var isOperationUnauthorized = Boolean.TRUE == incomingAmount.isLargerThan()
						                                                            .apply(initialBalance);

						if (isOperationUnauthorized)
								throw new UnauthorizedOperationException("Unauthorized operation. Tried to withdraw an amount larger than account balance..");
				};

		}

}
