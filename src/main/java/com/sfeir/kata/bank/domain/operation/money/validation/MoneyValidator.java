package com.sfeir.kata.bank.domain.operation.money.validation;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.operation.money.exception.MoneyInstantiationException;

public interface MoneyValidator {

	static void validate(BigDecimal amount) {

		if (amount.compareTo(BigDecimal.ZERO) < 0)
			throw new MoneyInstantiationException("Amount should be positive");
	}
}
