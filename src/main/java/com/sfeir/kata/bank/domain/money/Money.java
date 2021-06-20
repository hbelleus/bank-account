package com.sfeir.kata.bank.domain.money;

import java.math.BigDecimal;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of")
public class Money implements IMoneyOperation {

		@NonNull
		BigDecimal amount;

		public boolean isLargerThan(Money other) {
				return amount.abs()
				             .compareTo(other.getAmount()) > 0;
		}

		@Override
		public String toString() {
				return amount.toPlainString();
		}
}
