package com.sfeir.kata.bank.domain.client.account.operation.factory;

import com.sfeir.kata.bank.domain.money.MoneyService;

import io.vavr.Function2;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationFactory {

		public static
		    Function2<MoneyService, MoneyService, Deposit>
		    initDeposit() {
				return Deposit::new;
		}

		public static
		    Function2<MoneyService, MoneyService, Withdrawal>
		    initWithdrawal() {
				return Withdrawal::new;
		}
}
