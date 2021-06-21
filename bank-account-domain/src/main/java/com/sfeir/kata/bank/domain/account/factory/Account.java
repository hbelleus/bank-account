package com.sfeir.kata.bank.domain.account.factory;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.operation.OperationHistory;

import lombok.Value;

@Value
class Account implements IAccountOperator {

		private OperationHistory history;

}
