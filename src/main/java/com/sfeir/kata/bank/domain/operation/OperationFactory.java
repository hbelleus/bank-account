package com.sfeir.kata.bank.domain.operation;

import java.time.LocalDateTime;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.validation.OperationValidator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationFactory {

	public static Operation create(Money amount, Account account, OperationType type) {

		OperationValidator.validate(amount, account.getBalance(), type);

		var balanceResult = account.getBalance().add(amount);

		return Operation.builder().type(type).amount(amount).date(LocalDateTime.now()).initialBalance(amount)
				.balanceResult(balanceResult).build();
	}
}
