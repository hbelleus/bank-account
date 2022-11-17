package com.sfeir.kata.bank.behaviour.printing.state;

import com.sfeir.kata.bank.behaviour.withdrawal.state.AccountWithdrawalContext;
import com.sfeir.kata.bank.domain.behaviour.fixture.AccountStatementPrintingSpecification;
import com.sfeir.kata.bank.domain.behaviour.printer.AccountStatementPrinterSpecification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountStatementPrintingContext
    extends AccountWithdrawalContext {

		private AccountStatementPrintingSpecification accountStatementPrintingSpecification;

		private AccountStatementPrinterSpecification accountStatementPrinterSpecification;
}
