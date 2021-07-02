package com.sfeir.kata.bank.domain.client.account.operation.date;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import io.vavr.Function1;

public interface OperationDateFormatter {

		default Function1<TemporalAccessor, String>
		    formatTime() {

				return time -> DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:SS")
				                                .format(time);
		}
}
