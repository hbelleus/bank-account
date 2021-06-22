package com.sfeir.kata.bank.domain.client.account.statement.factory;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.collector.Collectors2;

import com.sfeir.kata.bank.domain.client.account.operation.OperationHistoryService;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.account.statement.line.AccountStatementLineService;
import com.sfeir.kata.bank.domain.client.account.statement.line.factory.AccountStatementLineFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountStatementFactory {

		public static AccountStatementService
		    createStatement(OperationHistoryService history) {

				ImmutableList<AccountStatementLineService> lines = history.getOperations()
				                                                          .toReversed()
				                                                          .stream()
				                                                          .map(AccountStatementLineFactory::of)
				                                                          .collect(Collectors2.toImmutableList());

				return new AccountStatement(lines);
		}
}
