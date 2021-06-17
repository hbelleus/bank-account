package com.sfeir.kata.bank.withdrawal;

import com.sfeir.kata.bank.domain.operation.money.Money;

public interface WithdrawalFunctionalTest {

	void make_a_withdrawal_with_success(Money amount);

	void make_an_unauthorized_withdrawal(Money amount);
}
