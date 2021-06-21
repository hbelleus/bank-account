package com.sfeir.kata.bank.domain.account;

import java.util.function.Consumer;
import java.util.function.Function;

import com.sfeir.kata.bank.domain.money.IMoneyOperator;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationHistory;
import com.sfeir.kata.bank.domain.operation.factory.OperationFactory;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;
import com.sfeir.kata.bank.domain.statement.IAccountStatement;
import com.sfeir.kata.bank.domain.statement.factory.AccountStatementFactory;

import io.vavr.Function1;
import io.vavr.Function2;

interface IAccountOperationDecomposition {

		OperationHistory getHistory();

		default
		    Function2<IMoneyOperator, IMoneyOperator, Operation>
		    deposit() {
				return OperationFactory::createDeposit;
		}

		default
		    Function2<IMoneyOperator, IMoneyOperator, Operation>
		    withdrawal() {
				return OperationFactory::createWithdrawal;
		}

		default Function1<Operation, Boolean> saveOperation() {
				return this.getHistory().getOperations()::add;
		}

		default Function<OperationHistory, IAccountStatement>
		    generateStatement() {
				return AccountStatementFactory::createStatement;
		}

		default Consumer<IAccountStatement>
		    printStatement(IStatementPrinter printer) {
				return printer::print;
		}
}