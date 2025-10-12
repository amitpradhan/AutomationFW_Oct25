@login_page
Feature: Home page
  This file contains all the testcases related to home page


  @loginAsUser
  Scenario: Add to cart - first item
    Given I am logged into application with user "Amit"
    Then I add first item to the cart
    And the item got added successfully


