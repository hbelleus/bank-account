package com.sfeir.kata.bank.domain.client;

import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationFactory;
import com.sfeir.kata.bank.domain.operation.OperationHistory;
import com.sfeir.kata.bank.domain.operation.OperationType;
import com.sfeir.kata.bank.domain.operation.money.Money;

import io.vavr.Function2;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client implements ClientOperation {

	private Account account;

	@Override
	public boolean deposit(Money amount) {

		var operation = OperationFactory.create(amount, account, OperationType.DEPOSIT);

		this.updateAccountBalance().accept(operation.getBalanceResult());

		return this.saveOperation().apply(this.account.getHistory(), operation);

	}

	@Override
	public boolean withdrawal(Money amount) {

		var operation = OperationFactory.create(amount.toNegative(), account, OperationType.WITHDRAWAL);

		return this.saveOperation().apply(this.account.getHistory(), operation);
	}
	
	private Consumer<Money> updateAccountBalance() {
		return this.account::setBalance;
	}

	private Function2<OperationHistory, Operation, Boolean> saveOperation() {
		return (history, ope) -> history.getOperations().add(ope);
	}
}