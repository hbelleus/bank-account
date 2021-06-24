package com.sfeir.kata.bank.domain.client.account.operation;

import java.util.Optional;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.sfeir.kata.bank.domain.client.account.operation.factory.Operation;

import io.vavr.Function0;
import io.vavr.Function1;

public interface OperationHistoryService {

		MutableList<Operation> getOperations();

		default Function1<Operation, Boolean> addOperation() {
				return this.getOperations()::add;
		}

		default Function0<MutableList<Operation>> reverse() {
				return this.getOperations()::toReversed;
		}

		default Function0<Boolean> isEmpty() {
				return this.getOperations()::isEmpty;
		}

		default Function0<ImmutableList<Operation>>
		    reverseOperationOrder() {
				return () -> reverse().apply().toImmutable();
		}

		default
		    Function1<ImmutableList<Operation>, Optional<Operation>>
		    pickFirst() {
				return operations -> operations.stream()
				                               .findFirst();
		}

		default Function0<Optional<Operation>>
		    getLastOperation() {
				return reverseOperationOrder().andThen(pickFirst());
		}
}
