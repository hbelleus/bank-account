package com.sfeir.kata.bank.domain.client.business.account;

import java.util.regex.Pattern;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.Account;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.OperationType;

@TestMethodOrder(OrderAnnotation.class)
class AccountStatementTest {

		private Account account;

		@BeforeEach
		public void init() {

				account = new Account();
		}

		@Test
		@Order(1)
		void givenEmptyHistory_whenGetStatement_thenStatement() {

				// GIVEN

				// WHEN
				var statement = account.getStatement().apply();

				// THEN
				Assertions.assertThat(statement.getLines())
				          .hasSize(0);

		}

		@Test
		@Order(2)
		void givenNotEmptyHistory_whenGetStatement_thenStatementHasOneLine()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("200");

				account.deposit().accept(amount);

				// WHEN
				var statement = account.getStatement().apply();

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
		void givenNotEmptyHistory_whenGetStatement_thenStatementHasOneLineWithGoodFormat()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("200");

				account.deposit().accept(amount);

				// WHEN
				var statement = account.getStatement().apply();

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
		void givenNotEmptyHistory_whenGetStatement_thenStatementHasTwoLine()
		    throws IllegalAccessException {

				// GIVEN
				var amount = Money.of("200");

				account.deposit().accept(amount);
				account.deposit().accept(amount);

				var expectedValue = amount.putMoney()
				                          .apply(amount)
				                          .toString();

				// WHEN
				var statement = account.getStatement().apply();

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
