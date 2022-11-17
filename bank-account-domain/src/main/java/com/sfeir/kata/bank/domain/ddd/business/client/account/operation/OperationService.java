package com.sfeir.kata.bank.domain.ddd.business.client.account.operation;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.date.OperationDateService;

public interface OperationService {

		Money getAmount();

		Money getBalance();

		OperationDateService getDate();

		OperationType getType();
}
