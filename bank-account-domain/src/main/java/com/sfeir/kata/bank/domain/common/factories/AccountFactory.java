package com.sfeir.kata.bank.domain.common.factories;

import com.sfeir.kata.bank.domain.ddd.business.client.account.Account;
import com.sfeir.kata.bank.domain.ddd.business.client.account.AccountOperationService;
import com.sfeir.kata.bank.domain.ddd.reporting.client.account.AccountReportingService;

import io.vavr.Function0;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountFactory {

		public static Function0<AccountOperationService>
		    createAccountForOperation() {
				return Account::new;
		}

		public static Function0<AccountReportingService>
		    createAccountForReporting() {
				return Account::new;
		}
}
