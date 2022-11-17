package com.sfeir.kata.bank.functional.withdrawal;

import com.sfeir.kata.bank.domain.common.money.Money;

public interface WithdrawalTestDefinition {

		void makeAWithdrawalWithSuccess(Money amount)
		    throws IllegalAccessException;

		void makeAnUnauthorizedWithdrawal(Money amount)
		    throws IllegalAccessException;
}
