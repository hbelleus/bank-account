package com.sfeir.kata.bank.domain.ddd.business.client.account.operation;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.specification.AuthorizedOperationSpecification;

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

				super.of(amount.toNegative().apply(),
				         balance.retrieveMoney().apply(amount),
				         OperationType.WITHDRAWAL);
		}
}
