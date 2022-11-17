package com.sfeir.kata.bank.domain.bddfriendly.service;

import com.sfeir.kata.bank.domain.bddfriendly.account.AccountSpecification;
import com.sfeir.kata.bank.domain.bddfriendly.account.operation.Withdrawal;
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
