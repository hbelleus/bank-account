package com.sfeir.kata.bank.domain.client.account.factory;

import org.eclipse.collections.impl.list.mutable.MutableListFactoryImpl;

import com.sfeir.kata.bank.domain.client.account.AccountService;
import com.sfeir.kata.bank.domain.client.account.operation.OperationHistory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankAccountFactory {

		public static AccountService create() {

				var operationHistory = new OperationHistory(MutableListFactoryImpl.INSTANCE.of());

				return new Account(operationHistory);
		}
}
