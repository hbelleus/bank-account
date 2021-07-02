package com.sfeir.kata.bank.infra.printer.logger;

import java.util.Map;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.factory.ClientFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoggerPrinter implements ILoggerPrinter {

		@Override
		public void print(AccountStatementService statement) {

				statement.getLines().forEach(log::warn);

		}

		public static void main(String[] args) {

				StatementPrinterService printer = new LoggerPrinter();

				ClientService client = ClientFactory.createClientForPrinting()
				                                    .apply(printer);

				Map.of(MoneyFactory.create(10000), client.deposit(),
				       MoneyFactory.create(7000), client.withdraw(),
				       MoneyFactory.create(3000), client.withdraw(),
				       MoneyFactory.create(15000), client.deposit())
				   .entrySet()
				   .stream()
				   .forEach(actions -> actions.getValue()
				                              .accept(actions.getKey()));

				client.printOperationHistory();

		}
}
