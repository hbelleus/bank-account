package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.factory.BankAccountFactory;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

@RunWith(JUnitPlatform.class)
class AccountBalanceTest {

		private AccountService account;

		@BeforeEach
		public void init() {

				account = BankAccountFactory.create();

				Assumptions.assumeThat(account.getBalance()
				                              .apply()
				                              .getAmount())
				           .isEqualTo(BigDecimal.ZERO);
		}

		@Test
		void givenOneDepositOf1000_whenGettingBalance_ThenBalanceIs1000() {

				// GIVEN
				var initialDeposit = BankMoneyFactory.create(1000);

				var isOperationSaved = account.deposit()
				                              .apply(initialDeposit);

				Assumptions.assumeThat(isOperationSaved).isTrue();

				var expectedValue = BankMoneyFactory.create(1000);

				// WHEN
				var balanceResult = this.account.getBalance()
				                                .apply();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

		@Test
		void givenOneDepositOf1000AndAWithDrawalOf500_whenGettingBalance_ThenBalanceIs500() {

				// GIVEN
				var initialDeposit = BankMoneyFactory.create(1000);
				var withdrawal = BankMoneyFactory.create(500);

				Assumptions.assumeThat(account.deposit()
				                              .apply(initialDeposit))
				           .isTrue();
				Assumptions.assumeThat(account.withdraw()
				                              .apply(withdrawal))
				           .isTrue();

				var expectedValue = BankMoneyFactory.create(500);

				// WHEN
				var balanceResult = account.getBalance().apply();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);

		}

		@Test
		void givenOneDepositOf1000And2WithDrawalsOf200_whenGettingBalance_ThenBalanceIs600() {

				// GIVEN
				var initialDeposit = BankMoneyFactory.create(1000);
				var withdrawal = BankMoneyFactory.create(200);

				List.of(account.deposit().apply(initialDeposit),
				        account.withdraw().apply(withdrawal),
				        account.withdraw().apply(withdrawal))
				    .stream()
				    .forEach(isOperationSaved -> Assumptions.assumeThat(isOperationSaved)
				                                            .isTrue());

				var expectedValue = BankMoneyFactory.create(600);

				// WHEN
				var balanceResult = this.account.getBalance()
				                                .apply();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

		@Test
		void givenOneDepositOf1000And2WithDrawalsOf200AndOneDepositOf100_whenGettingBalance_ThenBalanceIs700() {

				// GIVEN
				var initialDeposit = BankMoneyFactory.create(1000);
				var withdrawal = BankMoneyFactory.create(200);
				var finalDeposit = BankMoneyFactory.create(100);

				List.of(account.deposit().apply(initialDeposit),
				        account.withdraw().apply(withdrawal),
				        account.withdraw().apply(withdrawal),
				        account.deposit().apply(finalDeposit))
				    .stream()
				    .forEach(isOperationSaved -> Assumptions.assumeThat(isOperationSaved)
				                                            .isTrue());

				var expectedValue = BankMoneyFactory.create(700);

				// WHEN
				var balanceResult = this.account.getBalance()
				                                .apply();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

}
