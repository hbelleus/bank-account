package com.sfeir.kata.bank.domain.ddd.business.client.account;

import com.sfeir.kata.bank.domain.common.money.Money;

import io.vavr.Function0;

public interface AccountService {

		Function0<Money> getBalance();
}
