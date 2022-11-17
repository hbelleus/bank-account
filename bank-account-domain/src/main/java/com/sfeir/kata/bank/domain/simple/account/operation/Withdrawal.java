package com.sfeir.kata.bank.domain.simple.account.operation;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.simple.account.operation.specification.AuthorizedOperationSpecification;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Withdrawal extends Operation {

		@Builder
		Withdrawal(Money amount, Money balance) {

				AuthorizedOperationSpecification.isWithdrawalAuthorized()
				                                .accept(amount,
				                                        balance);

				var effectiveAmount = amount.toNegative().apply();
				var balanceAfterWithdrawal = balance.retrieveMoney()
				                                    .apply(amount);

				super.of(effectiveAmount, balanceAfterWithdrawal,
				         OperationType.WITHDRAWAL);
		}
}
