@smoke
Feature: Cases

  Scenario Outline: Create cases
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
    And I validate the interaction case number is displayed
    And I click on new product enrollment button
    And I enter a valid "<productName>" product in the product enrollment form
    And I click on enroll button
    And I validate the product enrollment is displayed
    And I click on new Case from the product enrollment page
    And I select the case type "<caseType>"
    And I fill the new case mandatory fields "<channel>" "<caseStatus>" "<caseSubType>" "<discussTopic>" "<cardNumber>"
    And I validate the correct case information is displayed
    Examples:
      | user    | productName | caseType | channel    | caseStatus | caseSubType | discussTopic | cardNumber |
      | admin   | AZ          | random   | random     | Open       | random      | random       | random     |
      | manager | AZ          | random   | random     | Open       | random      | random       | random     |
      | agent   | AZ          | random   | random     | Open       | random      | random       | random     |

  Scenario Outline: Create case Random
        Given I login as an "admin" user
        When the salesforce page is displayed
        Then I search the "Access Services" app
        And I select the "Customer Lookup" menu option
        And I click on new Account
        And I click on new and I select "Consumer/Patient/Caregiver" account
        And I fill the mandatory fields from the account form
        And I click on new Case from the person account page
        And I select the case type option "Interaction"
        And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
        And I validate the interaction case number is displayed
        And I click on new product enrollment button
        And I enter a valid "<productName>" product in the product enrollment form
        And I click on enroll button
        And I validate the product enrollment is displayed
        And I click on new Case from the person account tab
        And I select the case type option "<caseType>"
        And I fill the new case mandatory fields "<channel>" "<caseStatus>" "<caseSubType>" "<discussTopic>" "<cardNumber>"
        And I validate the correct case information is displayed
      Examples:
        | productName | caseType | channel    | caseStatus | caseSubType | discussTopic | cardNumber |
        | AZ          | Claims Support   | random     | Open       | random      | random       | random     |


