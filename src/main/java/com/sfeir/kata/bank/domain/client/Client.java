package com.sfeir.kata.bank.domain.client;

import java.time.LocalDateTime;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.operation.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client implements ClientOperation {

	private Account account;

	@Override
	public boolean deposit(Money amount) {

		var operation = Operation.builder().type(OperationType.DEPOSIT).amount(amount).date(LocalDateTime.now())
				.build();

		return this.account.getHistory().getOperations().add(operation);

	}
}
