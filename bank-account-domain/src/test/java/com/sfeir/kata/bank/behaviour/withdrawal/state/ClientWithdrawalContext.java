package com.sfeir.kata.bank.behaviour.withdrawal.state;

import com.sfeir.kata.bank.behaviour.state.ClientContext;
import com.sfeir.kata.bank.domain.common.money.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientWithdrawalContext extends ClientContext {

		private Money withdrawalAmount;
}
