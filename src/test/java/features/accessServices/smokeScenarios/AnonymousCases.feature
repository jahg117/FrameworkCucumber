Feature: Cases

  Scenario Outline: Create cases from patient
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Cases" menu option
    And I click on new Case from the cases list page
    And I select the case type option "Interaction"
    And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
    And I validate the interaction case number is displayed
    And I close the last tab
    And I click on new Case from the cases list page
    And I select the case type option "<caseType>"
    And I fill the new anonymous case fields "<productName>" "<caseRequested>" "<channel>" "<caseStatus>" "<caseSubType>" "<discussTopic>" "<cardNumber>"
    Examples:
      | user    | productName | caseType      | caseRequested              | channel    | caseStatus | caseSubType | discussTopic | cardNumber |
      | admin   | FASENRA     | Asset Request | Patient/Consumer/Caregiver | random     | Open       | random      | random       | random     |