package com.sfeir.kata.bank.domain.ddd.business.client.account.operation.date.factory;

import java.time.temporal.TemporalAccessor;

import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.date.OperationDateFormatter;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.date.OperationDateService;

import lombok.Value;

@Value
class OperationDate implements OperationDateService,
    OperationDateFormatter {

		TemporalAccessor time;

		@Override
		public String toString() {
				return this.formatTime().apply(this.time);
		}
}
