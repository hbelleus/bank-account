package com.sfeir.kata.bank.domain.statement.utils;

import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.statement.AccountStatementLine;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountStatementLineFactory {

	public static AccountStatementLine of(Operation operation) {

		var line = OperationReader.readOperationAsLine().apply(operation);

		return new AccountStatementLine(line);
	}

}
