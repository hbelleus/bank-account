package com.sfeir.kata.bank.domain.client.business.account.operation;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.simple.account.operation.Deposit;

class DepositTest {

		@BeforeEach
		public void init() {

		}

		@DisplayName("Testing Deposit instantiation")
		@Test
		void givenAmountOf100AndInitialBalanceOf1000_WhenWithdrawal_thenResultingBalanceIs900() {

				// GIVEN
				var balance = Money.of("1000");
				var amount = Money.of("100");

				// WHEN
				var result = Deposit.builder()
				                    .amount(amount)
				                    .balance(balance)
				                    .build();

				// THEN
				var expectedValue = Money.of("1100");

				Condition<Deposit> isAmountCorrect = new Condition<>((deposit) -> deposit.getAmount()
				                                                                         .equals(amount), "checking if saved operation has the correct amount "
				                                                                             + amount);

				Condition<Deposit> isBalanceCorrect = new Condition<>((deposit) -> deposit.getBalance()
				                                                                          .equals(expectedValue), "checking if operation has the correct resulting balance "
				                                                                              + balance);

				Assertions.assertThat(result)
				          .is(isAmountCorrect)
				          .is(isBalanceCorrect);

		}
}
