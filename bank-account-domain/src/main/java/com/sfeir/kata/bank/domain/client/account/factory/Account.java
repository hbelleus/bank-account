package com.sfeir.kata.bank.domain.client.account.factory;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.operation.factory.OperationHistory;

import lombok.Value;

@Value
class Account implements AccountService {

		private OperationHistory history;

}
