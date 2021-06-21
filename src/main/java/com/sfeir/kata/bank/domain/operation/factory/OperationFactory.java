package com.sfeir.kata.bank.domain.operation.factory;

import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationType;
import com.sfeir.kata.bank.domain.operation.date.OperationDate;
import com.sfeir.kata.bank.domain.operation.validation.OperationValidator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationFactory {

		@NoArgsConstructor(access = AccessLevel.PRIVATE)
		private static class OperationBalanceCalculator {

				static Money calculateBalance(Money balance,
				                              Money amount) {
						return balance.addMoney().apply(amount);
				}
		}

		public static Operation createDeposit(Money amount,
		                                      Money balance) {

				return initBuild().type(OperationType.DEPOSIT)
				                  .amount(amount)
				                  .balanceResult(OperationBalanceCalculator.calculateBalance(balance,
				                                                                             amount))
				                  .build();
		}

		public static Operation
		    createWithdrawal(Money amount, Money balance) {

				OperationValidator.validateWithdrawal(amount,
				                                      balance);

				var negativeAmount = amount.toNegative().apply();

				return initBuild().type(OperationType.WITHDRAWAL)
				                  .amount(negativeAmount)
				                  .balanceResult(OperationBalanceCalculator.calculateBalance(balance,
				                                                                             negativeAmount))
				                  .build();
		}

		private static Operation.OperationBuilder initBuild() {
				return Operation.builder()
				                .date(new OperationDate());
		}
}
