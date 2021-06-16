package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.operation.Money;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client implements ClientOperation {

	private Account account;

	@Override
	public boolean deposit(Money amount) {

		return false;

	}
}
