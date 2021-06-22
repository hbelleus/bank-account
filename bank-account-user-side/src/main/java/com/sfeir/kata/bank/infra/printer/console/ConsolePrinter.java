package com.sfeir.kata.bank.infra.printer.console;

import java.io.PrintStream;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.factory.BankClientFactory;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

import lombok.Value;

@Value
public class ConsolePrinter
    implements IConsolePrinter, IConsoleFormatter {

		PrintStream printer;

		@Override
		public void print(AccountStatementService statement) {

				this.consolePrint().accept(STATEMENT_HEADER);

				statement.getLines()
				         .stream()
				         .map(this.format())
				         .forEach(consolePrint());
		}

		public static void main(String[] args) {

				StatementPrinterService printer = new ConsolePrinter(System.out);

				ClientService client = BankClientFactory.create(printer);

				client.deposit()
				      .apply(BankMoneyFactory.create(10000));
				client.withdraw()
				      .apply(BankMoneyFactory.create(7000));

				client.withdraw()
				      .apply(BankMoneyFactory.create(3000));
				client.deposit()
				      .apply(BankMoneyFactory.create(10000));

				client.printOperationHistory();

		}

}
