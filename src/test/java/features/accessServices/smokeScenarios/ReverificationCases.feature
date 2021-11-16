@reverification
Feature: Reverification

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
      | user  | channel | caseStatus | caseOption     | product | channel | caseStatus | caseSubType | discussTopic | cardNumber | ctm                          | sd              | amntPatients | ntd                    | status  |
      | admin | RND     | Open       | Reverification | FASENRA | RND     | Open       | RND         | RND          | RND        | P0:hca,P1:internal.frm,P2:PF | N_A,N_A,N_A,RND | 2            | 06/11/2021, 1:30 PM_NE | On Hold |