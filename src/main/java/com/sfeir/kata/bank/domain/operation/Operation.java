package com.sfeir.kata.bank.domain.operation;

import java.time.LocalDateTime;

import com.sfeir.kata.bank.domain.money.Money;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Operation {

	Money amount;
	Money initialBalance;
	Money balanceResult;
	LocalDateTime date;
	OperationType type;

}
