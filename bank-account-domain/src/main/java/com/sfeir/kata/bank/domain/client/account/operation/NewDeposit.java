package com.sfeir.kata.bank.domain.client.account.operation;

import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class NewDeposit extends NewOperation {

		NewDeposit(MoneyService amount, MoneyService balance) {
				super.of(amount, balance.addMoney().apply(amount),
				         OperationType.DEPOSIT);
		}
}
