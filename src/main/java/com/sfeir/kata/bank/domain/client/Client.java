package com.sfeir.kata.bank.domain.client;

import java.time.LocalDateTime;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.operation.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationHistory;
import com.sfeir.kata.bank.domain.operation.OperationType;

import io.vavr.Function2;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client implements ClientOperation {

	private Account account;

	@Override
	public boolean deposit(Money amount) {

		var balanceResult = this.account.getBalance().add(amount);

		var operation = Operation.builder().type(OperationType.DEPOSIT).amount(amount).date(LocalDateTime.now())
				.initialBalance(amount).balanceResult(balanceResult).build();

		this.account.setBalance(balanceResult);

		Function2<OperationHistory, Operation, Boolean> saveOperation = (history, ope) -> history.getOperations()
				.add(ope);

		return saveOperation.apply(this.account.getHistory(), operation);

	}
}
