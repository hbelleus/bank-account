package com.sfeir.kata.bank.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.account.factory.BankAccountFactory;
import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

@RunWith(JUnitPlatform.class)
@TestMethodOrder(OrderAnnotation.class)
class OperationStatementTest {

		private IAccountOperator account;

		@BeforeEach
		public void init() {

				account = BankAccountFactory.create();
		}

		@Test
		@Order(1)
		void givenEmptyHistory_whenGetStatement_thenStatementIsEmpty() {

				// GIVEN

				// WHEN
				var statement = account.getStatement();

				// THEN
				Assertions.assertThat(statement.getLines())
				          .isEmpty();

		}

		@Test
		@Order(2)
		void givenNotEmptyHistory_whenGetStatement_thenStatementHasOneLine() {

				// GIVEN
				var amount = BankMoneyFactory.create(200);

				Assumptions.assumeThat(account.deposit(amount))
				           .isTrue();

				// WHEN
				var statement = account.getStatement();

				// THEN
				Assertions.assertThat(statement.getLines())
				          .isNotEmpty()
				          .hasSize(1)
				          .anyMatch(line -> amount.toString().equals(line.getAmount()))
				          .anyMatch(line -> amount.toString().equals(line.getBalance()));
		}

		@Test
		@Order(3)
		void givenNotEmptyHistory_whenGetStatement_thenStatementHasTwoLine() {

				// GIVEN
				var amount = BankMoneyFactory.create(200);

				Assumptions.assumeThat(account.deposit(amount))
				           .isTrue();
				Assumptions.assumeThat(account.deposit(amount))
				           .isTrue();

				var expectedValue = amount.addMoney()
				                          .apply(amount)
				                          .toString();

				// WHEN
				var statement = account.getStatement();

				// THEN
				Assertions.assertThat(statement.getLines())
				          .isNotEmpty()
				          .hasSize(2)
				          .allMatch(line -> amount.toString()
				                                  .equals(line.getAmount()));

				Assertions.assertThat(statement.getLines())
				          .first()
				          .hasFieldOrPropertyWithValue("balance",
				                                       expectedValue);
		}
}
