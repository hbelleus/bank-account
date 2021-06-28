package com.sfeir.kata.bank.behaviour.withdrawal.state;

import com.sfeir.kata.bank.domain.client.ClientService;
import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientWithdrawalContext {

	private ClientService client;
	private MoneyService withdrawalAmount;
}
