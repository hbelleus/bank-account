package com.sfeir.kata.bank.domain.client.account.operation;

import com.sfeir.kata.bank.domain.client.account.operation.specification.AuthorizedOperationSpecification;
import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Withdrawal extends Operation {

		@Builder
		Withdrawal(MoneyService amount, MoneyService balance) {

				AuthorizedOperationSpecification.isWithdrawalAuthorized()
				                                .accept(amount,
				                                        balance);

				super.of(amount.toNegative().apply(),
				         balance.retrieveMoney().apply(amount),
				         OperationType.WITHDRAWAL);
		}
}
