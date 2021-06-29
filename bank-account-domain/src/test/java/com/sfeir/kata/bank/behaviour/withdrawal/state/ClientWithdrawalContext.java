package com.sfeir.kata.bank.behaviour.withdrawal.state;

import com.sfeir.kata.bank.behaviour.state.ClientContext;
import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientWithdrawalContext extends ClientContext {

		private MoneyService withdrawalAmount;
}
