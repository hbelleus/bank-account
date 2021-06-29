package com.sfeir.kata.bank.domain.client.account;

import java.math.BigDecimal;
import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.client.account.operation.Deposit;
import com.sfeir.kata.bank.domain.client.account.operation.OperationService;
import com.sfeir.kata.bank.domain.client.account.operation.Withdrawal;
import com.sfeir.kata.bank.domain.client.account.operation.history.OperationHistoryService;
import com.sfeir.kata.bank.domain.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.client.account.statement.factory.AccountStatementFactory;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import io.vavr.Function0;

public interface AccountService {

		OperationHistoryService getHistory();

		default Function0<MoneyService> getBalance() {
				return () -> this.getHistory()
				                 .getLastOperation()
				                 .apply()
				                 .map(OperationService::getBalance)
				                 .orElse(MoneyFactory.create(BigDecimal.ZERO));
		}

		default Consumer<MoneyService> deposit() {
				return amount -> {
						final var deposit = Deposit.builder()
						                           .amount(amount)
						                           .balance(this.getBalance()
						                                        .apply())
						                           .build();

						this.getHistory()
						    .addOperation()
						    .accept(deposit);
				};
		}

		default Consumer<MoneyService> withdraw() {
				return amount -> {
						var withdrawal = Withdrawal.builder()
						                           .amount(amount)
						                           .balance(this.getBalance()
						                                        .apply())
						                           .build();
						this.getHistory()
						    .addOperation()
						    .accept(withdrawal);
				};
		}

		default Function0<AccountStatementService>
		    generateStatement() {
				return () -> AccountStatementFactory.createStatement()
				                                    .apply(this.getHistory());
		}
}
