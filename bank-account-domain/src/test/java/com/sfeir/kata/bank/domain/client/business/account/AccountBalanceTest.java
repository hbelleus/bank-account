package com.sfeir.kata.bank.domain.client.business.account;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.simple.account.Account;

class AccountBalanceTest {

		private Account account;

		@BeforeEach
		public void init() throws IllegalAccessException {

				account = new Account();

				Assumptions.assumeThat(account.getBalance()
				                              .getAmount())
				           .isEqualTo(BigDecimal.ZERO);
		}

		@Test
		void givenOneDepositOf1000_whenGettingBalance_ThenBalanceIs1000()
		    throws IllegalAccessException {

				// GIVEN
				var initialDeposit = Money.of("1000");

				account.deposit(initialDeposit);

				var expectedValue = Money.of("1000");

				// WHEN
				var balanceResult = this.account.getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

		@Test
		void givenOneDepositOf1000AndAWithDrawalOf500_whenGettingBalance_ThenBalanceIs500()
		    throws IllegalAccessException {

				// GIVEN
				var initialDeposit = Money.of("1000");
				var withdrawal = Money.of("500");

				account.deposit(initialDeposit);
				account.withdraw(withdrawal);

				var expectedValue = Money.of("500");

				// WHEN
				var balanceResult = account.getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);

		}

		@Test
		void givenOneDepositOf1000And2WithDrawalsOf200_whenGettingBalance_ThenBalanceIs600()
		    throws IllegalAccessException {

				// GIVEN
				var initialDeposit = Money.of("1000");
				var withdrawal = Money.of("200");

				account.deposit(initialDeposit);
				account.withdraw(withdrawal);
				account.withdraw(withdrawal);

				var expectedValue = Money.of("600");

				// WHEN
				var balanceResult = this.account.getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

		@Test
		void givenOneDepositOf1000And2WithDrawalsOf200AndOneDepositOf100_whenGettingBalance_ThenBalanceIs700()
		    throws IllegalAccessException {

				// GIVEN
				var initialDeposit = Money.of("1000");
				var withdrawal = Money.of("200");
				var finalDeposit = Money.of("100");

				account.deposit(initialDeposit);
				account.withdraw(withdrawal);
				account.withdraw(withdrawal);
				account.deposit(finalDeposit);

				var expectedValue = Money.of("700");

				// WHEN
				var balanceResult = this.account.getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

}
