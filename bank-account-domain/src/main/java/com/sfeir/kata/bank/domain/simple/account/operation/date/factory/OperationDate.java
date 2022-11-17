package com.sfeir.kata.bank.domain.simple.account.operation.date.factory;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import com.sfeir.kata.bank.domain.simple.account.operation.date.OperationDateFormatter;

import lombok.Value;

@Value
public class OperationDate
    implements OperationDateFormatter {

		TemporalAccessor time;

		@Override
		public String toString() {
				return DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:SS")
				                        .format(this.time);
		}

		public static OperationDate now() {

				return new OperationDate(Instant.now()
				                                .atZone(ZoneId.systemDefault()));
		}

}