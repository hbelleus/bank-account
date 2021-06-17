package com.sfeir.kata.bank.domain.operation;

import java.time.LocalDateTime;

import com.sfeir.kata.bank.domain.money.Money;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Operation {

	Money amount;
	Money balanceResult;
	LocalDateTime date;
	OperationType type;

	@Override
	public String toString() {

		var builder = new StringBuilder();

		var format = "|%s|%s|%s|%s|";

		builder.append(String.format(format, this.date, this.type, this.amount, this.balanceResult));
		builder.append(System.lineSeparator());
		
		return builder.toString();
	}

}
