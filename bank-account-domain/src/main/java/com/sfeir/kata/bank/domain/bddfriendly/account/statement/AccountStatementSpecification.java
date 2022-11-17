package com.sfeir.kata.bank.domain.bddfriendly.account.statement;

import java.util.List;

import org.eclipse.collections.api.list.ImmutableList;

import com.sfeir.kata.bank.domain.bddfriendly.account.operation.OperationSpecification;

import io.vavr.Function1;

public interface AccountStatementSpecification {

		public ImmutableList<AccountStatementLineSpecification>
		    getLines();

		Function1<List<OperationSpecification>, ImmutableList<AccountStatementLineSpecification>>
		    statementOfOperations();
}
