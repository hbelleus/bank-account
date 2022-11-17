package com.sfeir.kata.bank.domain.common.factories;

import com.sfeir.kata.bank.domain.ddd.business.client.ClientOperation;
import com.sfeir.kata.bank.domain.ddd.business.client.ClientOperationService;
import com.sfeir.kata.bank.domain.ddd.reporting.client.ClientReporting;
import com.sfeir.kata.bank.domain.ddd.reporting.client.ClientReportingService;
import com.sfeir.kata.bank.domain.ddd.reporting.client.account.printer.StatementPrinterService;

import io.vavr.Function0;
import io.vavr.Function1;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientFactory {

		public static Function0<ClientOperationService>
		    createClientForOperation() {

				return () -> ClientOperation.builder()
				                            .account(AccountFactory.createAccountForOperation()
				                                                   .apply())
				                            .build();

		}

		public static
		    Function1<StatementPrinterService, ClientReportingService>
		    createClientForPrinting() {

				return printer -> ClientReporting.builder()
				                                 .account(AccountFactory.createAccountForReporting()
				                                                        .apply())
				                                 .printer(printer)
				                                 .build();
		}
}
