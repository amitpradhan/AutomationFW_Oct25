@login_page
Feature: Login page
  This file contains all the testcases related to login page


  @loginAsUser
  Scenario: Login to AutomationExercise Application
    Given I am logged into application with user "Amit"
    Then I should be logged in successfully.

