package com.sfeir.kata.bank.domain.bddfriendly.account.operation;

import com.sfeir.kata.bank.domain.bddfriendly.account.operation.date.OperationDateSpecification;
import com.sfeir.kata.bank.domain.bddfriendly.account.statement.AccountStatementLineSpecification;
import com.sfeir.kata.bank.domain.common.money.Money;

public interface OperationSpecification {

		OperationType getOperationType();

		Money getAmount();

		OperationDateSpecification getOperationDate();

		Money getFinalBalance();

		AccountStatementLineSpecification toStatementLine();
}
