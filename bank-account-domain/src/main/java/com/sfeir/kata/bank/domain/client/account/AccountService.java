package com.sfeir.kata.bank.domain.client.account;

import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.account.statement.factory.AccountStatementFactory;
import com.sfeir.kata.bank.domain.money.MoneyService;

import io.vavr.Function0;
import io.vavr.Function1;

public interface AccountService
    extends AccountOperationDecomposition {

		default Function1<MoneyService, Boolean> deposit() {
				return initDeposit().andThen(save());
		}

		default Function1<MoneyService, Boolean> withdraw() {
				return initWithdrawal().andThen(save());
		}

		default Function0<AccountStatementService>
		    generateStatement() {
				return () -> AccountStatementFactory.createStatement(this.getHistory());
		}

		default Function0<MoneyService> getBalance() {

				return this.countOperations()
				           .andThen(getLastOperationIndex())
				           .andThen(getLastOperation())
				           .andThen(getBalanceFromLastOperation())
				           .curried();
		}

}
