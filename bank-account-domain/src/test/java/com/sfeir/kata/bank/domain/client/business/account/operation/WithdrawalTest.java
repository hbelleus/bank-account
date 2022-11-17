package com.sfeir.kata.bank.domain.client.business.account.operation;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.simple.account.operation.Withdrawal;
import com.sfeir.kata.bank.domain.simple.account.operation.specification.exception.UnauthorizedOperationException;

import io.vavr.Function0;

class WithdrawalTest {

		@BeforeEach
		public void init() {

		}

		@DisplayName("Testing successful Withdrawal instantiation")
		@Test
		void givenAmountOf100AndInitialBalanceOf1000_WhenWithdrawal_thenResultingBalanceIs900() {

				// GIVEN
				var balance = Money.of("1000");
				var amount = Money.of("100");

				// WHEN
				var result = Withdrawal.builder()
				                       .amount(amount)
				                       .balance(balance)
				                       .build();

				// THEN
				var expectedValue = Money.of("900");

				Condition<Withdrawal> isAmountCorrect = new Condition<>((withdrawal) -> withdrawal.getAmount()
				                                                                                  .equals(amount.toNegative()
				                                                                                                .apply()), "checking if saved operation has the correct amount "
				                                                                                                    + amount);

				Condition<Withdrawal> isBalanceCorrect = new Condition<>((withdrawal) -> withdrawal.getBalance()
				                                                                                   .equals(expectedValue), "checking if operation has the correct resulting balance "
				                                                                                       + balance);

				Assertions.assertThat(result)
				          .has(isAmountCorrect)
				          .has(isBalanceCorrect);

		}

		@DisplayName("Testing Withdrawal instantiation with a too large unauthorized amount")
		@Test
		void givenAnyPositiveAmountGreaterThanBalance_WhenWithdrawal_thenThrowsException() {

				// GIVEN
				var balance = Money.of("1000");
				var amount = Money.of("1500");

				Condition<Money> greatherThanBalance = new Condition<>((money) -> money.isAbsolutelyMoreThan()
				                                                                       .apply(balance), "checking if amount is greater than balance "
				                                                                           + balance);

				Assumptions.assumeThat(amount)
				           .is(greatherThanBalance);

				// WHEN
				Function0<Withdrawal> withdrawal = () -> Withdrawal.builder()
				                                                   .amount(amount)
				                                                   .balance(balance)
				                                                   .build();

				// THEN
				org.junit.jupiter.api.Assertions.assertThrows(UnauthorizedOperationException.class,
				                                              withdrawal::apply);
		}
}
