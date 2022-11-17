package com.sfeir.kata.bank.domain.common.money;

import java.math.BigDecimal;

import io.vavr.Function0;
import io.vavr.Function1;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PUBLIC)
public class Money {

		@NonNull
		BigDecimal amount;

		String currency = "EUR";

		public static Money zero() {
				return Money.of(BigDecimal.ZERO);
		}

		public static Money of(String amount) {
				return Money.of(new BigDecimal(amount));
		}

		@Override
		public String toString() {
				return amount.toPlainString().concat(currency);
		}

		public Function1<Money, Money> putMoney() {
				return money -> Money.of(this.getAmount()
				                             .add(money.getAmount()));
		}

		public Function1<Money, Money> retrieveMoney() {
				return money -> Money.of(this.getAmount()
				                             .subtract(money.getAmount()));
		}

		public Function0<Money> toNegative() {
				return () -> Money.of(this.getAmount().negate());
		}

		public Function1<Money, Boolean>
		    isAbsolutelyMoreThan() {
				return money -> this.getAmount()
				                    .abs()
				                    .compareTo(money.getAmount()) > 0;
		}

		public Function1<Money, Boolean> isMoreThan() {
				return money -> this.getAmount()
				                    .compareTo(money.getAmount()) > 0;
		}

		public Function1<Money, Boolean> isLessThan() {
				return money -> this.getAmount()
				                    .compareTo(money.getAmount()) < 0;
		}
}
