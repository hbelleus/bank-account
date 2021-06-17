package com.sfeir.kata.bank.utils;

import com.sfeir.kata.bank.domain.client.Client;
import com.sfeir.kata.bank.domain.client.ClientOperation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankClientFactory {

	public static ClientOperation create() {

		var account = BankAccountFactory.create();

		return Client.builder().account(account).build();
	}
}
