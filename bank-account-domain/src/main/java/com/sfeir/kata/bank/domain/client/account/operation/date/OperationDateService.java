package com.sfeir.kata.bank.domain.client.account.operation.date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import io.vavr.Function0;

public interface OperationDateService {

		Instant getTime();

		default Function0<String> formatTime() {

				return () -> DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:SS")
				                              .format(this.getTime()
				                                          .atZone(ZoneId.systemDefault()));
		}

}
