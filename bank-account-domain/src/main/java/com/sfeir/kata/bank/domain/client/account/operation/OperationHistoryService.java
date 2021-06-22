package com.sfeir.kata.bank.domain.client.account.operation;

import org.eclipse.collections.api.list.MutableList;

import io.vavr.Function0;
import io.vavr.Function1;

public interface OperationHistoryService {

		MutableList<Operation> getOperations();

		default Function1<Operation, Boolean> addOperation() {
				return this.getOperations()::add;
		}

		default Function0<Boolean> isEmpty() {
				return this.getOperations()::isEmpty;
		}
}
