package com.sfeir.kata.bank.withdrawal;

interface WithdrawalFunctionalTest {

	void givenPositiveAmountInLimit_whenWithdraw_thenAccountBalanceIsUpdated(String amount, String expectedValue);
	
	void givenPositiveAmountInLimit_whenWithdraw_thenAccountBalanceIsSaved(String amount);
	
	void givenPositiveAmountOutOfLimit_whenWithdraw_thenTransactionFailed(String amount, String expectedValue);
}
