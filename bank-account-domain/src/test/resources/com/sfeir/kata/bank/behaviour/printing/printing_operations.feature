@printing
Feature: Printing operations

  Scenario: Print message when no saved operation
    When I print the operations
    Then it should print the statement
    
  Scenario: Deposit and withdraw then print the operations
  	Given I firstly deposit 500 euros
  	And I secondly withdraw 150 euros
    When I print the operations
    Then the printer should be called 1 time