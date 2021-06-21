package com.sfeir.kata.bank.domain.account;

import java.math.BigDecimal;
import java.util.function.Consumer;
import java.util.function.Function;

import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.factory.OperationFactory;
import com.sfeir.kata.bank.domain.operation.factory.OperationHistory;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;
import com.sfeir.kata.bank.domain.statement.AccountStatement;
import com.sfeir.kata.bank.domain.statement.factory.AccountStatementFactory;

import io.vavr.Function1;
import io.vavr.Function2;
import io.vavr.control.Try;

interface IAccountOperationDecomposition {

		OperationHistory getHistory();

		default Function2<Money, Money, Operation> deposit() {
				return OperationFactory::createDeposit;
		}

		default Function2<Money, Money, Operation>
		    withdrawal() {
				return OperationFactory::createWithdrawal;
		}

		default Function1<Operation, Boolean> saveOperation() {
				return this.getHistory().getOperations()::add;
		}

		default Function<OperationHistory, AccountStatement>
		    generateStatement() {
				return AccountStatementFactory::createStatement;
		}

		default Consumer<AccountStatement>
		    printStatement(IStatementPrinter printer) {
				return printer::print;
		}

		default Money getBalance() {

				var numberOfOperations = this.getHistory()
				                             .getOperations()
				                             .size();

				var lastOperationIndex = numberOfOperations - 1;

				var lastOperation = Try.of(() -> this.getHistory()
				                                     .getOperations()
				                                     .get(lastOperationIndex));

				return lastOperation.map(Operation::getBalanceResult)
				                    .getOrElse(Money.of(BigDecimal.ZERO));
		}
}
