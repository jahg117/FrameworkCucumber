@cases
Feature: Cases

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option

  Scenario Outline: Create cases
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid "<productName>" product in the product enrollment form
    And I click on enroll button
    And I validate the product enrollment is displayed
    And I click on new Case
    And I select the case type "<caseType>"
    And I fill the new case mandatory fields "<channel>" "<caseStatus>" "<caseSubType>" "<discussTopic>" "<cardNumber>"
    And I validate the correct case information is displayed
    Examples:
      | productName | caseType | channel    | caseStatus | caseSubType | discussTopic | cardNumber |
      | AZ          | random   | random     | Open       | random      | random       | random     |
