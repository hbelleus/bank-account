package com.sfeir.kata.bank.domain.bddfriendly.account;

import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.bddfriendly.account.operation.OperationSpecification;
import com.sfeir.kata.bank.domain.bddfriendly.account.statement.AccountStatementSpecification;
import com.sfeir.kata.bank.domain.common.money.Money;

import io.vavr.Function0;
import io.vavr.Function1;

public interface AccountSpecification {

		List<OperationSpecification> getOperations();

		default Consumer<OperationSpecification>
		    addOperation() {
				return operation -> this.addLatestOperationAtFirstPosition()
				                        .accept(this.getOperations(),
				                                operation);
		}

		default Function0<Money> getBalance() {
				return () -> this.getLatestOperation()
				                 .andThen(this.getAccountBalance())
				                 .apply(this.getOperations());
		}

		AccountStatementSpecification getStatement();

		default
		    BiConsumer<List<OperationSpecification>, OperationSpecification>
		    addLatestOperationAtFirstPosition() {
				return (operations,
				        operation) -> operations.add(0, operation);
		}

		default
		    Function1<List<OperationSpecification>, Optional<OperationSpecification>>
		    getLatestOperation() {
				return operations -> operations.stream()
				                               .findFirst();
		}

		default
		    Function1<Optional<OperationSpecification>, Money>
		    getAccountBalance() {
				return latestOperation -> latestOperation.map(OperationSpecification::getFinalBalance)
				                                         .orElse(Money.zero());
		}
}
