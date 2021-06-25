# Bank account kata
Think of your personal bank account experience When in doubt, go for the simplest solution

# Requirements
- Deposit and Withdrawal
- Account statement (date, amount, balance)
- Statement printing
 
# User Stories
##### US 1:
**In order to** save money  
**As a** bank client  
**I want to** make a deposit in my account  
 
##### US 2: 
**In order to** retrieve some or all of my savings  
**As a** bank client  
**I want to** make a withdrawal from my account  
 
##### US 3: 
**In order to** check my operations  
**As a** bank client  
**I want to** see the history (operation, date, amount, balance)  of my operations  # Bank account kata
Think of your personal bank account experience When in doubt, go for the simplest solution


# Additional condition:

- It is not allowed to withdraw an amount larger than the current account balance.


# Functional analysis:

-> Ubiquitous language definition
-> DDD bounded context

A client has an account where he can make a deposit or a withdrawal.
A deposit consists in putting money in a bank account.
A withdraw consists in retrieving money from a bank account.
Each operation is saved in the history of operations from some account.
A statement is a view of operation history details. 
Each line of a statement corresponds to an operation (amount, balance, date, type of operation).
A client can see the statement through a printing service.

Objects:

- money 
- client > account > operationHistory > operations ( deposit / withdrawal )
- statement > statementLine 
- printer
					


# Solution:

- DDD with use of tactical design ( factory, service, object value, aggregate roots )
- Organizing code by component
- Hexagonal architecture
- Functional programming with vavr.io
- Collection handling with eclipse.collection
- Diamond TDD with Junit5 + assertj / BDD with cucumber + gherkin

