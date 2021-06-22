package com.sfeir.kata.bank.domain.client.factory;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.factory.BankAccountFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankClientFactory {

		public static ClientService create() {

				var account = BankAccountFactory.create();

				return Client.of(account, null);
		}

		public static ClientService
		    create(StatementPrinterService printer) {

				var account = BankAccountFactory.create();

				return Client.of(account, printer);
		}
}
