package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.operation.money.Money;

public interface ClientOperation {

	Account getAccount();

	boolean deposit(Money amount);
}
