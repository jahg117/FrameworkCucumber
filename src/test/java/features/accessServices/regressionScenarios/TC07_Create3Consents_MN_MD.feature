@consent123
Feature: Consents with 3 different addresses

  Scenario Outline: Create cases from patient
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    Then I add another address with state = MN
    And I close the last sub tab
    Then I add another address with state = MD
    And I close the last sub tab
    Then I click on the consent tab to click the new consent button at person account page
    And I select the "<consentTypeOption>" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentStatus>" "<consentDate>" "<consentSource>" "<consentAuth>" at new consent wizard page
    Then I select the consent address in the new consent wizard page

    Examples:
      | user     | consentTypeOption | consentStatus | consentDate | consentSource | consentAuth |
      | adminINT | ANP               | Active        | 6/21/2021   | Fax           | Self        |


