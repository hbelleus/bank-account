package com.sfeir.kata.bank.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.assertj.core.data.Index;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.account.factory.BankAccountFactory;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;
import com.sfeir.kata.bank.domain.operation.Operation;

@RunWith(JUnitPlatform.class)
class OperationDepositTest {

		private IAccountOperator account;

		@BeforeEach
		public void init() {

				account = BankAccountFactory.create();
		}

		@Test()
		void givenAnyAmount_whenDeposit_thenResultIsBoolean() {

				// GIVEN
				var amount = BankMoneyFactory.create(100);

				// WHEN
				final var result = account.deposit(amount);

				// THEN
				Assertions.assertThat(result)
				          .isInstanceOf(Boolean.class);
		}

		@Test()
		void givenAnyPositiveAmount_whenDeposit_thenOperationIsSaved() {

				// GIVEN
				var amount = BankMoneyFactory.create(100);

				// WHEN
				final var result = account.deposit(amount);

				// THEN

				Condition<Operation> savedOperation = new Condition<>((operation) -> operation.getAmount()
				                                                                              .equals(amount), "checking if saved operation has the correct amount", amount);

				Assertions.assertThat(result).isTrue();
				Assertions.assertThat(account.getHistory()
				                             .getOperations())
				          .isNotEmpty()
				          .has(savedOperation, Index.atIndex(0));
		}

		@Test()
		void givenAnyPositiveAmount_whenDeposit_thenAccountBalanceIsUpdated() {

				// GIVEN
				var amount = BankMoneyFactory.create(100);

				// WHEN
				account.deposit(amount);

				// THEN
				Condition<IAccountOperator> accountBalanceIsUpdated = new Condition<>((account) -> account.getBalance()
				                                                                                          .equals(amount), "checking if account balance has been updated", amount);

				Assertions.assertThat(account)
				          .is(accountBalanceIsUpdated);
		}

}
