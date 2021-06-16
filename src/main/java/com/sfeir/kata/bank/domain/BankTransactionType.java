package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;

import io.vavr.Function2;

public enum BankTransactionType implements BankTransactionExecution {

	DEPOSIT {

		@Override
		public Function2<BigDecimal, BigDecimal, BigDecimal> getOperation() {
			return (x, y) -> x.add(y);
		}

	},

	WITHDRAWAL {
		
		@Override
		public Function2<BigDecimal, BigDecimal, BigDecimal> getOperation() {
			return (x, y) -> x.subtract(y);
		}

	};
}
