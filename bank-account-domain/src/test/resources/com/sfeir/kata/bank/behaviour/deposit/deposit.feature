@deposit
Feature: Make a deposit

  Scenario: Deposit 0 euros and verify balance
    When I deposit 0 euros
    Then My balance after deposit should be 0
    
  Scenario: Deposit 500 euros and verify balance
    When I deposit 500 euros
    Then My balance after deposit should be 500