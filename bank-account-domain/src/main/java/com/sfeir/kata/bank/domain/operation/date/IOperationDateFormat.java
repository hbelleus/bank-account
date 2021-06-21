package com.sfeir.kata.bank.domain.operation.date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public interface IOperationDateFormat {

		default String format(Instant date) {

				var formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:SS");

				return formatter.format(date.atZone(ZoneId.systemDefault()));
		}
}
