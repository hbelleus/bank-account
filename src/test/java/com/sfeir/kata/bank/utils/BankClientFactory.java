package com.sfeir.kata.bank.utils;

import com.sfeir.kata.bank.domain.BankClient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankClientFactory {

	public static BankClient create() {

		final var accountService = new BankAccountServiceMock();
		final var clientService = new BankClientServiceMock(accountService);

		return clientService.getClient();
	}
}
