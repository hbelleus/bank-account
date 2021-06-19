package com.sfeir.kata.bank.domain.operation;

import java.time.LocalDateTime;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.validation.OperationValidator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationFactory {

	public static Operation createDeposit(Money amount, Account account) {

		return Operation.builder().type(OperationType.DEPOSIT).amount(amount).date(LocalDateTime.now())
				.balanceResult(account.getBalance().apply().add().apply(amount)).build();
	}

	public static Operation createWithdrawal(Money amount, Account account) {

		OperationValidator.validateWithdrawal(amount, account.getBalance().apply());

		amount = amount.toNegative().apply();

		return Operation.builder().type(OperationType.WITHDRAWAL).amount(amount).date(LocalDateTime.now())
				.balanceResult(account.getBalance().apply().add().apply(amount)).build();
	}
}
