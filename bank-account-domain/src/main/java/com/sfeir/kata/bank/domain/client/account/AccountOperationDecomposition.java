package com.sfeir.kata.bank.domain.client.account;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.client.account.operation.Operation;
import com.sfeir.kata.bank.domain.client.account.operation.OperationHistoryService;
import com.sfeir.kata.bank.domain.client.account.operation.factory.OperationFactory;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

import io.vavr.Function0;
import io.vavr.Function1;
import io.vavr.control.Try;

interface AccountOperationDecomposition {

		OperationHistoryService getHistory();

		Function0<MoneyService> getBalance();

		default Function1<MoneyService, Operation>
		    initDeposit() {
				return amount -> OperationFactory.createDeposit(amount,
				                                                this.getBalance()
				                                                    .apply());
		}

		default Function1<MoneyService, Operation>
		    initWithdrawal() {
				return amount -> OperationFactory.createWithdrawal(amount,
				                                                   this.getBalance()
				                                                       .apply());
		}

		default Function1<Operation, Boolean> save() {
				return this.getHistory().addOperation();
		}

		default Function0<Integer> countOperations() {
				return () -> this.getHistory()
				                 .getOperations()
				                 .size();
		}

		default Function1<Integer, Integer>
		    getLastOperationIndex() {
				return numberOfOperations -> numberOfOperations - 1;
		}

		default Function1<Integer, Try<Operation>>
		    getLastOperation() {
				return lastOperationIndex -> Try.of(() -> this.getHistory()
				                                              .getOperations()
				                                              .get(lastOperationIndex));
		}

		default Function1<Try<Operation>, MoneyService>
		    getBalanceFromLastOperation() {
				return lastOperation -> lastOperation.map(Operation::getBalanceResult)
				                                     .getOrElse(BankMoneyFactory.create(BigDecimal.ZERO));
		}
}
