package com.sfeir.kata.bank.domain.client.account;

import com.sfeir.kata.bank.domain.client.account.operation.history.factory.OperationHistory;
import com.sfeir.kata.bank.domain.client.account.operation.history.factory.OperationHistoryFactory;

import lombok.Value;

@Value
public class Account implements AccountService {

		OperationHistory history;

		public Account() {
				this.history = OperationHistoryFactory.initializeHistory()
				                                      .apply();
		}

}
