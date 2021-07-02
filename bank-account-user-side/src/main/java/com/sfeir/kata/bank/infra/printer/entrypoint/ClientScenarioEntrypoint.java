package com.sfeir.kata.bank.infra.printer.entrypoint;

import java.util.Map;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.factory.ClientFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;
import com.sfeir.kata.bank.infra.printer.console.ConsolePrinter;

import lombok.Value;

@Value
public class ClientScenarioEntrypoint {

		private static StatementPrinterService statementConsolePrinter = new ConsolePrinter(System.out);

		ClientService client;

		ClientScenarioEntrypoint(ClientService client) {
				this.client = client;
		}

		public static void main(String[] args) {

				ClientService client = ClientFactory.createClientForPrinting()
				                                    .apply(statementConsolePrinter);

				Map.of(MoneyFactory.create(10000), client.deposit(),
				       MoneyFactory.create(7000), client.withdraw(),
				       MoneyFactory.create(3000), client.withdraw(),
				       MoneyFactory.create(15000), client.deposit())
				   .entrySet()
				   .stream()
				   .forEach(actions -> actions.getValue()
				                              .accept(actions.getKey()));

				var clientScenario = new ClientScenarioEntrypoint(client);

				clientScenario.getClient().printOperationHistory();

		}

}
