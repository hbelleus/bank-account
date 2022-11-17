package com.sfeir.kata.bank.domain.behaviour.fixture;

import com.sfeir.kata.bank.domain.behaviour.account.AccountSpecification;
import com.sfeir.kata.bank.domain.common.money.Money;

public interface DepositFixtureSpecification {

		void deposit(Money amount,
		             AccountSpecification account);
}
