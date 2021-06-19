package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.printer.StatementPrinter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ClientOperationContext implements ClientOperation {

	private final Account account;

	private final StatementPrinter printer;

}
