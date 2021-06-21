package com.sfeir.kata.bank.domain.money.factory;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.money.IMoneyOperator;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
class Money implements IMoneyOperator {

		@NonNull
		BigDecimal amount;

		@Override
		public String toString() {
				return amount.toPlainString();
		}
}
