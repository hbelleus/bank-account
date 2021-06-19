package com.sfeir.kata.bank.domain.operation;

import java.time.LocalDateTime;

import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.utils.OperationComparator;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Operation implements OperationComparator {

	Money amount;
	Money balanceResult;
	LocalDateTime date;
	OperationType type;
}
