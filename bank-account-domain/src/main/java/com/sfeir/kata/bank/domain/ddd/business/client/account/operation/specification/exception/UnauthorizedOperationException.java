package com.sfeir.kata.bank.domain.ddd.business.client.account.operation.specification.exception;

public class UnauthorizedOperationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedOperationException(String message) {
		super(message);
	}
}
