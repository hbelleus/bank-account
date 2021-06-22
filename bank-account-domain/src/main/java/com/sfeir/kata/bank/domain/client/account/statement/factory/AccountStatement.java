package com.sfeir.kata.bank.domain.client.account.statement.factory;

import org.eclipse.collections.api.list.ImmutableList;

import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.account.statement.line.AccountStatementLineService;

import lombok.Value;

@Value
class AccountStatement implements AccountStatementService {

		ImmutableList<AccountStatementLineService> lines;
}
