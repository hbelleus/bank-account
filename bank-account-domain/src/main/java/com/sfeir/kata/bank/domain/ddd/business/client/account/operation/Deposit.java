package com.sfeir.kata.bank.domain.ddd.business.client.account.operation;

import com.sfeir.kata.bank.domain.common.money.Money;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Deposit extends Operation {

		@Builder
		Deposit(Money amount, Money balance) {
				super.of(amount, balance.putMoney().apply(amount),
				         OperationType.DEPOSIT);
		}
}
