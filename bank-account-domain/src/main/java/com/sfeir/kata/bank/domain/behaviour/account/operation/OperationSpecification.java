package com.sfeir.kata.bank.domain.behaviour.account.operation;

import com.sfeir.kata.bank.domain.behaviour.account.operation.date.OperationDateSpecification;
import com.sfeir.kata.bank.domain.behaviour.account.statement.AccountStatementLineSpecification;
import com.sfeir.kata.bank.domain.common.money.Money;

public interface OperationSpecification {

		OperationType getOperationType();

		Money getAmount();

		OperationDateSpecification getOperationDate();

		Money getFinalBalance();

		AccountStatementLineSpecification toStatementLine();
}
