package com.sfeir.kata.bank.functional.withdrawal;

import com.sfeir.kata.bank.domain.common.money.Money;

public interface WithdrawalTestDefinition {

		void make_a_withdrawal_with_success(Money amount)
		    throws IllegalAccessException;

		void make_an_unauthorized_withdrawal(Money amount)
		    throws IllegalAccessException;
}
