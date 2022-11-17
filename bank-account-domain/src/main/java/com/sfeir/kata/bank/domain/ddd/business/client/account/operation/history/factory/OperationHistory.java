package com.sfeir.kata.bank.domain.ddd.business.client.account.operation.history.factory;

import java.util.Optional;

import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.MutableListFactoryImpl;

import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.OperationService;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.history.OperationHistoryService;

import io.vavr.Function0;
import io.vavr.Function1;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class OperationHistory
    implements OperationHistoryService {

		@NonNull
		MutableList<OperationService> operations;

		OperationHistory() {

				this.operations = MutableListFactoryImpl.INSTANCE.empty();
		}

		@Override
		public String toString() {

				Function1<String, String> separateLines = string -> string.concat(System.lineSeparator());

				var builder = new StringBuilder(separateLines.apply("operations :"));

				operations.stream()
				          .map(OperationService::toString)
				          .map(separateLines)
				          .forEach(builder::append);

				return builder.toString();
		}

		@Override
		public Function0<Boolean> isEmpty() {
				return this.getOperations()::isEmpty;
		}

		@Override
		public Function0<Integer> size() {
				return this.getOperations()::size;
		}

		public Function0<ImmutableList<OperationService>>
		    readOperations() {
				return this.getOperations()::toImmutable;
		}

		public
		    Function1<ImmutableList<OperationService>, Optional<OperationService>>
		    pickFirst() {
				return operationList -> operationList.stream()
				                                     .findFirst();
		}

		@Override
		public Function0<Optional<OperationService>>
		    getLastOperation() {
				return (readOperations()).andThen(pickFirst());
		}

}
