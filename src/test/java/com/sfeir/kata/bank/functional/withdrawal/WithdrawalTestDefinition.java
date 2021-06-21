package com.sfeir.kata.bank.functional.withdrawal;

import com.sfeir.kata.bank.domain.money.IMoneyOperator;

public interface WithdrawalTestDefinition {

		void make_a_withdrawal_with_success(IMoneyOperator amount);

		void make_an_unauthorized_withdrawal(IMoneyOperator amount);
}
