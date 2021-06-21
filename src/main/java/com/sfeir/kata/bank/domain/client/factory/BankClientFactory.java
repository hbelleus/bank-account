package com.sfeir.kata.bank.domain.client.factory;

import com.sfeir.kata.bank.domain.account.factory.BankAccountFactory;
import com.sfeir.kata.bank.domain.client.IClientOperator;
import com.sfeir.kata.bank.domain.printer.IStatementPrinter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankClientFactory {

		public static IClientOperator create() {

				var account = BankAccountFactory.create();

				return ClientOperationContext.of(account, null);
		}

		public static IClientOperator
		    create(IStatementPrinter printer) {

				var account = BankAccountFactory.create();

				return ClientOperationContext.of(account, printer);
		}
}
