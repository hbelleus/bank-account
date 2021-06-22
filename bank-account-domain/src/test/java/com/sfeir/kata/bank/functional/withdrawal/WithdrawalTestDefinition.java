package com.sfeir.kata.bank.functional.withdrawal;

import com.sfeir.kata.bank.domain.money.MoneyService;

public interface WithdrawalTestDefinition {

		void make_a_withdrawal_with_success(MoneyService amount);

		void make_an_unauthorized_withdrawal(MoneyService amount);
}
