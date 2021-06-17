package com.sfeir.kata.bank.deposit;

import com.sfeir.kata.bank.domain.operation.Money;

interface DepositTestDefinition {

	void givenPositiveAmount_whenDeposit_thenAccountIsUpdated(Money amount);

	void givenNegativeAmount_whenDeposit_thenAccountBalanceNotUpdated(Money amount);

}
