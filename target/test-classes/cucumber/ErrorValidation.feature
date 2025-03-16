
@tag
Feature: Placing purchase order from Ecommerce website


  @tag1
  Scenario Outline: Negative  Test for Sign In
  Given landed on Ecommerce page
  When login with username<userName> and password<password>
  Then "Incorrect email or password." message is displayed on Sign In page

    Examples: 
     |userName             |password|
     |keerthanapp@gmail.com|Pass@124|
