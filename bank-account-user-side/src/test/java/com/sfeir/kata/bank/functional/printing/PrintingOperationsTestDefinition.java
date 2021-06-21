package com.sfeir.kata.bank.functional.printing;

public interface PrintingOperationsTestDefinition {

	void print_statement_of_empty_history(String expectedResult);

	void print_non_empty_statement(String expectedResult);
}
