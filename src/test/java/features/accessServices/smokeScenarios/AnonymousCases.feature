@smoke
Feature: Anonymous Cases

  Scenario Outline: Create anonymous cases from patient
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Cases" menu option
    And I click on new Case from the cases list page
    And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
    And I validate the interaction case number is displayed
    Examples:
      | user  | channel | caseStatus |
      | admin | RND     | Open       |

    Scenario Outline: Create anonymous cases from patients
      Given I login as an "<user>" user
      When the salesforce page is displayed
      Then I search the "Access Services" app
      And I select the "Customer Lookup" menu option
      And I click on new Account
      And I click on new and I select "<caseRequested>" account
      And I fill the mandatory fields from the account form
      And I click on new Case from the person account page
      And I select the case type option "Interaction"
      And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
      And I validate the interaction case number is displayed
      Examples:
        | user  | caseRequested              | channel | caseStatus |
        | admin | Patient/Consumer/Caregiver | RND     | Open       |