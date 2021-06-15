package com.sfeir.kata.bank.utils;

import java.util.ArrayList;

import com.sfeir.kata.bank.domain.BankAccount;
import com.sfeir.kata.bank.service.BankAccountService;

class BankAccountServiceMock implements BankAccountService {

	@Override
	public BankAccount getBankAccount() {
		return BankAccount.builder().balance("500").overdraft("0").history(new ArrayList<>()).build();
	}

}
