package com.sfeir.kata.bank.functional.withdrawal;

import java.util.stream.Stream;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.provider.Arguments;

import com.sfeir.kata.bank.domain.common.money.Money;

import io.vavr.Function1;

@TestInstance(Lifecycle.PER_CLASS)
public interface WithdrawalTestSource {

		Function1<Money, Arguments> createCase = (amount) -> Arguments.of(amount);

		public static Stream<Arguments>
		    givenAuthorizedAmount() {

				var case1 = createCase.apply(Money.zero());
				var case2 = createCase.apply(Money.of("500"));

				return Stream.of(case1, case2);
		}

		public static Stream<Arguments>
		    givenUnauthorizedAmount() {

				var case1 = createCase.apply(Money.of("1000"));

				return Stream.of(case1);
		}
}
