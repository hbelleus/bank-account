package com.sfeir.kata.bank.domain;

public interface BankTransactionExecution extends BankTransactionCalculation {

	default String run(String amount, String balance) {
		return this.balanceCalculation().apply(balance, amount);
	}

}
