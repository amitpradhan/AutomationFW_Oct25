@cart_page
Feature: Cart page
  This file contains all the testcases related to Cart page


  @validateTotalAmount
  Scenario: Verify Total amount should match quantity multiply by price
    Given I am logged into application with user "Amit"
    Then I add first item to the cart
    Then check Price and Quantity in Cart page
    Then validate the total amount
