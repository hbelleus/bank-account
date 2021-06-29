package com.sfeir.kata.bank.domain;

import java.util.regex.Pattern;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.factory.AccountFactory;
import com.sfeir.kata.bank.domain.client.account.operation.OperationType;
import com.sfeir.kata.bank.domain.money.factory.MoneyFactory;

@RunWith(JUnitPlatform.class)
@TestMethodOrder(OrderAnnotation.class)
class OperationStatementTest {

		private AccountService account;

		@BeforeEach
		public void init() {

				account = AccountFactory.createAccount().apply();
		}

		@Test
		@Order(1)
		void givenEmptyHistory_whenGetStatement_thenStatementIsEmpty() {

				// GIVEN

				// WHEN
				var statement = account.generateStatement().apply();

				// THEN
				Assertions.assertThat(statement.getLines())
				          .isEmpty();

		}

		@Test
		@Order(2)
		void givenNotEmptyHistory_whenGetStatement_thenStatementHasOneLine() {

				// GIVEN
				var amount = MoneyFactory.create(200);

				account.deposit().accept(amount);

				// WHEN
				var statement = account.generateStatement().apply();

				// THEN
				Assertions.assertThat(statement.getLines())
				          .isNotEmpty()
				          .hasSize(1)
				          .anyMatch(line -> amount.toString()
				                                  .equals(line.getAmount()))
				          .anyMatch(line -> amount.toString()
				                                  .equals(line.getBalance()));

		}

		@Test
		@Order(2)
		void givenNotEmptyHistory_whenGetStatement_thenStatementHasOneLineWithGoodFormat() {

				// GIVEN
				var amount = MoneyFactory.create(200);

				account.deposit().accept(amount);

				// WHEN
				var statement = account.generateStatement().apply();

				// THEN
				Pattern datePattern = Pattern.compile("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}");

				Assertions.assertThat(statement.getLines())
				          .isNotEmpty()
				          .hasSize(1)
				          .anyMatch(line -> amount.toString()
				                                  .equals(line.getAmount()))
				          .anyMatch(line -> amount.toString()
				                                  .equals(line.getBalance()))
				          .anyMatch(line -> OperationType.DEPOSIT.name()
				                                                 .equals(line.getType()))
				          .anyMatch(line -> true == datePattern.asPredicate()
				                                               .test(line.getDate()));

		}

		@Test
		@Order(3)
		void givenNotEmptyHistory_whenGetStatement_thenStatementHasTwoLine() {

				// GIVEN
				var amount = MoneyFactory.create(200);

				account.deposit().accept(amount);
				account.deposit().accept(amount);

				var expectedValue = amount.putMoney()
				                          .apply(amount)
				                          .toString();

				// WHEN
				var statement = account.generateStatement().apply();

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
