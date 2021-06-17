package com.sfeir.kata.bank.domain.money;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.money.validation.MoneyValidator;

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

	public Money toNegative() {

		return new Money(this.amount.negate());
	}

	public Boolean isLargerThan(Money other) {
		return amount.abs().compareTo(other.getAmount()) > 0;
	}
}
