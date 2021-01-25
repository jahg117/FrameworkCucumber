Feature: Setup configurable consents

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option

  Scenario: Create AZ product enrollment consent
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid "AZ" product in the product enrollment form
    And I click on enroll button
    And I select the created program enrollment
    And I select the "Accounts" menu option

  Scenario: Create DSI product enrollment consent
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid "DSI" product in the product enrollment form
    And I click on enroll button
    And I select the created program enrollment
    And I select the "Accounts" menu option

  Scenario: Create a consent for AZ
    Given A External ID i search a CPC at Customer Lookup
    Then I Click On The External ID found for CPC at Customer Lookup