package com.sfeir.kata.bank.domain.behaviour.account.operation.date.factory;

import java.time.temporal.TemporalAccessor;

import com.sfeir.kata.bank.domain.behaviour.account.operation.date.OperationDateFormatter;
import com.sfeir.kata.bank.domain.behaviour.account.operation.date.OperationDateSpecification;

import lombok.Value;

@Value
class OperationDate implements OperationDateSpecification,
    OperationDateFormatter {

		TemporalAccessor time;

		@Override
		public String toString() {
				return this.formatTime().apply(this.time);
		}
}
