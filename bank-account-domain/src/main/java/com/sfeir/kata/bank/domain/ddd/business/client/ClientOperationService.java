package com.sfeir.kata.bank.domain.ddd.business.client;

import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.AccountOperationService;

public interface ClientOperationService {

		AccountOperationService getAccount();

		default Consumer<Money> deposit() {
				return this.getAccount().deposit();
		}

		default Consumer<Money> withdraw() {
				return this.getAccount().withdraw();
		}
}