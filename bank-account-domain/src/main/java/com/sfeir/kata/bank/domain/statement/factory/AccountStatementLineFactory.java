package com.sfeir.kata.bank.domain.statement.factory;

import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.statement.IAccountStatementLine;

import io.vavr.Function1;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountStatementLineFactory {

		public static IAccountStatementLine
		    of(Operation operation) {

				return OperationReader.readOperationAsLine()
				                      .apply(operation);
		}

		@NoArgsConstructor(access = AccessLevel.PRIVATE)
		static class OperationReader {

				static Function1<Operation, IAccountStatementLine>
				    readOperationAsLine() {
						return operation -> AccountStatementLine.builder()
																.amount(operation.getAmount().toString())
																.balance(operation.getBalanceResult().toString())
																.date(operation.getDate().toString())
																.type(operation.getType().name())
																.build();
				}
		}
}
