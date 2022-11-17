package com.sfeir.kata.bank.domain.ddd.business.client;

import com.sfeir.kata.bank.domain.ddd.business.client.account.AccountOperationService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ClientOperation
    implements ClientOperationService {

		private final AccountOperationService account;

}
