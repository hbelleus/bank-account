package com.sfeir.kata.bank.behaviour.deposit;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/deposit.feature", publish = true, plugin = { "pretty",
		"summary" }, snippets = SnippetType.CAMELCASE)
public class ClientDepositOperationShould {

}
