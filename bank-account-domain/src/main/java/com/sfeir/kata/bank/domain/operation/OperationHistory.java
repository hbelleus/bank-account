package com.sfeir.kata.bank.domain.operation;

import org.eclipse.collections.api.list.MutableList;

import io.vavr.Function1;
import lombok.Value;

@Value
public class OperationHistory {

		MutableList<Operation> operations;

		public boolean isEmpty() {
				return this.operations.isEmpty();
		}

		@Override
		public String toString() {

				Function1<String, String> nextLine = string -> string.concat(System.lineSeparator());

				var builder = new StringBuilder(nextLine.apply("operations :"));

				operations.stream()
				          .map(Operation::toString)
				          .map(nextLine)
				          .forEach(builder::append);

				return builder.toString();
		}

}
