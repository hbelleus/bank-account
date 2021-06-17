package com.sfeir.kata.bank.behaviour.deposit;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/deposit.feature", publish = true, plugin = { "pretty",
		"html:target/cucumber" })
public class ClientDepositOperationShould {

}
