package com.sfeir.kata.bank.domain.operation.factory;

import com.sfeir.kata.bank.domain.account.Account;
import com.sfeir.kata.bank.domain.money.Money;
import com.sfeir.kata.bank.domain.operation.Operation;
import com.sfeir.kata.bank.domain.operation.OperationType;
import com.sfeir.kata.bank.domain.operation.date.OperationDate;
import com.sfeir.kata.bank.domain.operation.validation.OperationValidator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationFactory {

		public static Operation createDeposit(Money amount,
		                                      Account account) {

				return initBuild().type(OperationType.DEPOSIT)
				                  .amount(amount)
				                  .balanceResult(account.getBalance()
				                                        .add()
				                                        .apply(amount))
				                  .build();
		}

		public static Operation
		    createWithdrawal(Money amount, Account account) {

				OperationValidator.validateWithdrawal(amount,
				                                      account.getBalance());

				var negativeAmount = amount.toNegative().apply();

				return initBuild().type(OperationType.WITHDRAWAL)
				                  .amount(negativeAmount)
				                  .balanceResult(account.getBalance()
				                                        .add()
				                                        .apply(negativeAmount))
				                  .build();
		}

		private static Operation.OperationBuilder initBuild() {
				return Operation.builder()
				                .date(new OperationDate());
		}
}
