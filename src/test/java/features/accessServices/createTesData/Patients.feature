@createData
Feature: Patients

  Scenario Outline: Create Patients
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option
    And I click on new Account
    And I click on new and I select "Consumer/Patient/Caregiver" account
    And I fill the mandatory fields from the account form
    And I save the displayed patient ID
    Then I click on Payer tab from PersonAccountPage
    Then I select the "<insuranceType>" and i fill the insurance form with "<dataPMI>" or "<dataPMB>"
    And I create a list of product enrollments
      | ProductEnrollment |
      | Fasenra           |
      | Enhertu           |
    And I click on the consent tab to click the new consent button at person account page
    And I select the "Non-Promotional" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentStatus>" "<consentDate>" "<consentSource>" "<consentAuth>" at new consent wizard page
    And I select the consent address in the new consent wizard page
    And I validate the consent ID is displayed
    And I close the last sub tab
    And I click on the consent tab to click the new consent button at person account page
    And I select the "DSI Non-Promotional" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentStatus>" "<consentDate>" "<consentSource>" "<consentAuth>" at new consent wizard page
    And I select the consent address in the new consent wizard page
    And I validate the consent ID is displayed
    And I close the last sub tab
    And I click on new Case from the person account page
    And I select the case type option "Interaction"
    And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
    Examples:
      | user  | channel | caseStatus | consentStatus | consentDate | consentSource | consentAuth | insuranceType | dataPMI                                                               | dataPMB                                                                          |
      | admin | random  | Open       | Active        | 3/31/2021   | RND           | Self        | NOPI,PMI,PBM  | RND,Self,Vandame,4/6/2021,Danaher,Danaher Plan,2403061405,RND,RND,RND | RND,Self,Pepe Le Pu,4/5/2021,Danaher,Danaher Plan,2403061405,RND,RND,RND,RND,RND |