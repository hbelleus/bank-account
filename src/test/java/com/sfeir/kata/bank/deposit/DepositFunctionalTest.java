package com.sfeir.kata.bank.deposit;

public interface DepositFunctionalTest {

	void givenPositiveAmount_whenDeposit_thenAccountBalanceIsUpdated(String amount, String expectedValue);

	void givenPositiveAmount_whenDeposit_thenTransactionIsSaved(String amount);

	void givenNegativeOrNullAmount_whenDeposit_thenAccountBalanceIsNotUpdated(String amount, String expectedValue);

}
