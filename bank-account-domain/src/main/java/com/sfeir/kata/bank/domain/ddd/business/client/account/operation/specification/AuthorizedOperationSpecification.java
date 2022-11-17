package com.sfeir.kata.bank.domain.ddd.business.client.account.operation.specification;

import java.util.function.BiConsumer;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.specification.exception.UnauthorizedOperationException;

public interface AuthorizedOperationSpecification {

		static BiConsumer<Money, Money>
		    isWithdrawalAuthorized() {

				return (incomingAmount, initialBalance) -> {

						var isOperationUnauthorized = Boolean.TRUE == incomingAmount.isAbsolutelyMoreThan()
						                                                            .apply(initialBalance);

						if (isOperationUnauthorized)
								throw new UnauthorizedOperationException("Unauthorized operation. Tried to withdraw an amount larger than account balance..");
				};

		}

}
