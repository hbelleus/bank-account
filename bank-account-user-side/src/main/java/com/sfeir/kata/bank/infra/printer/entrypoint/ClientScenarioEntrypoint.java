package com.sfeir.kata.bank.infra.printer.entrypoint;

import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;
import com.sfeir.kata.bank.domain.simple.ClientService;
import com.sfeir.kata.bank.domain.simple.factory.ClientFactory;
import com.sfeir.kata.bank.domain.simple.printer.AccountStatementPrinterService;
import com.sfeir.kata.bank.infra.printer.console.ConsolePrinter;

import lombok.Value;

@Value
public class ClientScenarioEntrypoint {

		private static AccountStatementPrinterService statementConsolePrinter = new ConsolePrinter(System.out);

		ClientService client;

		ClientScenarioEntrypoint(AccountStatementPrinterService printer) {

				this.client = ClientFactory.createClientForPrinting()
				                           .apply(printer);
		}

		public static void main(String[] args) {

				var clientScenario = new ClientScenarioEntrypoint(statementConsolePrinter);

				clientScenario.getClient()
				              .deposit()
				              .accept(MoneyFactory.create(10000));

				clientScenario.getClient()
				              .withdraw()
				              .accept(MoneyFactory.create(7000));

				clientScenario.getClient()
				              .withdraw()
				              .accept(MoneyFactory.create(3000));

				clientScenario.getClient()
				              .deposit()
				              .accept(MoneyFactory.create(15000));

				clientScenario.getClient().printOperationHistory();

		}

}
