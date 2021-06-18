package com.sfeir.kata.bank.domain.money;

import java.math.BigDecimal;

import lombok.Value;

@Value
public class Money {

	BigDecimal amount;

	private Money(BigDecimal amount) {
		this.amount = amount;
	}

	public static Money of(BigDecimal amount) {

		return new Money(amount.abs());
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

	@Override
	public String toString() {
		return String.format("%s", amount.toPlainString());
	}
}
