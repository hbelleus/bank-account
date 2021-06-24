package com.sfeir.kata.bank.domain.client.account.operation.factory;

import com.sfeir.kata.bank.domain.client.account.operation.OperationType;
import com.sfeir.kata.bank.domain.client.account.operation.date.OperationDate;
import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class Operation {

		protected MoneyService amount;
		protected MoneyService balance;

		protected OperationDate date;
		protected OperationType type;

		protected void of(MoneyService amount,
		                  MoneyService balance,
		                  OperationType type) {

				this.amount  = amount;
				this.balance = balance;
				this.date    = new OperationDate();
				this.type    = type;
		}

		@Override
		public String toString() {
				return String.format("%s of %s done at %s ->  new balance: %s",
				                     this.type, this.amount,
				                     this.date.toString(),
				                     this.balance);
		}
}
