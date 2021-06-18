package com.sfeir.kata.bank.domain.operation;

import java.time.LocalDateTime;

import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.utils.DateFormatter;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Operation implements Comparable<Operation> {

	Money amount;
	Money balanceResult;
	LocalDateTime date;
	OperationType type;

	@Override
	public String toString() {

		var format = "|%s|%s|%s|%s|";

		return String.format(format, DateFormatter.format(date), this.type, this.amount, this.balanceResult);
	}

	@Override
	public int compareTo(Operation o) {
		return this.getDate().compareTo(o.getDate());
	}

}
