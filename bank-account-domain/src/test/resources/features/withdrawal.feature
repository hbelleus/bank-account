Feature: Make a withdrawal

  Scenario: Withdraw after initial deposit and verify balance
    When I deposit 100 euros
    And I withdraw 50 euros
    Then My balance should be 50

  Scenario: Unauthorized withdraw
    Given I want to retrieve 50 euros from my account
    Then withdrawal should be unauthorized