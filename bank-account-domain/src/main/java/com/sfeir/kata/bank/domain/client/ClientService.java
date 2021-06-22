package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.MoneyService;

import io.vavr.Function1;

public interface ClientService {

		AccountService getAccount();

		StatementPrinterService getPrinter();

		default Function1<MoneyService, Boolean> deposit() {
				return this.getAccount().deposit();
		}

		default Function1<MoneyService, Boolean> withdraw() {
				return this.getAccount().withdraw();
		}

		default void printOperationHistory() {

				this.getPrinter()
				    .print(this.getAccount()
				               .generateStatement()
				               .apply());
		}
}