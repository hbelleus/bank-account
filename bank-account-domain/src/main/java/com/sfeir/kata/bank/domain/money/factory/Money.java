package com.sfeir.kata.bank.domain.money.factory;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class Money implements MoneyService {

		@NonNull
		BigDecimal amount;

		@Override
		public String toString() {
				return amount.toPlainString();
		}
}
