package com.sfeir.kata.bank.domain.bddfriendly.account;

import java.util.ArrayList;
import java.util.List;

import com.sfeir.kata.bank.domain.bddfriendly.account.operation.Deposit;
import com.sfeir.kata.bank.domain.bddfriendly.account.operation.OperationSpecification;
import com.sfeir.kata.bank.domain.bddfriendly.account.statement.AccountStatement;
import com.sfeir.kata.bank.domain.bddfriendly.account.statement.AccountStatementSpecification;
import com.sfeir.kata.bank.domain.common.money.Money;

import lombok.Value;

@Value
public class Account implements AccountSpecification {

		List<OperationSpecification> operations = new ArrayList<>();

		public Account() {

				var initialDeposit = Deposit.builder()
				                            .amount(Money.zero())
				                            .initialBalance(Money.zero())
				                            .build();

				this.addOperation().accept(initialDeposit);
		}

		@Override
		public AccountStatementSpecification getStatement() {
				return new AccountStatement(this.operations);
		}

}
