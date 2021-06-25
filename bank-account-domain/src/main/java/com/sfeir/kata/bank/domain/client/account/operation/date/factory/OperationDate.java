package com.sfeir.kata.bank.domain.client.account.operation.date.factory;

import java.time.temporal.TemporalAccessor;

import com.sfeir.kata.bank.domain.client.account.operation.date.OperationDateService;

import lombok.Value;

@Value
class OperationDate implements OperationDateService {

		TemporalAccessor time;

		@Override
		public String toString() {
				return this.formatTime().apply();
		}
}
