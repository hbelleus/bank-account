package com.sfeir.kata.bank.behaviour.withdrawal;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/withdrawal.feature", publish = true, plugin = { "pretty",
		"html:target/cucumber" })
public class ClientWithdrawalOperationShould {

}
