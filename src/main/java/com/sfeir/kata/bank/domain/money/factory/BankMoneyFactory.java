package com.sfeir.kata.bank.domain.money.factory;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.money.IMoneyOperator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankMoneyFactory {

		public static IMoneyOperator create(BigDecimal amount) {
				return Money.of(amount);
		}

		public static IMoneyOperator create(String amount) {
				return Money.of(new BigDecimal(amount));
		}

		public static IMoneyOperator create(int amount) {
				return Money.of(BigDecimal.valueOf(amount));
		}
}
