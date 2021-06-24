package com.sfeir.kata.bank.domain.client.account;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.client.account.operation.OperationHistoryService;
import com.sfeir.kata.bank.domain.client.account.operation.factory.Operation;
import com.sfeir.kata.bank.domain.client.account.operation.factory.OperationFactory;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.account.statement.factory.AccountStatementFactory;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

import io.vavr.Function0;
import io.vavr.Function1;

public interface AccountService {

		OperationHistoryService getHistory();

		default Function0<MoneyService> getBalance() {
				return () -> this.getHistory()
				                 .getLastOperation()
				                 .apply()
				                 .map(Operation::getBalance)
				                 .orElse(BankMoneyFactory.create(BigDecimal.ZERO));
		}

		default Function1<MoneyService, Boolean> deposit() {
				return amount -> OperationFactory.initDeposit()
				                                 .andThen(this.getHistory()
				                                              .addOperation())
				                                 .apply(amount,
				                                        this.getBalance()
				                                            .apply());
		}

		default Function1<MoneyService, Boolean> withdraw() {
				return amount -> OperationFactory.initWithdrawal()
				                                 .andThen(this.getHistory()
				                                              .addOperation())
				                                 .apply(amount,
				                                        this.getBalance()
				                                            .apply());
		}

		default Function0<AccountStatementService>
		    generateStatement() {
				return () -> AccountStatementFactory.createStatement(this.getHistory());
		}
}
