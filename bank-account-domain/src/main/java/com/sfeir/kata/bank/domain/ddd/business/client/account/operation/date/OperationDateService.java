package com.sfeir.kata.bank.domain.ddd.business.client.account.operation.date;

import java.time.temporal.TemporalAccessor;

public interface OperationDateService {

		TemporalAccessor getTime();
}
