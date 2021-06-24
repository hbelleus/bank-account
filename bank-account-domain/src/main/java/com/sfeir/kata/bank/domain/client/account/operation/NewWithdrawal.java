package com.sfeir.kata.bank.domain.client.account.operation;

import com.sfeir.kata.bank.domain.client.account.operation.specification.AuthorizedOperationSpecification;
import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class NewWithdrawal extends NewOperation {

		NewWithdrawal(MoneyService amount,
		              MoneyService balance) {

				AuthorizedOperationSpecification.validateWithdrawal()
				                                .accept(amount,
				                                        balance);

				super.of(amount.toNegative().apply(),
				         balance.retrieveMoney().apply(amount),
				         OperationType.WITHDRAWAL);
		}
}
