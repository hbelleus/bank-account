package com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement.line;

public interface AccountStatementLineService {

	String getType();
	
	String getAmount();
	
	String getBalance();
	
	String getDate();
}
