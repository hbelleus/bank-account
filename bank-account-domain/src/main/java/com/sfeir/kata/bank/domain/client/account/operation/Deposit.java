package com.sfeir.kata.bank.domain.client.account.operation;

import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Deposit extends Operation {

		@Builder
		Deposit(MoneyService amount, MoneyService balance) {
				super.of(amount, balance.putMoney().apply(amount),
				         OperationType.DEPOSIT);
		}
}
