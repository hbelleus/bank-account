package com.sfeir.kata.bank.domain;

import java.math.BigDecimal;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.client.IClientOperatior;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.utils.BankClientMockFactory;

@RunWith(JUnitPlatform.class)
class AccountBalanceTest {

		private IClientOperatior clientOperator;

		@BeforeEach
		public void init() {

				clientOperator = BankClientMockFactory.create();

				Assumptions.assumeThat(clientOperator.getAccount()
				                                     .getBalance()
				                                     .getAmount())
				           .isEqualTo(BigDecimal.ZERO);
		}

		@Test
		void givenOneDepositOf1000_whenGettingBalance_ThenBalanceIs1000() {

				// GIVEN
				Money initialDeposit = Money.of(BigDecimal.valueOf(1000));

				var isOperationSaved = clientOperator.deposit(initialDeposit);

				Assumptions.assumeThat(isOperationSaved).isTrue();

				Money expectedValue = Money.of(BigDecimal.valueOf(1000));

				// WHEN
				var balanceResult = this.clientOperator.getAccount()
				                                       .getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

		@Test
		void givenOneDepositOf1000AndAWithDrawalOf500_whenGettingBalance_ThenBalanceIs500() {

				// GIVEN
				Money initialDeposit = Money.of(BigDecimal.valueOf(1000));
				Money withdrawal = Money.of(BigDecimal.valueOf(500));

				var isFirstOperationSaved = clientOperator.deposit(initialDeposit);
				var isSecondOperationSaved = clientOperator.withdraw(withdrawal);

				Assumptions.assumeThat(isFirstOperationSaved)
				           .isTrue();
				Assumptions.assumeThat(isSecondOperationSaved)
				           .isTrue();

				Money expectedValue = Money.of(BigDecimal.valueOf(500));

				// WHEN
				var balanceResult = this.clientOperator.getAccount()
				                                       .getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);

		}

		@Test
		void givenOneDepositOf1000And2WithDrawalsOf200_whenGettingBalance_ThenBalanceIs600() {

				// GIVEN
				Money initialDeposit = Money.of(BigDecimal.valueOf(1000));
				Money withdrawal = Money.of(BigDecimal.valueOf(200));

				var isFirstOperationSaved = clientOperator.deposit(initialDeposit);
				var isSecondOperationSaved = clientOperator.withdraw(withdrawal);
				var isThirdOperationSaved = clientOperator.withdraw(withdrawal);

				List.of(isFirstOperationSaved,
				        isSecondOperationSaved,
				        isThirdOperationSaved)
				    .stream()
				    .forEach(isOperationSaved -> Assumptions.assumeThat(isOperationSaved)
				                                            .isTrue());

				Money expectedValue = Money.of(BigDecimal.valueOf(600));

				// WHEN
				var balanceResult = this.clientOperator.getAccount()
				                                       .getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

		@Test
		void givenOneDepositOf1000And2WithDrawalsOf200AndOneDepositOf100_whenGettingBalance_ThenBalanceIs700() {

				// GIVEN
				Money initialDeposit = Money.of(BigDecimal.valueOf(1000));
				Money withdrawal = Money.of(BigDecimal.valueOf(200));
				Money finalDeposit = Money.of(BigDecimal.valueOf(100));

				var isFirstOperationSaved = clientOperator.deposit(initialDeposit);
				var isSecondOperationSaved = clientOperator.withdraw(withdrawal);
				var isThirdOperationSaved = clientOperator.withdraw(withdrawal);
				var isFourthOperationSaved = clientOperator.deposit(finalDeposit);

				List.of(isFirstOperationSaved,
				        isSecondOperationSaved,
				        isThirdOperationSaved,
				        isFourthOperationSaved)
				    .stream()
				    .forEach(isOperationSaved -> Assumptions.assumeThat(isOperationSaved)
				                                            .isTrue());

				Money expectedValue = Money.of(BigDecimal.valueOf(700));

				// WHEN
				var balanceResult = this.clientOperator.getAccount()
				                                       .getBalance();

				// THEN
				Assertions.assertThat(balanceResult)
				          .isEqualTo(expectedValue);
		}

}
