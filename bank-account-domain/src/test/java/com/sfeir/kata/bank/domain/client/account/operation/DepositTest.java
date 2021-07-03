package com.sfeir.kata.bank.domain.client.account.operation;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

class DepositTest {

		@BeforeEach
		public void init() {

		}

		@DisplayName("Testing Deposit instantiation")
		@Test
		void givenAmountOf100AndInitialBalanceOf1000_WhenWithdrawal_thenResultingBalanceIs900() {

				// GIVEN
				var balance = MoneyFactory.create(1000);
				var amount = MoneyFactory.create(BigDecimal.valueOf(100));

				// WHEN
				var result = Deposit.builder()
				                    .amount(amount)
				                    .balance(balance)
				                    .build();

				// THEN
				var expectedValue = MoneyFactory.create(1100);

				Condition<Deposit> isAmountCorrect = new Condition<>((deposit) -> deposit.getAmount()
				                                                                         .equals(amount), "checking if saved operation has the correct amount "
				                                                                             + amount);

				Condition<Deposit> isBalanceCorrect = new Condition<>((deposit) -> deposit.getBalance()
				                                                                          .equals(expectedValue), "checking if operation has the correct resulting balance "
				                                                                              + balance);

				Assertions.assertThat(result)
				          .has(isAmountCorrect)
				          .has(isBalanceCorrect);

		}
}
