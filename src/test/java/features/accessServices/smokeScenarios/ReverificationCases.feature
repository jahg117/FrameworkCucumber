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
      | user    | channel | caseStatus | caseOption     | caseRequestedType    | product | channel | caseStatus | caseSubType      | discussTopic | cardNumber |
      | admin   | RND     | Open       | Reverification | Health Care Provider | FASENRA | RND     | Open       | Reverification   | Initiated    | RND        |
      | agent   | RND     | Open       | Reverification | Health Care Provider | FASENRA | RND     | Open       | Reverification   | Initiated    | RND        |
      | manager | RND     | Open       | Reverification | Health Care Provider | FASENRA | RND     | Open       | Reverification   | Initiated    | RND        |

  Scenario Outline: Add and validate case contacts
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Cases" menu option
    Then I open the RV case
    And I add a new case team member "<ctm>" and validate it
    Then I click on Reverification tab from RV Case
    Then I create a search to select patients and associate them to RV case using the following "<sd>"
    Given The "<amntPatients>" i select from the table the patients to be associate to case
    Then I validate that the patients selected are displayed on the patient section
    Then I click on create RV case without selecting any of the patients to validate warning message No patients selected
    Given a Next treatment date "<ntd>" i will update the date in the patient section
    Then I select the checkbox for the patients click Create RV case and validate message RV created
    And I validate the BI data with the RV cases are with the status "<status>" "<ntd>"

    Examples:
      | user  | ctm                          | sd              | amntPatients | ntd                    | status  |
      | admin | P0:hca,P1:internal.frm,P2:PF | N_A,N_A,N_A,RND | 2            | 06/11/2021, 1:30 PM_NE | On Hold |