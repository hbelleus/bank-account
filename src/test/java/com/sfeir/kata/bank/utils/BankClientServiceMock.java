package com.sfeir.kata.bank.utils;

import com.sfeir.kata.bank.domain.BankClient;
import com.sfeir.kata.bank.service.BankAccountService;
import com.sfeir.kata.bank.service.BankClientService;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class BankClientServiceMock implements BankClientService {

	@NonNull
	private BankAccountService accountService;

	@Override
	public BankClient getClient() {
		return BankClient.builder().firstName("Hattmann").lastName("Belleus").email("belleus.h@sfeir.com")
				.account(accountService.getBankAccount()).build();
	}
}
