package com.sfeir.kata.bank.domain.statement.utils;

import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.statement.AccountStatementLine;

import io.vavr.Function0;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountStatementLineFactory {

	public static AccountStatementLine of(Operation operation) {

		Function0<String> toValue = operation::toString;

		return new AccountStatementLine(toValue.apply());
	}
}
