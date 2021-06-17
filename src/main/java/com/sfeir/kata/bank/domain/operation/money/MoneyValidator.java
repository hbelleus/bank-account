package com.sfeir.kata.bank.domain.operation.money;

import java.math.BigDecimal;

public interface MoneyValidator {

	static void validate(BigDecimal amount) {

		if (amount.compareTo(BigDecimal.ZERO) < 0)
			throw new IllegalArgumentException("Amount should be positive");
	}
}
