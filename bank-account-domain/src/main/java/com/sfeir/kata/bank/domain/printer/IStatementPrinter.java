package com.sfeir.kata.bank.domain.printer;

import com.sfeir.kata.bank.domain.statement.IAccountStatement;

public interface IStatementPrinter {

		void print(IAccountStatement statement);
}
