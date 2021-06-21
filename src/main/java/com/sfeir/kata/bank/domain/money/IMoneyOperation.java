package com.sfeir.kata.bank.domain.money;

import java.math.BigDecimal;

import io.vavr.Function0;
import io.vavr.Function1;

public interface IMoneyOperation {

		BigDecimal getAmount();

		default Function1<Money, Money> addMoney() {
				return money -> Money.of(this.getAmount()
				                             .add(money.getAmount()));
		}

		default Function0<Money> toNegative() {
				return () -> Money.of(this.getAmount().negate());
		}
}
