package com.sfeir.kata.bank.domain.ddd.business.client.account.operation.history;

import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.collections.api.list.MutableList;

import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.OperationService;

import io.vavr.Function0;

public interface OperationHistoryService {

		MutableList<OperationService> getOperations();

		default Consumer<OperationService> addOperation() {
				return operation -> this.getOperations()
				                        .add(0, operation);
		}

		Function0<Boolean> isEmpty();

		Function0<Integer> size();

		Function0<Optional<OperationService>>
		    getLastOperation();
}
