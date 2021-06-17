package com.sfeir.kata.bank.domain.operation.money;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class Money {

	BigDecimal amount;

	private Money(BigDecimal amount) {
		this.amount = amount;
	}

	public static Money of(BigDecimal amount) {

		MoneyValidator.validate(amount);

		return new Money(amount);
	}

	public Money add(Money money) {

		var finalAmount = this.amount.add(money.amount);

		return new Money(finalAmount);
	}

	public Money retrieve(Money money) {

		var finalAmount = this.amount.subtract(money.amount);

		return new Money(finalAmount);
	}
}
