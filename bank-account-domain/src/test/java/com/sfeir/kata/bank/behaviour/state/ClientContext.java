package com.sfeir.kata.bank.behaviour.state;

import com.sfeir.kata.bank.domain.ddd.business.client.account.Account;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ClientContext {

		private Account account;
}
