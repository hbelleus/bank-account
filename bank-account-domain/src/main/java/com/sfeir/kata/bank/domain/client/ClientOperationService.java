package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.money.MoneyService;

import io.vavr.Function1;

public interface ClientOperationService {

		AccountService getAccount();

		default Function1<MoneyService, Boolean> deposit() {
				return this.getAccount().deposit();
		}

		default Function1<MoneyService, Boolean> withdraw() {
				return this.getAccount().withdraw();
		}
}