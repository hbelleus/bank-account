package com.sfeir.kata.bank.domain.bddfriendly.account.operation;

import com.sfeir.kata.bank.domain.common.money.Money;

public interface DepositSpecification {

		default Boolean isDepositValid(Money amount) {
				return amount.isMoreThan().apply(Money.zero());
		}
}
