package com.sfeir.kata.bank.functional.deposit;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.provider.Arguments;

import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

import io.vavr.Function1;

@TestInstance(Lifecycle.PER_CLASS)
public interface DepositTestSource {

		Function1<MoneyService, Arguments> createCase = (amount) -> Arguments.of(amount);

		public static Stream<Arguments>
		    generatePositiveAmount() {

				var case1 = createCase.apply(BankMoneyFactory.create(BigDecimal.ZERO));
				var case2 = createCase.apply(BankMoneyFactory.create("2788.90"));

				return Stream.of(case1, case2);
		}
}
