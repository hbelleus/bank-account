package com.sfeir.kata.bank.domain.client.account.operation;

import com.sfeir.kata.bank.domain.client.account.operation.date.OperationDate;
import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Operation {

		MoneyService amount;
		MoneyService balanceResult;

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
