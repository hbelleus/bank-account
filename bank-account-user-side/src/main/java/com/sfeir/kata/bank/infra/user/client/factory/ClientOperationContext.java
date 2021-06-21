package com.sfeir.kata.bank.infra.user.client.factory;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;
import com.sfeir.kata.bank.infra.user.client.IClientOperator;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
class ClientOperationContext
    implements IClientOperator {

		private final IAccountOperator account;

		private final IStatementPrinter printer;

}
