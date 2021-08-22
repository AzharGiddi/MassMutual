Feature: Validate Problem
  
    Background: 
      Given Browser is launched with "https://www.google.com"
      When Test page with title "Google" has loaded successfully
  
    @smoketest
    Scenario: Validate right count of Values appear on screen
      Then Verify 5 values are displayed.
      
    @smoketest2
    Scenario: Validate currency values on the screen are greater than zero
      Then Verify currency values are greater than 0
  
    @smoketest3
    Scenario: Validate total Balance is Sum of all values displayed on the screen
      Then Verify sum of all the values is refelecting correctly in Total Balance
  
    @smoketest4
    Scenario: Verify values displayed on screen are formatted as currency
      Then Verify values are formatted as currency.
  
    @Regression
    Scenario: Complete validation
       Then Verify 5 values are displayed.
      Then Verify currency values are greater than 0
      Then Verify sum of all the values is refelecting correctly in Total Balance
      Then Verify values are formatted as currency.
