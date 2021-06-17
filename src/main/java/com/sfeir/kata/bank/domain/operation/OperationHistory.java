package com.sfeir.kata.bank.domain.operation;

import java.util.List;

import lombok.Value;

@Value
public class OperationHistory {

	List<Operation> operations;
	
	public Boolean isEmpty() {
		return this.operations.isEmpty();
	}

}
