package com.sfeir.kata.bank.domain.client.account;

import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.client.account.operation.history.OperationHistoryService;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.money.MoneyService;

import io.vavr.Function0;

public interface AccountService {

		OperationHistoryService getHistory();

		Function0<MoneyService> getBalance();

		Consumer<MoneyService> deposit();

		Consumer<MoneyService> withdraw();

		Function0<AccountStatementService> generateStatement();
}
