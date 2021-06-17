package com.sfeir.kata.bank.domain.statement;

import java.util.List;

import lombok.Value;

@Value
public class AccountStatement {

	List<AccountStatementLine> lines;
}
