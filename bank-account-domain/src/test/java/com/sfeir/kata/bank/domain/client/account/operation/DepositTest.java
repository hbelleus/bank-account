package com.sfeir.kata.bank.domain.client.account.operation;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

@RunWith(JUnitPlatform.class)
class DepositTest {

		@BeforeEach
		public void init() {

		}

		@Test
		void givenAmountOf100AndInitialBalanceOf1000_WhenWithdrawal_thenResultingBalanceIs900() {

				// GIVEN
				var balance = BankMoneyFactory.create(1000);
				var amount = BankMoneyFactory.create(BigDecimal.valueOf(100));

				// WHEN
				var result = new NewDeposit(amount, balance);

				// THEN
				var expectedValue = BankMoneyFactory.create(1100);

				Condition<NewDeposit> isAmountCorrect = new Condition<>((deposit) -> deposit.getAmount()
				                                                                            .equals(amount), "checking if saved operation has the correct amount "
				                                                                                + amount);

				Condition<NewDeposit> isBalanceCorrect = new Condition<>((deposit) -> deposit.getBalance()
				                                                                             .equals(expectedValue), "checking if operation has the correct resulting balance "
				                                                                                 + balance);

				Assertions.assertThat(result)
				          .has(isAmountCorrect)
				          .has(isBalanceCorrect);

		}
}
