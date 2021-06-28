package com.sfeir.kata.bank.domain.client.account.operation.history.factory;

import org.eclipse.collections.api.list.MutableList;

import com.sfeir.kata.bank.domain.client.account.operation.OperationService;

import io.vavr.Function0;
import io.vavr.Function1;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OperationHistoryFactory {

		public static Function0<OperationHistory> initializeHistory() {
				return OperationHistory::new;
		}
		
		public static Function1<MutableList<OperationService>, OperationHistory> populateHistory() {
				return OperationHistory::new;
		}
}
