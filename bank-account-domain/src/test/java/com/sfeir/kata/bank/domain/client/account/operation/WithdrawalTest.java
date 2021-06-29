package com.sfeir.kata.bank.domain.client.account.operation;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.client.account.operation.specification.exception.UnauthorizedOperationException;
import com.sfeir.kata.bank.domain.money.MoneyService;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

import io.vavr.Function0;

@RunWith(JUnitPlatform.class)
class WithdrawalTest {

		@BeforeEach
		public void init() {

		}

		@Test
		void givenAmountOf100AndInitialBalanceOf1000_WhenWithdrawal_thenResultingBalanceIs900() {

				// GIVEN
				var balance = MoneyFactory.create(1000);
				var amount = MoneyFactory.create(BigDecimal.valueOf(100));

				// WHEN
				var result = Withdrawal.builder()
				                       .amount(amount)
				                       .balance(balance)
				                       .build();

				// THEN
				var expectedValue = MoneyFactory.create(900);

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

		@Test
		void givenAnyPositiveAmountGreaterThanBalance_WhenWithdrawal_thenThrowsException() {

				// GIVEN
				var balance = MoneyFactory.create(1000);
				var amount = MoneyFactory.create(1500);

				Condition<MoneyService> greatherThanBalance = new Condition<>((money) -> money.isLargerThan()
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
