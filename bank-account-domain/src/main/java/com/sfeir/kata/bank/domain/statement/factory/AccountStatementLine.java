package com.sfeir.kata.bank.domain.statement.factory;

import com.sfeir.kata.bank.domain.statement.IAccountStatementLine;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class AccountStatementLine implements IAccountStatementLine {

	String type;

	String amount;

	String balance;

	String date;

}
