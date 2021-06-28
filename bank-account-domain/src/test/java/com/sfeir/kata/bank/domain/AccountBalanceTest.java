package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.factory.AccountFactory;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

@RunWith(JUnitPlatform.class)
class AccountBalanceTest {

	private AccountService account;

	@BeforeEach
	public void init() {

		account = AccountFactory.createAccount().apply();

		Assumptions.assumeThat(account.getBalance().apply().getAmount()).isEqualTo(BigDecimal.ZERO);
	}

	@Test
	void givenOneDepositOf1000_whenGettingBalance_ThenBalanceIs1000() {

		// GIVEN
		var initialDeposit = MoneyFactory.create(1000);

		account.deposit().accept(initialDeposit);

		var expectedValue = MoneyFactory.create(1000);

		// WHEN
		var balanceResult = this.account.getBalance().apply();

		// THEN
		Assertions.assertThat(balanceResult).isEqualTo(expectedValue);
	}

	@Test
	void givenOneDepositOf1000AndAWithDrawalOf500_whenGettingBalance_ThenBalanceIs500() {

		// GIVEN
		var initialDeposit = MoneyFactory.create(1000);
		var withdrawal = MoneyFactory.create(500);

		account.deposit().accept(initialDeposit);
		account.withdraw().accept(withdrawal);

		var expectedValue = MoneyFactory.create(500);

		// WHEN
		var balanceResult = account.getBalance().apply();

		// THEN
		Assertions.assertThat(balanceResult).isEqualTo(expectedValue);

	}

	@Test
	void givenOneDepositOf1000And2WithDrawalsOf200_whenGettingBalance_ThenBalanceIs600() {

		// GIVEN
		var initialDeposit = MoneyFactory.create(1000);
		var withdrawal = MoneyFactory.create(200);

		account.deposit().accept(initialDeposit);
		account.withdraw().accept(withdrawal);
		account.withdraw().accept(withdrawal);

		var expectedValue = MoneyFactory.create(600);

		// WHEN
		var balanceResult = this.account.getBalance().apply();

		// THEN
		Assertions.assertThat(balanceResult).isEqualTo(expectedValue);
	}

	@Test
	void givenOneDepositOf1000And2WithDrawalsOf200AndOneDepositOf100_whenGettingBalance_ThenBalanceIs700() {

		// GIVEN
		var initialDeposit = MoneyFactory.create(1000);
		var withdrawal = MoneyFactory.create(200);
		var finalDeposit = MoneyFactory.create(100);

		account.deposit().accept(initialDeposit);
		account.withdraw().accept(withdrawal);
		account.withdraw().accept(withdrawal);
		account.deposit().accept(finalDeposit);

		var expectedValue = MoneyFactory.create(700);

		// WHEN
		var balanceResult = this.account.getBalance().apply();

		// THEN
		Assertions.assertThat(balanceResult).isEqualTo(expectedValue);
	}

}
