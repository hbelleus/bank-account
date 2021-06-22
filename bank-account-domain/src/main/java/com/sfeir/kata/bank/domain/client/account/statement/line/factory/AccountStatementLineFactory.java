package com.sfeir.kata.bank.domain.client.account.statement.line.factory;

import com.sfeir.kata.bank.domain.client.account.operation.Operation;
import com.sfeir.kata.bank.domain.client.account.statement.line.AccountStatementLineService;

import io.vavr.Function1;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountStatementLineFactory {

		public static AccountStatementLineService
		    of(Operation operation) {

				return OperationReader.readOperationAsLine()
				                      .apply(operation);
		}

		@NoArgsConstructor(access = AccessLevel.PRIVATE)
		static class OperationReader {

				static Function1<Operation, AccountStatementLineService>
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
