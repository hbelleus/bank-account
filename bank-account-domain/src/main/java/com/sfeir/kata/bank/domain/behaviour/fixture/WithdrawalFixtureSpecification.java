package com.sfeir.kata.bank.domain.behaviour.fixture;

import com.sfeir.kata.bank.domain.behaviour.account.AccountSpecification;
import com.sfeir.kata.bank.domain.common.money.Money;

public interface WithdrawalFixtureSpecification {

		void withdraw(Money amount,
		              AccountSpecification account);
}
