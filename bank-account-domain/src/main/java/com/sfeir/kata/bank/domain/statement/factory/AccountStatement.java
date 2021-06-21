package com.sfeir.kata.bank.domain.statement.factory;

import org.eclipse.collections.api.list.ImmutableList;

import com.sfeir.kata.bank.domain.statement.IAccountStatement;
import com.sfeir.kata.bank.domain.statement.IAccountStatementLine;

import lombok.Value;

@Value
class AccountStatement implements IAccountStatement {

		ImmutableList<IAccountStatementLine> lines;
}
