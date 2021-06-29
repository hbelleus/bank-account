package com.sfeir.kata.bank.domain.client.account.operation.history.factory;

import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.impl.list.mutable.MutableListFactoryImpl;

import com.sfeir.kata.bank.domain.client.account.operation.OperationService;
import com.sfeir.kata.bank.domain.client.account.operation.history.OperationHistoryService;

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

}
