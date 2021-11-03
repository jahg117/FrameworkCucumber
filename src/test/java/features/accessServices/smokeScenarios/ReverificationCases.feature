@smokeScenarios
Feature: Reverification

  Scenario Outline: Create reverification cases
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Cases" menu option
    And I click on new Case from the cases list page
    And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
    And I validate the correct case interaction information displayed
    And I click on the child case button
    And I select the case type option "<caseOption>" from the child case form
    And I fill the child case mandatory fields without name and product enrollment "<caseRequestedType>" "<product>" "<channel>" "<caseStatus>" "<caseSubType>" "<discussTopic>" "<cardNumber>"
    Examples:
      | user  | channel | caseStatus | caseOption     | caseRequestedType    | product | channel | caseStatus | caseSubType      | discussTopic | cardNumber |
      | admin | RND     | Open       | Reverification | Health Care Provider | FASENRA | RND     | Open       | Reverification   | Initiated    | RND        |

