package com.sfeir.kata.bank.domain.operation.validation.exception;

public class UnauthorizedOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedOperationException(String message) {
		super(message);
	}
}
