package com.sfeir.kata.bank.domain.bddfriendly.service;

import com.sfeir.kata.bank.domain.bddfriendly.account.AccountSpecification;
import com.sfeir.kata.bank.domain.bddfriendly.account.operation.Deposit;
import com.sfeir.kata.bank.domain.common.money.Money;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DepositService {

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
