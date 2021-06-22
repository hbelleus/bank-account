package com.sfeir.kata.bank.domain.client.account.operation.date;

import java.time.Instant;

import lombok.Value;

@Value
public class OperationDate implements OperationDateService {

		Instant time;

		public OperationDate() {
				time = Instant.now();
		}

		@Override
		public String toString() {
				return this.formatTime().apply();
		}
}
