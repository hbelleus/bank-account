package com.sfeir.kata.bank.domain.bddfriendly.account.operation;

import com.sfeir.kata.bank.domain.bddfriendly.account.operation.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.common.money.Money;

public interface WithdrawalSpecification
    extends OperationSpecification {

		Money getInitialBalance();

		default boolean isWithdrawalAuthorized() {

				var isOperationUnauthorized = Boolean.TRUE == this.getAmount()
				                                                  .isAbsolutelyMoreThan()
				                                                  .apply(this.getInitialBalance());

				if (isOperationUnauthorized)
						throw new UnauthorizedOperationException("Unauthorized operation. Tried to withdraw an amount larger than account balance..");
				else
						return true;
		}

		default Boolean isWithdrawalValid(Money amount) {
				return amount.isLessThan().apply(Money.zero());
		}

}
