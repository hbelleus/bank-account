package com.sfeir.kata.bank.functional.printing;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import com.sfeir.kata.bank.infra.printer.IConsolePrinter;

public interface PrintingOperationsTest {

	public static Stream<Arguments> print_statement_of_empty_history() {
		return Stream.of(Arguments.of(IConsolePrinter.STATEMENT_HEADER));
	}

	public static Stream<Arguments> print_non_empty_statement() {

		var expectedResult = IConsolePrinter.STATEMENT_HEADER.concat(System.lineSeparator()).concat("|%s|%s|%s|%s|")
				.concat(System.lineSeparator());

		return Stream.of(Arguments.of(expectedResult));
	}
}
