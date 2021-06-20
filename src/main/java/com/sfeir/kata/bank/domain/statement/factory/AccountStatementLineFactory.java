package com.sfeir.kata.bank.domain.statement.factory;

import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.statement.AccountStatementLine;

import io.vavr.Function1;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountStatementLineFactory {

		public static AccountStatementLine
		    of(Operation operation) {

				return OperationReader.readOperationAsLine()
				                      .andThen(AccountStatementLine::new)
				                      .apply(operation);
		}

		@NoArgsConstructor(access = AccessLevel.PRIVATE)
		static class OperationReader {

				static Function1<Operation, String>
				    readOperationAsLine() {
						return operation -> String.format("|%s|%s|%s|%s|",
						                                  operation.getDate()
						                                           .toString(),
						                                  operation.getType(),
						                                  operation.getAmount(),
						                                  operation.getBalanceResult());
				}
		}
}
