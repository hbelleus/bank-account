package com.sfeir.kata.bank.domain.client;

import java.util.function.Consumer;
import java.util.function.Function;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.factory.OperationFactory;
import com.sfeir.kata.bank.domain.operation.factory.OperationHistory;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;
import com.sfeir.kata.bank.domain.statement.AccountStatement;
import com.sfeir.kata.bank.domain.statement.factory.AccountStatementFactory;

import io.vavr.Function1;
import io.vavr.Function2;

interface IClientOperationDecomposition {

		Account getAccount();

		IStatementPrinter getPrinter();

		default Function2<Money, Account, Operation> deposit() {
				return OperationFactory::createDeposit;
		}

		default Function2<Money, Account, Operation>
		    withdrawal() {
				return OperationFactory::createWithdrawal;
		}

		default Function1<Operation, Boolean> saveOperation() {
				return this.getAccount()
				           .getHistory()
				           .getOperations()::add;
		}

		default Function<OperationHistory, AccountStatement>
		    generateStatement() {
				return AccountStatementFactory::createStatement;
		}

		default Consumer<AccountStatement> printStatement() {
				return this.getPrinter()::print;
		}

}
