package com.sfeir.kata.bank.domain.account;

import com.sfeir.kata.bank.domain.operation.factory.OperationHistory;

import lombok.Value;

@Value
public class Account implements IAccountOperator {

		private OperationHistory history;

}
