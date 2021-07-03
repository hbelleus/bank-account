package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;

import io.vavr.control.Try;

public interface ClientPrintingService {

		AccountService getAccount();

		StatementPrinterService getPrinter();

		default void printOperationHistory() {

				Try.of(() -> this.getAccount()
				                 .generateStatement()
				                 .apply())
				   .andThen(this.getPrinter()::print);
		}
}