package com.sfeir.kata.bank.domain.statement.utils;

import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.utils.DateFormatter;

import io.vavr.Function1;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class OperationReader {

	static Function1<Operation, String> readOperationAsLine() {
		return operation -> String.format("|%s|%s|%s|%s|", DateFormatter.format(operation.getDate()),
				operation.getType(), operation.getAmount(), operation.getBalanceResult());
	}
}
