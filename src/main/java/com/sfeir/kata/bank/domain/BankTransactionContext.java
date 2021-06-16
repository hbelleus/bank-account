package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;

import com.sfeir.kata.bank.exception.TransactionException;

import io.vavr.Function1;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class BankTransactionContext implements BankTransactionValidator {

	@NonNull
	private BankTransaction transaction;
	@NonNull
	private BankAccount account;
	@Setter
	private String balanceAfterTransaction;

	public static BankTransactionContext of(BankTransaction transaction, BankAccount account)
			throws TransactionException {

		final var transactionContext = new BankTransactionContext(transaction, account);

		transactionContext.validate();

		return transactionContext;

	}

	public void validate() throws TransactionException {

		Function1<String, BigDecimal> toBigDecimal = BigDecimal::new;

		final var isAmountValid = this.isAmountGreaterThanZero(toBigDecimal.apply(this.transaction.getAmount()));

		final var isDeposit = BankTransactionType.DEPOSIT == transaction.getType();

		final var isTransactionAuthorized = isDeposit || this.isLimitRespected(
				toBigDecimal.apply(this.account.getBalance()), toBigDecimal.apply(this.account.getOverdraft()));

		if (!isAmountValid)
			throw new TransactionException("Transact should have an amount greater than 0");

		else if (!isTransactionAuthorized)
			throw new TransactionException("Transaction is not authorized.");

	}

}
