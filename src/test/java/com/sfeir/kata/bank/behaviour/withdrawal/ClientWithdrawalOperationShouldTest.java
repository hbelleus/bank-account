package com.sfeir.kata.bank.behaviour.withdrawal;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/withdrawal.feature", plugin = { "pretty",
		"summary" }, snippets = SnippetType.CAMELCASE)
public class ClientWithdrawalOperationShouldTest {

}
