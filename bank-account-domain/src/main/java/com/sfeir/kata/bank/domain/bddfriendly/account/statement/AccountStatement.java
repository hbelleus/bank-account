package com.sfeir.kata.bank.domain.bddfriendly.account.statement;

import java.util.List;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.impl.collector.Collectors2;

import com.sfeir.kata.bank.domain.bddfriendly.account.operation.OperationSpecification;

import io.vavr.Function1;
import lombok.Value;

@Value
public class AccountStatement
    implements AccountStatementSpecification {

		ImmutableList<AccountStatementLineSpecification> lines;

		public AccountStatement(List<OperationSpecification> operations) {
				super();
				this.lines = this.statementOfOperations()
				                 .apply(operations);
		}

		@Override
		public String toString() {
				var header = "|OPERATION |AMOUNT     |DATE        |NEW BALANCE   |";

				var content = lines.stream()
				                   .map(AccountStatementLineSpecification::toString)
				                   .reduce((x, y) -> x.concat(y))
				                   .orElse("");

				return header.concat(content);
		}

		@Override
		public
		    Function1<List<OperationSpecification>, ImmutableList<AccountStatementLineSpecification>>
		    statementOfOperations() {
				return operations -> operations.stream()
				                               .map(OperationSpecification::toStatementLine)
				                               .collect(Collectors2.toImmutableList());
		}
}
