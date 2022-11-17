package com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement.factory;

import org.eclipse.collections.api.list.ImmutableList;

import com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement.line.AccountStatementLineService;

import lombok.Value;

@Value
class AccountStatement implements AccountStatementService {

		ImmutableList<AccountStatementLineService> lines;
}
