package com.sfeir.kata.bank.domain.account;

import com.sfeir.kata.bank.domain.operation.Money;
import com.sfeir.kata.bank.domain.operation.OperationHistory;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Account {

	@Setter
	private Money balance;
	private OperationHistory history;

}
