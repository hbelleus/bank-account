package com.sfeir.kata.bank.domain.bddfriendly.account.operation;

import com.sfeir.kata.bank.domain.bddfriendly.account.operation.date.OperationDateSpecification;
import com.sfeir.kata.bank.domain.bddfriendly.account.operation.date.factory.OperationDateFactory;
import com.sfeir.kata.bank.domain.bddfriendly.account.statement.AccountStatementLine;
import com.sfeir.kata.bank.domain.bddfriendly.account.statement.AccountStatementLineSpecification;
import com.sfeir.kata.bank.domain.common.money.Money;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Operation
    implements OperationSpecification {

		protected Money amount;
		protected Money finalBalance;

		protected OperationDateSpecification operationDate;
		protected OperationType              operationType;

		protected void of(Money amount, Money finalBalance,
		                  OperationType type) {

				this.amount        = amount;
				this.operationDate = OperationDateFactory.now()
				                                         .apply();
				this.operationType = type;
				this.finalBalance  = finalBalance;
		}

		@Override
		public AccountStatementLineSpecification
		    toStatementLine() {
				return AccountStatementLine.builder()
				                           .amount(this.getAmount()
				                                       .toString())
				                           .balance(this.getFinalBalance()
				                                        .toString())
				                           .date(this.getOperationDate()
				                                     .toString())
				                           .type(this.getOperationType()
				                                     .name())
				                           .build();
		}
}
