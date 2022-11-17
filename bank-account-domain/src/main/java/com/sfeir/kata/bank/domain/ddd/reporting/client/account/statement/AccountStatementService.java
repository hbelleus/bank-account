package com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement;

import org.eclipse.collections.api.list.ImmutableList;

import com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement.line.AccountStatementLineService;

public interface AccountStatementService {

		ImmutableList<AccountStatementLineService> getLines();
		
		
}
