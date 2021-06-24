package com.sfeir.kata.bank.domain.client.account.factory;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.operation.factory.OperationHistoryFactory;

import io.vavr.Function0;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountFactory {

		public static Function0<AccountService> createAccount() {

				return OperationHistoryFactory.initializeHistory()
				                              .andThen(Account::new);
		}
}
