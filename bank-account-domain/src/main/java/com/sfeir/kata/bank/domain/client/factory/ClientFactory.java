package com.sfeir.kata.bank.domain.client.factory;

import com.sfeir.kata.bank.domain.client.ClientOperationService;
import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.Account;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientFactory {

		public static ClientOperationService createClient() {

				return Client.builder()
				             .account(new Account())
				             .build();

		}

		public static ClientService
		    createClient(StatementPrinterService printer) {

				return Client.builder()
				             .account(new Account())
				             .printer(printer)
				             .build();
		}
}
