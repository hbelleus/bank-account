package com.sfeir.kata.bank.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BankClient {

	private String firstName;
	private String lastName;
	private String email;
	private BankAccount account;

}
