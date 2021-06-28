package com.sfeir.kata.bank.domain.client.factory;

import com.sfeir.kata.bank.domain.client.ClientOperationService;
import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.factory.AccountFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientFactory {

	public static ClientOperationService createClient() {

		return Client.builder()
				     .account(AccountFactory.createAccount()
				    		                .apply())
				     .build();
	}

	public static ClientService createClient(StatementPrinterService printer) {

		return Client.builder()
			     .account(AccountFactory.createAccount()
			    		                .apply())
			     .printer(printer)
			     .build();	
	}
}
