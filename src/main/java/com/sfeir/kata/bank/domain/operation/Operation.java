package com.sfeir.kata.bank.domain.operation;

import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.date.OperationDate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Operation {

		Money         amount;
		Money         balanceResult;
		OperationDate date;
		OperationType type;

		@Override
		public String toString() {
				return String.format("%s of %s done at %s ->  new balance: %s",
				                     this.type, this.amount,
				                     this.date.toString(),
				                     this.balanceResult);
		}
}
