@regressionScenarios
Feature: Consents with 3 different addresses

  Scenario Outline: Create cases from patient
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option
    And I click on new Account
    And I click on new and I select "Consumer/Patient/Caregiver" account
    And I fill the mandatory fields from the account form
    And I add another address with state = MN
    And I add another address with state = MD
    And I add any state in the default address except MN and MD
    And I click on the consent tab to click the new consent button at person account page
    And I select the "<consentTypeOption>" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentStatus>" "<consentDate>" "<consentSource>" "<consentAuth>" at new consent wizard page
    And I select the first consent address in the new consent wizard page
    And I validate that the consent expiration date is correct
    And I click on the consent tab to click the new consent button at person account page
    And I select the "<consentTypeOption>" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentStatus>" "<consentDate>" "<consentSource>" "<consentAuth>" at new consent wizard page
    And I select the second consent address in the new consent wizard page
    And I validate that the consent expiration date is correct
    And I click on the consent tab to click the new consent button at person account page
    And I select the "<consentTypeOption>" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentStatus>" "<consentDate>" "<consentSource>" "<consentAuth>" at new consent wizard page
    And I select the third consent address in the new consent wizard page
    And I validate that the consent expiration date is correct

    Examples:
      | user  | consentTypeOption | consentStatus | consentDate | consentSource | consentAuth |
      | admin | ANP               | Active        | 7/19/2021   | Fax           | Self        |


