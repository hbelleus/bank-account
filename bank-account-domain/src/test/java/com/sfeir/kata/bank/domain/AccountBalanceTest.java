package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.account.factory.BankAccountFactory;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

@RunWith(JUnitPlatform.class)
class AccountBalanceTest {

		private IAccountOperator accountOperator;

		@BeforeEach
		public void init() {

				accountOperator = BankAccountFactory.create();

				Assumptions.assumeThat(accountOperator.getBalance()
				                                      .getAmount())
				           .isEqualTo(BigDecimal.ZERO);
		}

		@Test
		void givenOneDepositOf1000_whenGettingBalance_ThenBalanceIs1000() {

				// GIVEN
				var initialDeposit = BankMoneyFactory.create(1000);

				var isOperationSaved = accountOperator.deposit(initialDeposit);

				Assumptions.assumeThat(isOperationSaved).isTrue();

				var expectedValue = BankMoneyFactory.create(1000);

				// WHEN
				var balanceResult = this.accountOperator.getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

		@Test
		void givenOneDepositOf1000AndAWithDrawalOf500_whenGettingBalance_ThenBalanceIs500() {

				// GIVEN
				var initialDeposit = BankMoneyFactory.create(1000);
				var withdrawal = BankMoneyFactory.create(500);

				Assumptions.assumeThat(accountOperator.deposit(initialDeposit))
				           .isTrue();
				Assumptions.assumeThat(accountOperator.withdraw(withdrawal))
				           .isTrue();

				var expectedValue = BankMoneyFactory.create(500);

				// WHEN
				var balanceResult = accountOperator.getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);

		}

		@Test
		void givenOneDepositOf1000And2WithDrawalsOf200_whenGettingBalance_ThenBalanceIs600() {

				// GIVEN
				var initialDeposit = BankMoneyFactory.create(1000);
				var withdrawal = BankMoneyFactory.create(200);

				List.of(accountOperator.deposit(initialDeposit),
				        accountOperator.withdraw(withdrawal),
				        accountOperator.withdraw(withdrawal))
				    .stream()
				    .forEach(isOperationSaved -> Assumptions.assumeThat(isOperationSaved)
				                                            .isTrue());

				var expectedValue = BankMoneyFactory.create(600);

				// WHEN
				var balanceResult = this.accountOperator.getBalance();

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

				List.of(accountOperator.deposit(initialDeposit),
				        accountOperator.withdraw(withdrawal),
				        accountOperator.withdraw(withdrawal),
				        accountOperator.deposit(finalDeposit))
				    .stream()
				    .forEach(isOperationSaved -> Assumptions.assumeThat(isOperationSaved)
				                                            .isTrue());

				var expectedValue = BankMoneyFactory.create(700);

				// WHEN
				var balanceResult = this.accountOperator.getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

}
