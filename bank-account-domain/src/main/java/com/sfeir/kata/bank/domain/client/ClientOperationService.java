package com.sfeir.kata.bank.domain.client;

import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.money.MoneyService;

public interface ClientOperationService {

		AccountService getAccount();

		default Consumer<MoneyService> deposit() {
				return this.getAccount().deposit();
		}

		default Consumer<MoneyService> withdraw() {
				return this.getAccount().withdraw();
		}
}