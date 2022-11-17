package com.sfeir.kata.bank.domain.ddd.business.client.account;

import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.Deposit;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.OperationService;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.Withdrawal;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.history.OperationHistoryService;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.history.factory.OperationHistoryFactory;
import com.sfeir.kata.bank.domain.ddd.reporting.client.account.AccountReportingService;

import io.vavr.Function0;
import lombok.Value;

@Value
public class Account implements AccountOperationService,
    AccountReportingService {

		OperationHistoryService history;

		public Account() {
				this.history = OperationHistoryFactory.initializeHistory()
				                                      .apply();
		}

		@Override
		public Function0<Money> getBalance() {
				return () -> this.getHistory()
				                 .getLastOperation()
				                 .apply()
				                 .map(OperationService::getBalance)
				                 .orElse(Money.zero());
		}

		@Override
		public Consumer<Money> deposit() {
				return amount -> this.getHistory()
				                     .addOperation()
				                     .accept(Deposit.builder()
				                                    .amount(amount)
				                                    .balance(this.getBalance()
				                                                 .apply())
				                                    .build());
		}

		@Override
		public Consumer<Money> withdraw() {
				return amount -> this.getHistory()
				                     .addOperation()
				                     .accept(Withdrawal.builder()
				                                       .amount(amount)
				                                       .balance(this.getBalance()
				                                                    .apply())
				                                       .build());
		}

}
