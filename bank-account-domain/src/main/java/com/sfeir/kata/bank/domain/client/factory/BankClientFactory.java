package com.sfeir.kata.bank.domain.client.factory;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.factory.AccountFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankClientFactory {

		public static ClientService createClient() {

				return Client.of(AccountFactory.createAccount()
				                               .apply(),
				                 null);
		}

		public static ClientService
		    createClient(StatementPrinterService printer) {

				return Client.of(AccountFactory.createAccount()
				                               .apply(),
				                 printer);
		}
}
