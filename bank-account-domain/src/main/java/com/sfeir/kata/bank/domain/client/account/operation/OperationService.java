package com.sfeir.kata.bank.domain.client.account.operation;

import com.sfeir.kata.bank.domain.client.account.operation.date.OperationDateService;
import com.sfeir.kata.bank.domain.money.MoneyService;

public interface OperationService {
	
	MoneyService getAmount();
	
	MoneyService getBalance();
	
	OperationDateService getDate();
	
	OperationType getType();
}
