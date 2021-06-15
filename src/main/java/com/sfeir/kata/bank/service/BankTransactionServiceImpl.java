package com.sfeir.kata.bank.service;

import com.sfeir.kata.bank.domain.BankAccount;
import com.sfeir.kata.bank.domain.BankTransaction;
import com.sfeir.kata.bank.domain.BankTransactionContext;
import com.sfeir.kata.bank.domain.BankTransactionType;

import io.vavr.control.Try;

public class BankTransactionServiceImpl implements BankTransactionService {

	public boolean deposit(String amount, BankAccount account) {

		return this.executeTransaction(amount, account, BankTransactionType.DEPOSIT);
	}

	private boolean executeTransaction(String amount, BankAccount account, BankTransactionType operationType) {

		return this.initTransaction(amount, account, operationType).andThen(this::runTransaction)
				.flatMap(this::saveTransaction).getOrElse(false);
	}

	private Try<BankTransactionContext> initTransaction(String amount, BankAccount account,
			BankTransactionType transactionType) {

		final var transaction = BankTransaction.of(amount, transactionType);

		return Try.of(() -> BankTransactionContext.of(transaction, account));
	}

	private void runTransaction(BankTransactionContext context) {

		Try.run(() -> this.calculateAccountBalance(context).andThen(this::updateAccountBalance));
	}

	private Try<BankTransactionContext> calculateAccountBalance(BankTransactionContext context) {

		var transaction = context.getTransaction();
		var account = context.getAccount();

		return Try.of(() -> transaction.getType().run(transaction.getAmount(), account.getBalance()))
				.map(context::setBalanceAfterTransaction);
	}

	private void updateAccountBalance(BankTransactionContext context) {

		var account = context.getAccount();

		account.setBalance(context.getBalanceAfterTransaction());
	}

	private Try<Boolean> saveTransaction(BankTransactionContext context) {

		var transaction = context.getTransaction();
		var account = context.getAccount();

		return Try.of(() -> account.getHistory().add(transaction));
	}
}
