package com.sfeir.kata.bank.service;

import com.sfeir.kata.bank.domain.BankAccount;

public interface BankTransactionService {

	boolean deposit(String amount, BankAccount account);
}
