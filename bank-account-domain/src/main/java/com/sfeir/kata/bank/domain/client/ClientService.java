package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.money.MoneyService;

import io.vavr.Function1;
import io.vavr.control.Try;

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

				Try.of(() -> this.getAccount()
				                 .generateStatement()
				                 .apply())
				   .onSuccess(this.getPrinter()::print);
		}
}