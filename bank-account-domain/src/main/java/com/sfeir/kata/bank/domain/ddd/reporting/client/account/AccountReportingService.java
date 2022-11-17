package com.sfeir.kata.bank.domain.ddd.reporting.client.account;

import java.util.function.Consumer;

import com.sfeir.kata.bank.domain.common.money.Money;
import com.sfeir.kata.bank.domain.ddd.business.client.account.AccountService;
import com.sfeir.kata.bank.domain.ddd.business.client.account.operation.history.OperationHistoryService;
import com.sfeir.kata.bank.domain.ddd.reporting.client.account.printer.StatementPrinterService;
import com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement.AccountStatementService;
import com.sfeir.kata.bank.domain.ddd.reporting.client.account.statement.factory.AccountStatementFactory;

import io.vavr.Function0;

public interface AccountReportingService
    extends AccountService {

		OperationHistoryService getHistory();

		Function0<Money> getBalance();

		default Function0<AccountStatementService>
		    getStatement() {
				return () -> AccountStatementFactory.createStatement()
				                                    .apply(this.getHistory());
		}

		default Consumer<StatementPrinterService>
		    printStatement() {
				return printer -> printer.print(this.getStatement()
				                                    .apply());
		}

}
