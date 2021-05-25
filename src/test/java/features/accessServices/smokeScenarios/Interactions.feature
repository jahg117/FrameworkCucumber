Feature: Interactions

  Scenario Outline: Create interaction from patient
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option
    And I click on new Account
    And I click on new and I select "Consumer/Patient/Caregiver" account
    And I fill the mandatory fields from the account form
    And I click on new Case from the person account page
    And I select the case type option "Interaction"
    And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
    And I validate the correct case interaction information displayed
    Examples:
      | user  | channel | caseStatus |
      | admin | RND     | Open       |

  Scenario Outline: Create interaction from cases option
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    Given I select the "Cases" menu option
    When I click on new Case from the cases list page
    Then I select the case type option "Interaction"
    And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
    And I validate the correct case interaction information displayed
    Examples:
      | user  | channel | caseStatus |
      | admin | RND     | Open       |