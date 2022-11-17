package com.sfeir.kata.bank.domain.bddfriendly.service;

import com.sfeir.kata.bank.domain.bddfriendly.account.AccountSpecification;
import com.sfeir.kata.bank.domain.common.money.Money;

public interface WithdrawalFixtureSpecification {

		void withdraw(Money amount,
		              AccountSpecification account);
}
