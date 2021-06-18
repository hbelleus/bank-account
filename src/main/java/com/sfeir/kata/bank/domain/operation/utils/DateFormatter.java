package com.sfeir.kata.bank.domain.operation.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateFormatter {

	public static String format(LocalDateTime date) {

		var formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu HH:mm:SS");

		return formatter.format(date);
	}
}
