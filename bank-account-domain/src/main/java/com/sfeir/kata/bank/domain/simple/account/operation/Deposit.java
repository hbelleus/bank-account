package com.sfeir.kata.bank.domain.simple.account.operation;

import com.sfeir.kata.bank.domain.common.money.Money;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Deposit extends Operation {

		@Builder
		Deposit(Money amount, Money balance) {

				var balanceAfterDeposit = balance.putMoney()
				                                 .apply(amount);

				super.of(amount, balanceAfterDeposit,
				         OperationType.DEPOSIT);
		}
}
