Feature: Create 3 consents for different addresses

  Background:
    Given I login according to the account selected from table
      | userAccount | useThisAccount |
      | RND         | N              |
      | admin       | N              |
      | agent       | Y              |
      | manager     | N              |
      | frm         | N              |
      | externalFrm | N              |

    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option

  @consent123
  Scenario Outline: Create a new CPC Search from CustomerLookup page
    Given The account type "<accountType>" i select the filter to perform a search at CustomerLookup page
    Given A value or set of value to create a CPC Search I enter "<externalID>" "<firstName>" "<middleName>" "<lastName>" "<dateOfBirth>" "<careGiver>" "<email>" "<phoneOrFax>" "<addressLine1>" "<state>" "<city>" "<zipCode>" "<country>" "<searchAllFromFile>" at CustomerLookup page
    Then I validate results from search
#    Then I click on the external ID found for CPC at customer lookup

    Examples:
      | accountType | externalID | firstName | middleName | lastName | dateOfBirth | careGiver | email | phoneOrFax | addressLine1      | state | city | zipCode | country | searchAllFromFile |
      | CPC         | 1dkbuNfo   | N_A       | N_A        | N_A      | N_A         | N_A       | N_A   | N_A        | N_A               | N_A   | N_A  | N_A     | N_A     | N                 |


  Scenario Outline: Create AZ Non-Promotional consent selecting address with MN state
#    Given I search an external ID
    Then I click on the consent tab to click the new consent button at person account page
    And I select the "<consentTypeOption>" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentStatus>" "<consentDate>" "<consentSource>" "<consentAuth>" at new consent wizard page
    Then I select the consent address in the new consent wizard page

    Examples:
      | consentTypeOption          | consentStatus | consentDate | consentSource | consentAuth |
      | AZ Non-Promotional Consent | Active        | 5/05/2021   | Fax           | Self        |