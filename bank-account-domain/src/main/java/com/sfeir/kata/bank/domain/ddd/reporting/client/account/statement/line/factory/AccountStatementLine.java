package com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement.line.factory;

import com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement.line.AccountStatementLineService;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class AccountStatementLine implements AccountStatementLineService {

	String type;

	String amount;

	String balance;

	String date;

}
