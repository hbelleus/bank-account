package com.sfeir.kata.bank.behaviour.deposit.state;

import com.sfeir.kata.bank.behaviour.state.AccountContext;
import com.sfeir.kata.bank.domain.behaviour.fixture.DepositFixtureSpecification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountDepositContext extends AccountContext {

		DepositFixtureSpecification depositFixtureSpecification;
}
