package com.sfeir.kata.bank.infra.printer.console;

import java.io.PrintStream;
import java.util.Map;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.factory.ClientFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import lombok.Value;

@Value
public class ConsolePrinter implements IConsolePrinter, IConsoleFormatter {

	PrintStream printer;

	@Override
	public void print(AccountStatementService statement) {

		this.consolePrint().accept(STATEMENT_HEADER);

		statement.getLines().stream().map(this.format()).forEach(consolePrint());
	}

	public static void main(String[] args) {

		StatementPrinterService printer = new ConsolePrinter(System.out);

		ClientService client = ClientFactory.createClient(printer);

		Map.of(MoneyFactory.create(10000), client.deposit(), MoneyFactory.create(7000), client.withdraw(),
				MoneyFactory.create(3000), client.withdraw(), MoneyFactory.create(10000), client.deposit()).entrySet()
				.stream().forEach(actions -> actions.getValue().accept(actions.getKey()));

		client.printOperationHistory();

	}

}
