package com.sfeir.kata.bank.domain.bddfriendly.account.statement;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountStatementLine
    implements AccountStatementLineSpecification {

		String type;

		String amount;

		String balance;

		String date;

		@Override
		public String toString() {
				return String.format(System.lineSeparator()
				                           .concat("|%s |%s     |%s        |%s   |"),
				                     this.type, this.amount,
				                     this.date, this.balance);
		}
}
