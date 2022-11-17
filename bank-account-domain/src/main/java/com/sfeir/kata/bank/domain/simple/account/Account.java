package com.sfeir.kata.bank.domain.simple.account;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.collections.impl.collector.Collectors2;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.simple.account.operation.Deposit;
import com.sfeir.kata.bank.domain.simple.account.operation.Operation;
import com.sfeir.kata.bank.domain.simple.account.operation.Withdrawal;
import com.sfeir.kata.bank.domain.simple.account.statement.AccountStatement;
import com.sfeir.kata.bank.domain.simple.account.statement.AccountStatementLine;

public class Account implements AccountSpecification {

		private final List<Operation> operations = new ArrayList<>();

		public Account() {

				var initialDeposit = Deposit.builder()
				                            .amount(Money.zero())
				                            .balance(Money.zero())
				                            .build();

				this.operations.add(initialDeposit);
		}

		@Override
		public Money getBalance()
		    throws IllegalAccessException {
				return this.operations.stream()
				                      .findFirst()
				                      .map(Operation::getBalance)
				                      .orElseThrow(() -> new IllegalAccessException("Couldn't read balance"));
		}

		@Override
		public void deposit(Money amount)
		    throws IllegalAccessException {

				this.addOperation(Deposit.builder()
				                         .amount(amount)
				                         .balance(this.getBalance())
				                         .build());
		}

		@Override
		public void withdraw(Money amount)
		    throws IllegalAccessException {

				this.addOperation(Withdrawal.builder()
				                            .amount(amount)
				                            .balance(this.getBalance())
				                            .build());
		}

		@Override
		public AccountStatement getStatement() {
				return operations.stream()
				                 .map(AccountStatementLine::of)
				                 .collect(Collectors.collectingAndThen(Collectors2.toImmutableList(),
				                                                       AccountStatement::new));
		}

		private void addOperation(Operation operation) {
				this.operations.add(0, operation);
		}

}
