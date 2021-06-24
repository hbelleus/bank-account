package com.sfeir.kata.bank.domain.client.account.operation.factory;

import com.sfeir.kata.bank.domain.client.account.operation.OperationType;
import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Deposit extends Operation {

		Deposit(MoneyService amount, MoneyService balance) {
				super.of(amount, balance.addMoney().apply(amount),
				         OperationType.DEPOSIT);
		}
}
