package com.sfeir.kata.bank.domain;

import java.time.LocalDateTime;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
public class BankTransaction {

	private String amount;
	private BankTransactionType type;
	private LocalDateTime time;

	public static BankTransaction of(String amount, BankTransactionType type) {
		return BankTransaction.builder().amount(amount).type(type).time(LocalDateTime.now()).build();
	}

}
