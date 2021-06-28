package com.sfeir.kata.bank.domain.client.account.operation.history;

import java.util.Optional;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;

import com.sfeir.kata.bank.domain.client.account.operation.OperationService;

import io.vavr.Function0;
import io.vavr.Function1;

public interface OperationHistoryService {

		MutableList<OperationService> getOperations();

		default Function1<OperationService, Boolean> addOperation() {
				return this.getOperations()::add;
		}
		
		default Function0<Boolean> isEmpty() {
			return this.getOperations()::isEmpty;
		}

		default Function0<MutableList<OperationService>> reverseLastOperationFirst() {
				return this.getOperations()::toReversed;
		}

		default
		    Function1<ImmutableList<OperationService>, Optional<OperationService>>
		    pickFirst() {
				return operations -> operations.stream()
				                               .findFirst();
		}

		default Function0<Optional<OperationService>>
		    getLastOperation() {
				return reverseLastOperationFirst()
					   .andThen(MutableList::toImmutable)
					   .andThen(pickFirst());
		}
}
