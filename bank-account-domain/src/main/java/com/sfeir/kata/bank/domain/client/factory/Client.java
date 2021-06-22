package com.sfeir.kata.bank.domain.client.factory;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.printer.StatementPrinterService;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
class Client implements ClientService {

		private final AccountService account;

		private final StatementPrinterService printer;

}
