@productEnrollment
Feature: Product Enrollment

  Scenario Outline: Create product enrollment
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    And I enter a product enrollment in the product enrollment form
      | ProductEnrollment |
      | Fasenra           |
      | Calquence         |
      | Enhertu           |
    Examples:
      | user  |
      | admin |