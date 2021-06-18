package com.sfeir.kata.bank.functional.printing;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

public interface PrintingOperationsFunctionalCase {

	public static Stream<Arguments> print_statement_of_empty_history() {
		return Stream.of(Arguments.of("No Operation"));
	}

	public static Stream<Arguments> print_non_empty_statement() {

		var expectedResult = "|DATE|OPERATION|AMOUNT|BALANCE|".concat(System.lineSeparator()).concat("|%s|%s|%s|%s|")
				.concat(System.lineSeparator());

		return Stream.of(Arguments.of(expectedResult));
	}
}
