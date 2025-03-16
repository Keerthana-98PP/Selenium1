
@tag
Feature: Placing purchase order from Ecommerce website

Background:
Given landed on Ecommerce page


  @Regression
  Scenario Outline: Postive Test for placing order
    Given login with username<userName> and password<password>
    When Add product<productName> to cart
    And checkout<productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on conformation page

    Examples: 
     |userName             |password|productName|
     |keerthanapp@gmail.com|Pass@123|ZARA COAT 3|
