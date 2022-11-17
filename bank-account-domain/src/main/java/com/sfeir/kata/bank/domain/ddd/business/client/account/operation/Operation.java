package com.sfeir.kata.bank.domain.ddd.business.client.account.operation;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.date.OperationDateService;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.date.factory.OperationDateFactory;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
abstract class Operation implements OperationService {

		protected Money amount;
		protected Money balance;

		protected OperationDateService date;
		protected OperationType        type;

		protected void of(Money amount, Money balance,
		                  OperationType type) {

				this.amount  = amount;
				this.balance = balance;
				this.date    = OperationDateFactory.now().apply();
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
