@patients
Feature: Add information existing patients

  Scenario: Existing patients
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Accounts" menu option
    And I search the patient "PEP_ID-2010677" from the search bar
    And I validate the patient ID is displayed