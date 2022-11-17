package com.sfeir.kata.bank.domain.ddd.business.client.account;

import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.common.money.Money;

public interface AccountOperationService
    extends AccountService {

		Consumer<Money> deposit();

		Consumer<Money> withdraw();
}
