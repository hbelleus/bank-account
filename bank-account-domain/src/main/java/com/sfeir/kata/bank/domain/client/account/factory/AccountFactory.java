package com.sfeir.kata.bank.domain.client.account.factory;

import com.sfeir.kata.bank.domain.client.account.AccountService;

import io.vavr.Function0;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountFactory {

		public static Function0<AccountService>
		    createAccount() {
				return Account::new;
		}
}
