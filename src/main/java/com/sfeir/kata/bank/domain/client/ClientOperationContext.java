package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class ClientOperationContext
    implements IClientOperatior {

		private final IAccountOperator account;

		private final IStatementPrinter printer;

}
