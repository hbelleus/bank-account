package com.sfeir.kata.bank.domain.behaviour.fixture;

import com.sfeir.kata.bank.domain.behaviour.account.AccountSpecification;
import com.sfeir.kata.bank.domain.behaviour.account.operation.Withdrawal;
import com.sfeir.kata.bank.domain.common.money.Money;

public class WithdrawalService
    implements WithdrawalFixtureSpecification {

		@Override
		public void withdraw(Money amount,
		                     AccountSpecification account) {

				var deposit = Withdrawal.builder()
				                        .amount(amount)
				                        .initialBalance(account.getBalance()
				                                               .apply())
				                        .build();

				account.addOperation().accept(deposit);
		}
}
