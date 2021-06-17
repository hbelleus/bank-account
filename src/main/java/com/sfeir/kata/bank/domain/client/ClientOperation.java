package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.money.Money;

public interface ClientOperation {

	Account getAccount();

	boolean deposit(Money amount);

	boolean withdrawal(Money amount);

	void printOperationHistory();
}
