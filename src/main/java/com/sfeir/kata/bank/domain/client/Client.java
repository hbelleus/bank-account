package com.sfeir.kata.bank.domain.client;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.operation.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationFactory;
import com.sfeir.kata.bank.domain.operation.OperationHistory;

import io.vavr.Function2;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client implements ClientOperation {

	private Account account;

	@Override
	public boolean deposit(Money amount) {

		var operation = OperationFactory.create(amount, account);

		this.account.setBalance(operation.getBalanceResult());

		Function2<OperationHistory, Operation, Boolean> saveOperation = (history, ope) -> history.getOperations()
				.add(ope);

		return saveOperation.apply(this.account.getHistory(), operation);

	}
}
