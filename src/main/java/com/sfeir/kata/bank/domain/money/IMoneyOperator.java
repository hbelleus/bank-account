package com.sfeir.kata.bank.domain.money;

import java.math.BigDecimal;

import com.sfeir.kata.bank.domain.money.factory.BankMoneyFactory;

import io.vavr.Function0;
import io.vavr.Function1;

public interface IMoneyOperator {

		BigDecimal getAmount();

		default Function1<IMoneyOperator, IMoneyOperator>
		    addMoney() {
				return money -> BankMoneyFactory.create(this.getAmount()
				                                            .add(money.getAmount()));
		}

		default Function0<IMoneyOperator> toNegative() {
				return () -> BankMoneyFactory.create(this.getAmount()
				                                         .negate());
		}

		default Function1<IMoneyOperator, Boolean>
		    isLargerThan() {
				return money -> this.getAmount()
				                    .abs()
				                    .compareTo(money.getAmount()) > 0;
		}

}
