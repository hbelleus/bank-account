package com.sfeir.kata.bank.functional.deposit;

import com.sfeir.kata.bank.domain.common.money.Money;

public interface DepositTestDefinition {

		void makeADeposit(Money amount)
		    throws IllegalAccessException;

}
