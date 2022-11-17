package com.sfeir.kata.bank.domain.ddd.reporting.client;

import com.sfeir.kata.bank.domain.ddd.reporting.client.account.AccountReportingService;
import com.sfeir.kata.bank.domain.ddd.reporting.client.account.printer.StatementPrinterService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ClientReporting
    implements ClientReportingService {

		private final AccountReportingService account;

		private final StatementPrinterService printer;
}
