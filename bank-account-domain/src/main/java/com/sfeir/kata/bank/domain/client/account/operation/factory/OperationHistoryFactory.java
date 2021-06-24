package com.sfeir.kata.bank.domain.client.account.operation.factory;

import org.eclipse.collections.api.list.MutableList;

import io.vavr.Function0;
import io.vavr.Function1;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationHistoryFactory {

		public static Function0<OperationHistory> initializeHistory() {
				return OperationHistory::new;
		}
		
		public static Function1<MutableList<Operation>, OperationHistory> populateHistory() {
				return OperationHistory::new;
		}
}
