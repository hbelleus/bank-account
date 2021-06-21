package com.sfeir.kata.bank.domain.statement;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountStatementLine implements IAccountStatementLine {

	String type;

	String amount;

	String balance;

	String date;

}
