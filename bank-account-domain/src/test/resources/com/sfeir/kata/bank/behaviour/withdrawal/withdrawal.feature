@withdrawal
Feature: Make a withdrawal

  Scenario: Withdraw after initial deposit and verify balance
    Given I have 100 euros in my account
    When I withdraw 50 euros
    Then My balance after withdrawal should be 50

  Scenario: Unauthorized withdraw
    Given I want to retrieve 50 euros from my account
    Then withdrawal should be unauthorized