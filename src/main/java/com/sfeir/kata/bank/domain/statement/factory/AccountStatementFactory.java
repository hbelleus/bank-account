package com.sfeir.kata.bank.domain.statement.factory;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.collector.Collectors2;

import com.sfeir.kata.bank.domain.operation.factory.OperationHistory;
import com.sfeir.kata.bank.domain.statement.AccountStatement;
import com.sfeir.kata.bank.domain.statement.AccountStatementLine;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountStatementFactory {

		public static AccountStatement
		    createStatement(OperationHistory history) {

				ImmutableList<AccountStatementLine> lines = history.getOperations()
				                                                   .toReversed()
				                                                   .stream()
				                                                   .map(AccountStatementLineFactory::of)
				                                                   .collect(Collectors2.toImmutableList());

				return new AccountStatement(lines);
		}
}
