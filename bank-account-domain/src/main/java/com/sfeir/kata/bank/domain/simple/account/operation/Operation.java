package com.sfeir.kata.bank.domain.simple.account.operation;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.simple.account.operation.date.factory.OperationDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public abstract class Operation {

		protected Money amount;
		protected Money balance;

		protected OperationDate date;
		protected OperationType type;

		protected void of(Money amount, Money balance,
		                  OperationType type) {

				this.amount  = amount;
				this.balance = balance;
				this.date    = OperationDate.now();
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
