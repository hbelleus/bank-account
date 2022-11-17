package com.sfeir.kata.bank.domain.simple.account.statement;

import com.sfeir.kata.bank.domain.simple.account.operation.Operation;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AccountStatementLine {

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

		public static AccountStatementLine
		    of(Operation operation) {
				return AccountStatementLine.builder()
				                           .amount(operation.getAmount()
				                                            .toString())
				                           .balance(operation.getBalance()
				                                             .toString())
				                           .date(operation.getDate()
				                                          .toString())
				                           .type(operation.getType()
				                                          .name())
				                           .build();
		}

}
