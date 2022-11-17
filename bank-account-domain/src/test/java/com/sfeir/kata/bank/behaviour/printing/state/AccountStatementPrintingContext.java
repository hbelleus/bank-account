package com.sfeir.kata.bank.behaviour.printing.state;

import com.sfeir.kata.bank.behaviour.withdrawal.state.AccountWithdrawalContext;
import com.sfeir.kata.bank.domain.bddfriendly.printer.AccountStatementPrinterSpecification;
import com.sfeir.kata.bank.domain.bddfriendly.service.AccountStatementPrintingSpecification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountStatementPrintingContext
    extends AccountWithdrawalContext {

		private AccountStatementPrintingSpecification accountStatementPrintingSpecification;

		private AccountStatementPrinterSpecification accountStatementPrinterSpecification;
}
