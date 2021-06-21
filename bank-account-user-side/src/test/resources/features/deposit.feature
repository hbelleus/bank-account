Feature: Make a deposit

  Scenario: Deposit 0 euros and verify balance
    When I deposit 0 euros
    Then My balance should be 0
    
  Scenario: Deposit 500 euros and verify balance
    When I deposit 0 euros
    Then My balance should be 500