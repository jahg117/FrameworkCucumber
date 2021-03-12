@childCase
Feature: Interactions

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app

  Scenario Outline: Create interaction from patient
    Given I select the "Customer Lookup" menu option
    When I click on new Account
    Then I click on new and I select "Consumer/Patient/Caregiver" account
    And I fill the mandatory fields from the account form
    And I click on new Case from the person account page
    And I select the case type option "Interaction"
    And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
    And I click on the child case button
    And I select the case type option "<caseOption>" from the child case form
    And I fill the child case mandatory fields "<product>" "<channel>" "<caseStatus>" "<caseSubType>" "<discussTopic>" "<cardNumber>"
    And I validate the correct case information is displayed
    Examples:
      |   caseOption       | product        |  channel    | caseStatus | caseSubType | discussTopic | cardNumber |
      |   Claims Support   | fasenra        |  random     | Open       | random      | random       | random     |