package com.sfeir.kata.bank.utils;

import java.lang.reflect.InvocationTargetException;

import com.sfeir.kata.bank.service.BankTransactionService;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankTransactionServiceFactory {

	public static BankTransactionService createImplementation(Class<? extends BankTransactionService> serviceImpl)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {

		return serviceImpl.getConstructor().newInstance();
	}
}
