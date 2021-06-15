package com.sfeir.kata.bank.deposit;

import java.util.stream.Stream;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.provider.Arguments;

import io.vavr.Function2;

@TestInstance(Lifecycle.PER_CLASS)
public interface DepositiFunctionalCase extends DepositFunctionalTest {

	Function2<String, String, Arguments> createCase = (amount, expected) -> Arguments.of(amount, expected);

	public static Stream<Arguments> givenPositiveAmount_whenDeposit_thenAccountBalanceIsUpdated() {

		var case1 = createCase.apply("500", "1000");
		var case2 = createCase.apply("1000", "1500");
		var case3 = createCase.apply("2788.90", "3288.90");

		return Stream.of(case1, case2, case3);
	}

	public static Stream<Arguments> givenNegativeOrNullAmount_whenDeposit_thenAccountBalanceIsNotUpdated() {

		final var initialBalance = "500";

		var case1 = createCase.apply("0", initialBalance);
		var case2 = createCase.apply("-1000", initialBalance);
		var case3 = createCase.apply("-5", initialBalance);

		return Stream.of(case1, case2, case3);
	}
}
