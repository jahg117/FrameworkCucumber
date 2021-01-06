Feature: Setup configurable consents

  Background: 
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app

  Scenario: Create AZ product enrollment consent
    Given I click on new Account
    When I click on new and I select "Patient/Consumer/Caregiver" account
    Then I fill the mandatory fields from the account form