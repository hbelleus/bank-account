package com.sfeir.kata.bank.domain.client.account.factory;

import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.operation.Deposit;
import com.sfeir.kata.bank.domain.client.account.operation.OperationService;
import com.sfeir.kata.bank.domain.client.account.operation.Withdrawal;
import com.sfeir.kata.bank.domain.client.account.operation.history.OperationHistoryService;
import com.sfeir.kata.bank.domain.client.account.operation.history.factory.OperationHistoryFactory;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.account.statement.factory.AccountStatementFactory;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import io.vavr.Function0;
import lombok.Value;

@Value
class Account implements AccountService {

		OperationHistoryService history;

		Account() {
				this.history = OperationHistoryFactory.initializeHistory()
				                                      .apply();
		}

		@Override
		public Function0<MoneyService> getBalance() {
				return () -> this.getHistory()
				                 .getLastOperation()
				                 .apply()
				                 .map(OperationService::getBalance)
				                 .orElse(MoneyFactory.zero());
		}

		@Override
		public Consumer<MoneyService> deposit() {
				return amount -> {
						this.getHistory()
						    .addOperation()
						    .accept(Deposit.builder()
						                   .amount(amount)
						                   .balance(this.getBalance()
						                                .apply())
						                   .build());

				};
		}

		@Override
		public Consumer<MoneyService> withdraw() {
				return amount -> {
						this.getHistory()
						    .addOperation()
						    .accept(Withdrawal.builder()
						                      .amount(amount)
						                      .balance(this.getBalance()
						                                   .apply())
						                      .build());
				};
		}

		@Override
		public Function0<AccountStatementService>
		    generateStatement() {
				return () -> AccountStatementFactory.createStatement()
				                                    .apply(this.getHistory());
		}

}
