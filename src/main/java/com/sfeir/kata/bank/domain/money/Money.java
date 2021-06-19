package com.sfeir.kata.bank.domain.money;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Money implements MoneyOperation {

	@NonNull
	BigDecimal amount;

	public static Money of(BigDecimal amount) {

		return new Money(amount.abs());
	}

	public boolean isLargerThan(Money other) {
		return amount.abs().compareTo(other.getAmount()) > 0;
	}

	@Override
	public String toString() {
		return amount.toPlainString();
	}
}
