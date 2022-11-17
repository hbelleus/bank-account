package com.sfeir.kata.bank.behaviour.state;

import com.sfeir.kata.bank.domain.behaviour.account.AccountSpecification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AccountContext {

		private AccountSpecification accountSpecification;
}
