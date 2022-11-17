package com.sfeir.kata.bank.behaviour.printing.steps;

import java.math.BigDecimal;

import org.mockito.Mockito;

import com.sfeir.kata.bank.behaviour.printing.state.AccountStatementPrintingContext;
import com.sfeir.kata.bank.domain.bddfriendly.account.Account;
import com.sfeir.kata.bank.domain.bddfriendly.printer.AccountStatementPrinterSpecification;
import com.sfeir.kata.bank.domain.bddfriendly.service.AccountStatementPrintingService;
import com.sfeir.kata.bank.domain.bddfriendly.service.DepositService;
import com.sfeir.kata.bank.domain.bddfriendly.service.WithdrawalService;
import com.sfeir.kata.bank.domain.common.money.Money;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SetupSteps {

		@NonNull
		private final AccountStatementPrintingContext accountStatementPrintingContext;

		@Before("@printing")
		public void setupForPrinting() {

				accountStatementPrintingContext.setDepositFixtureSpecification(new DepositService());
				accountStatementPrintingContext.setWithdrawalFixtureSpecification(new WithdrawalService());
				accountStatementPrintingContext.setAccountStatementPrintingSpecification(new AccountStatementPrintingService());
				accountStatementPrintingContext.setAccountStatementPrinterSpecification(Mockito.mock(AccountStatementPrinterSpecification.class));
				accountStatementPrintingContext.setAccountSpecification(new Account());
		}

		@Given("^I firstly deposit (\\d+) euros$")
		public void given_a_deposit(BigDecimal amount) {

				accountStatementPrintingContext.getDepositFixtureSpecification()
				                               .deposit(Money.of(amount),
				                                        accountStatementPrintingContext.getAccountSpecification());

		}

		@Given("^I secondly withdraw (\\d+) euros$")
		public void given_a_withdraw(BigDecimal amount) {

				accountStatementPrintingContext.getWithdrawalFixtureSpecification()
				                               .withdraw(Money.of(amount),
				                                         accountStatementPrintingContext.getAccountSpecification());

		}
}
