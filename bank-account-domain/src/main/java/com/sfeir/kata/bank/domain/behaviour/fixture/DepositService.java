package com.sfeir.kata.bank.domain.behaviour.fixture;

import com.sfeir.kata.bank.domain.behaviour.account.AccountSpecification;
import com.sfeir.kata.bank.domain.behaviour.account.operation.Deposit;
import com.sfeir.kata.bank.domain.common.money.Money;

public class DepositService
    implements DepositFixtureSpecification {

		@Override
		public void deposit(Money amount,
		                    AccountSpecification account) {

				var deposit = Deposit.builder()
				                     .amount(amount)
				                     .initialBalance(account.getBalance()
				                                            .apply())
				                     .build();

				account.addOperation().accept(deposit);
		}
}
