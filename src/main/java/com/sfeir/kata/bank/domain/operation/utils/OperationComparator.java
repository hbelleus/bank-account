package com.sfeir.kata.bank.domain.operation.utils;

import java.time.LocalDateTime;

import com.sfeir.kata.bank.domain.operation.Operation;

public interface OperationComparator extends Comparable<Operation> {

	LocalDateTime getDate();

	@Override
	default int compareTo(Operation o) {
		return this.getDate().compareTo(o.getDate());
	}
}
