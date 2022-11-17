package com.sfeir.kata.bank.behaviour.withdrawal.state;

import com.sfeir.kata.bank.behaviour.deposit.state.AccountDepositContext;
import com.sfeir.kata.bank.domain.behaviour.fixture.WithdrawalFixtureSpecification;
import com.sfeir.kata.bank.domain.common.money.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountWithdrawalContext
    extends AccountDepositContext {

		private WithdrawalFixtureSpecification withdrawalFixtureSpecification;

		private Money withdrawalAmount;
}
