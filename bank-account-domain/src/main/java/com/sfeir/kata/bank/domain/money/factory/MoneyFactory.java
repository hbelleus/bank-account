package com.sfeir.kata.bank.domain.money.factory;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MoneyFactory {

		public static MoneyService create(BigDecimal amount) {
				return Money.of(amount);
		}

		public static MoneyService create(String amount) {
				return Money.of(new BigDecimal(amount));
		}

		public static MoneyService create(int amount) {
				return Money.of(BigDecimal.valueOf(amount));
		}

		public static MoneyService zero() {
				return Money.of(BigDecimal.valueOf(0));
		}
}
