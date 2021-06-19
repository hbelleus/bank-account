package com.sfeir.kata.bank.domain.client;

import java.util.function.Consumer;
import java.util.function.Function;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationFactory;
import com.sfeir.kata.bank.domain.operation.OperationHistory;
import com.sfeir.kata.bank.domain.printer.StatementPrinter;
import com.sfeir.kata.bank.domain.statement.AccountStatement;
import com.sfeir.kata.bank.domain.statement.utils.AccountStatementFactory;

import io.vavr.Function1;
import io.vavr.Function2;

public interface ClientOperationDecomposition {

	Account getAccount();

	StatementPrinter getPrinter();

	default Function2<Money, Account, Operation> deposit() {
		return OperationFactory::createDeposit;
	}

	default Function2<Money, Account, Operation> withdrawal() {
		return OperationFactory::createWithdrawal;
	}

	default Function1<Operation, Boolean> saveOperation() {
		return this.getAccount().getHistory().getOperations()::add;
	}

	default Function<OperationHistory, AccountStatement> generateStatement() {
		return AccountStatementFactory::createStatement;
	}

	default Consumer<AccountStatement> printStatement() {
		return this.getPrinter()::print;
	}

}
