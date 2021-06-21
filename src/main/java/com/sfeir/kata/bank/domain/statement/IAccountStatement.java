package com.sfeir.kata.bank.domain.statement;

import org.eclipse.collections.api.list.ImmutableList;

public interface IAccountStatement {

	ImmutableList<IAccountStatementLine> getLines();
}
