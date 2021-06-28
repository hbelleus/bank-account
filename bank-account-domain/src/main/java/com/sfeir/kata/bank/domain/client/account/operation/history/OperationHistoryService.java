package com.sfeir.kata.bank.domain.client.account.operation.history;

import java.util.Optional;
import java.util.function.Consumer;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.sfeir.kata.bank.domain.client.account.operation.OperationService;

import io.vavr.Function0;
import io.vavr.Function1;

public interface OperationHistoryService {

		MutableList<OperationService> getOperations();

		default Consumer<OperationService> addOperation() {
				return operation -> this.getOperations().add(0, operation);
		}
		
		default Function0<Boolean> isEmpty() {
			return this.getOperations()::isEmpty;
		}
		
		default Function0<Integer> size() {
			return this.getOperations()::size;
		}

		default Function0<ImmutableList<OperationService>> readOperation() {
				return this.getOperations()::toImmutable;
		}

		default
		    Function1<ImmutableList<OperationService>, Optional<OperationService>>
		    pickFirst() {
				return operations -> operations.stream()
				                               .findFirst();
		}

		default Function0<Optional<OperationService>>
		    getLastOperation() {
				
				return (readOperation())
						.andThen(pickFirst());
		}
}
