package com.sfeir.kata.bank.domain.client.account.statement.factory;

import java.util.stream.Collectors;

import org.eclipse.collections.impl.collector.Collectors2;

import com.sfeir.kata.bank.domain.client.account.operation.history.OperationHistoryService;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.account.statement.line.factory.AccountStatementLineFactory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountStatementFactory {

		public static AccountStatementService
		    createStatement(OperationHistoryService history) {

				return history.reverseLastOperationFirst()
				              .apply()
				              .stream()
				              .map(AccountStatementLineFactory::of)
				              .collect(Collectors.collectingAndThen(Collectors2.toImmutableList(),
				                                                    AccountStatement::new));

		}
}
