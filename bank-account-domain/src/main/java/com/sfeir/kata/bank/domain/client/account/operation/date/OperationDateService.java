package com.sfeir.kata.bank.domain.client.account.operation.date;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import io.vavr.Function0;

public interface OperationDateService {

		TemporalAccessor getTime();

		default Function0<String> formatTime() {

				return () -> DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:SS")
				                              .format(this.getTime());
		}
}
