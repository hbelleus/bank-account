package com.sfeir.kata.bank.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationHistory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankAccountMockFactory {

	public static synchronized Account create() {

		var operationHistory = new OperationHistory(new ArrayList<Operation>());
		var initialBalance = Money.of(BigDecimal.ZERO);

		return Account.builder().balance(initialBalance).history(operationHistory).build();
	}
}
