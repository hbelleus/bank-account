package com.sfeir.kata.bank.domain.client.factory;

import com.sfeir.kata.bank.domain.client.ClientOperationService;
import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.factory.AccountFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;

import io.vavr.Function0;
import io.vavr.Function1;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientFactory {

		public static Function0<ClientOperationService>
		    createClientForOperation() {

				return () -> Client.builder()
				                   .account(AccountFactory.createAccount()
				                                          .apply())
				                   .build();

		}

		public static
		    Function1<StatementPrinterService, ClientService>
		    createClientForPrinting() {

				return printer -> Client.builder()
				                        .account(AccountFactory.createAccount()
				                                               .apply())
				                        .printer(printer)
				                        .build();
		}
}
