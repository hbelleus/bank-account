package com.sfeir.kata.bank.domain.statement;

public interface IAccountStatementLine {

	String getType();
	
	String getAmount();
	
	String getBalance();
	
	String getDate();
}
