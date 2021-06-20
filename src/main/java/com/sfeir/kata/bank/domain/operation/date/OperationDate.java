package com.sfeir.kata.bank.domain.operation.date;

import java.time.Instant;

import lombok.Value;

@Value
public class OperationDate implements IOperationDateFormat {

		Instant time;

		public OperationDate() {
				time = Instant.now();
		}

		@Override
		public String toString() {
				return this.format(this.time);
		}
}
