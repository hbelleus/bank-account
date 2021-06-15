package com.sfeir.kata.bank.domain;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class BankAccount {

	@Setter
	private String balance;
	private String overdraft;
	private List<BankTransaction> history;

	public boolean addTransaction(BankTransaction transaction) {
		return this.history.add(transaction);
	}
}
