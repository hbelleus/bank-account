package com.sfeir.kata.bank.domain.client;

import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationFactory;
import com.sfeir.kata.bank.domain.operation.OperationHistory;
import com.sfeir.kata.bank.domain.operation.OperationType;
import com.sfeir.kata.bank.domain.statement.AccountStatement;
import com.sfeir.kata.bank.domain.statement.StatementPrinter;
import com.sfeir.kata.bank.domain.statement.utils.AccountStatementFactory;

import io.vavr.Function1;
import io.vavr.Function2;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Client implements ClientOperation {

	private Account account;

	private StatementPrinter printer;

	@Override
	public boolean deposit(Money amount) {

		var operation = this.initOperation(amount, OperationType.DEPOSIT);

		return this.runOperation(operation);

	}

	@Override
	public boolean withdrawal(Money amount) {

		var operation = this.initOperation(amount.toNegative(), OperationType.WITHDRAWAL);

		return this.runOperation(operation);
	}

	private Operation initOperation(Money amount, OperationType operationtype) {

		return OperationFactory.create(amount, this.account, operationtype);
	}

	private Boolean runOperation(Operation operation) {

		this.updateAccountBalance().accept(operation.getBalanceResult());

		return this.saveOperation().apply(this.account.getHistory(), operation);
	}

	private Consumer<Money> updateAccountBalance() {
		return this.account::setBalance;
	}

	private Function2<OperationHistory, Operation, Boolean> saveOperation() {
		return (history, operation) -> history.getOperations().add(operation);
	}

	@Override
	public void printOperationHistory() {

		if (this.account.getHistory().isEmpty())

			printMessage().accept("No Operation");
		else {

			var statement = createStatement().apply(this.account.getHistory());

			printStatement().accept(statement);
		}
	}

	private Function1<OperationHistory, AccountStatement> createStatement() {
		return AccountStatementFactory::createStatement;
	}

	private Consumer<AccountStatement> printStatement() {
		return this.printer::print;
	}

	private Consumer<String> printMessage() {
		return this.printer::print;
	}
}
