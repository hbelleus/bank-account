package com.sfeir.kata.bank.deposit;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.provider.Arguments;

import io.vavr.Function1;

@TestInstance(Lifecycle.PER_CLASS)
public interface DepositiFunctionalCase extends DepositTestDefinition {

	Function1<BigDecimal, Arguments> createCase = (amount) -> Arguments.of(amount);

	public static Stream<Arguments> givenPositiveAmount() {

		var case1 = createCase.apply(new BigDecimal("0"));
		var case2 = createCase.apply(new BigDecimal("2788.90"));

		return Stream.of(case1, case2);
	}

	public static Stream<Arguments> givenNegativeAmount() {

		var case1 = createCase.apply(new BigDecimal("-100"));

		return Stream.of(case1);
	}
}
