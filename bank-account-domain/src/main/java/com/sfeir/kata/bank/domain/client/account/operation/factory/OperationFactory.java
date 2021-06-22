package com.sfeir.kata.bank.domain.client.account.operation.factory;

import com.sfeir.kata.bank.domain.client.account.operation.Operation;
import com.sfeir.kata.bank.domain.client.account.operation.OperationType;
import com.sfeir.kata.bank.domain.client.account.operation.date.OperationDate;
import com.sfeir.kata.bank.domain.client.account.operation.specification.AuthorizedOperationSpecification;
import com.sfeir.kata.bank.domain.money.MoneyService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationFactory {

		@NoArgsConstructor(access = AccessLevel.PRIVATE)
		private static class OperationBalanceCalculator {

				static MoneyService
				    calculateBalance(MoneyService balance,
				                     MoneyService amount) {
						return balance.addMoney().apply(amount);
				}
		}

		public static Operation
		    createDeposit(MoneyService amount,
		                  MoneyService balance) {

				return initBuild().type(OperationType.DEPOSIT)
				                  .amount(amount)
				                  .balanceResult(OperationBalanceCalculator.calculateBalance(balance,
				                                                                             amount))
				                  .build();
		}

		public static Operation
		    createWithdrawal(MoneyService amount,
		                     MoneyService balance) {

				AuthorizedOperationSpecification.validateWithdrawal()
				                                .accept(amount,
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
