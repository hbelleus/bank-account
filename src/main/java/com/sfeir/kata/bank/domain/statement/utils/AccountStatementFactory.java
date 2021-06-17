package com.sfeir.kata.bank.domain.statement.utils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.sfeir.kata.bank.domain.operation.OperationHistory;
import com.sfeir.kata.bank.domain.statement.AccountStatement;
import com.sfeir.kata.bank.domain.statement.AccountStatementLine;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountStatementFactory {

	public static AccountStatement createStatement(OperationHistory history) {

		List<AccountStatementLine> lines = history.getOperations().stream().sorted(Comparator.reverseOrder())
				.map(AccountStatementLineFactory::of).collect(Collectors.toUnmodifiableList());

		return new AccountStatement(lines);
	}
}
