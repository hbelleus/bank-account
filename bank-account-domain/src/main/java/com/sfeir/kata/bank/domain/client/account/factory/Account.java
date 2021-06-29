package com.sfeir.kata.bank.domain.client.account.factory;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.operation.history.factory.OperationHistory;
import com.sfeir.kata.bank.domain.client.account.operation.history.factory.OperationHistoryFactory;

import lombok.Value;

@Value
class Account implements AccountService {

		OperationHistory history;

		Account() {
				this.history = OperationHistoryFactory.initializeHistory()
				                                      .apply();
		}

}
