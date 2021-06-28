package com.sfeir.kata.bank.behaviour.state;

import com.sfeir.kata.bank.domain.client.ClientService;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ClientContext {

	private ClientService client;
}
