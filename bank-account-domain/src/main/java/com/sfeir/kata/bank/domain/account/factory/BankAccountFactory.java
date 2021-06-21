package com.sfeir.kata.bank.domain.account.factory;

import org.eclipse.collections.impl.list.mutable.MutableListFactoryImpl;

import com.sfeir.kata.bank.domain.account.IAccountOperator;
import com.sfeir.kata.bank.domain.operation.OperationHistory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankAccountFactory {

		public static IAccountOperator create() {

				var operationHistory = new OperationHistory(MutableListFactoryImpl.INSTANCE.of());

				return new Account(operationHistory);
		}
}