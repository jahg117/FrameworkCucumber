Feature: Cases

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option

  Scenario: Create cases
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    And I click on new Case