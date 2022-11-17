package com.sfeir.kata.bank.domain.bddfriendly.account.operation;

import com.sfeir.kata.bank.domain.common.money.Money;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class Withdrawal extends Operation
    implements WithdrawalSpecification {

		Money initialBalance;

		@Builder
		Withdrawal(Money amount, Money initialBalance) {

				this.initialBalance = initialBalance;
				this.amount         = amount;

				this.isWithdrawalAuthorized();

				var effectiveAmount = amount.toNegative().apply();
				var balanceAfterWithdrawal = initialBalance.retrieveMoney()
				                                           .apply(amount);

				this.isWithdrawalValid(effectiveAmount);

				super.of(effectiveAmount, balanceAfterWithdrawal,
				         OperationType.WITHDRAWAL);
		}

}
