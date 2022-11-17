package com.sfeir.kata.bank.domain.behaviour.account.operation.date;

import java.time.temporal.TemporalAccessor;

public interface OperationDateSpecification {

		TemporalAccessor getTime();
}
