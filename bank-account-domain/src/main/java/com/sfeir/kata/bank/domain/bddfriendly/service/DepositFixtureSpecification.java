package com.sfeir.kata.bank.domain.bddfriendly.service;

import com.sfeir.kata.bank.domain.bddfriendly.account.AccountSpecification;
import com.sfeir.kata.bank.domain.common.money.Money;

public interface DepositFixtureSpecification {

		void deposit(Money amount,
		             AccountSpecification account);
}
