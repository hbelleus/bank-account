package com.sfeir.kata.bank.withdrawal;

import java.util.stream.Stream;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.provider.Arguments;

import io.vavr.Function2;

@TestInstance(Lifecycle.PER_CLASS)
public interface WithdrawalFunctionalCase extends WithdrawalFunctionalTest {

	Function2<String, String, Arguments> createCase = (amount, expected) -> Arguments.of(amount, expected);

	public static Stream<Arguments> givenPositiveAmountInLimit_whenWithdraw_thenAccountBalanceIsUpdated() {

		var case1 = createCase.apply("500", "0");
		var case2 = createCase.apply("250", "250");
		var case3 = createCase.apply("100.90", "399.10");

		return Stream.of(case1, case2, case3);
	}

	public static Stream<Arguments> givenPositiveAmountOutOfLimit_whenWithdraw_thenTransactionFailed() {

		final var initialBalance = "500";

		var case1 = createCase.apply("1500", initialBalance);
		var case2 = createCase.apply("-1000", initialBalance);
		var case3 = createCase.apply("600", initialBalance);

		return Stream.of(case1, case2, case3);
	}
}
