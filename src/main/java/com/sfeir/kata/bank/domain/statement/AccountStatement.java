package com.sfeir.kata.bank.domain.statement;

import org.eclipse.collections.api.list.ImmutableList;

import lombok.Value;

@Value
public class AccountStatement {

		ImmutableList<AccountStatementLine> lines;
}
