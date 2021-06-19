package com.sfeir.kata.bank.domain.account;

import com.sfeir.kata.bank.domain.operation.OperationHistory;

import lombok.Value;

@Value
public class Account implements AccountBalance {

	private OperationHistory history;

}
