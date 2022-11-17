package com.sfeir.kata.bank.domain.behaviour.account.operation;

import com.sfeir.kata.bank.domain.common.money.Money;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Deposit extends Operation
    implements DepositSpecification {

		@Builder
		Deposit(Money amount, Money initialBalance) {

				var balanceAfterDeposit = initialBalance.putMoney()
				                                        .apply(amount);

				this.isDepositValid(amount);

				super.of(amount, balanceAfterDeposit,
				         OperationType.DEPOSIT);
		}
}
