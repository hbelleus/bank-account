package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ClientOperationContext
    implements IClientOperation {

		private final Account account;

		private final IStatementPrinter printer;

}
