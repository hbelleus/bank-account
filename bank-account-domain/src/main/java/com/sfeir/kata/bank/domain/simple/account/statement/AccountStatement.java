package com.sfeir.kata.bank.domain.simple.account.statement;

import org.eclipse.collections.api.list.ImmutableList;

import lombok.Value;

@Value
public class AccountStatement {

		ImmutableList<AccountStatementLine> lines;

		@Override
		public String toString() {
				var header = "|OPERATION |AMOUNT     |DATE        |NEW BALANCE   |";

				var content = lines.stream()
				                   .map(AccountStatementLine::toString)
				                   .reduce((x, y) -> x.concat(y))
				                   .orElse("");

				return header.concat(content);
		}
}
