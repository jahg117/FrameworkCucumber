Feature: Setup configurable consents
"""
//============Script Purpose:
Creation Of consents AZ or DSI

//============Script Configuration:
There are 2 options for creation in can be by DataTable or With Scenario Outline Tables

dummyValue  Options:
  * Can be any text, used to perform a serach can be any string combination

accountType Options:
  * HCA = Facility
  * HCP = Prescribers
  * CPC = Consumer/Patients/Caregiver
  * Emp = Internal AZ

consentType Options:
  * AZ
  * DSI

accountKeyValueJSON Options:
  * accountType is the keyName use to get the value from JSON file (it does not required to be change)

consentKeyValueJSON Options:
  * consentKeyValueJSON is the value that will get from JSON file ConstantData.json (it does not required to be change)

fileNameJSON Options:
  * fileNameJSON is the name of the JSON File from where it will be extracting all the data (it does not required to be change)

ConsentType Options
  * DFM = DSI Family Consent
  * DCC = DSI Copay Consent
  * APC = AZ Promotional Consent
  * DPC = DSI Promotional Consent
  * AFP = AZ Family Consent
  * ANP = AZ Non-Promotional Consent
  * DNC = DSI Non-Promotional Consent
  * ACC = AZ Copay Consent

externalID Options:
  * it contains an externailID in case theres is already an account that we want to used

selectedView Options:
  * it will contains the name of the filter that we want to use e.g. Recently Viewed

consentStatus = it contains the status for the creation of the new Consent i.e. "Active", (can be random)

consentDate = it contains the date for the new consent i.e. "3/31/2021", (can be random)

consentSource = it contains the type of source for the new consent i.e. "Fax", (can be random)

consentAuth = it contains the type of authorization for the new consent creation i.e. "Self", (can be random)

product = it contains the drug to be use to create the PE e.g. Calquence

randomSelection = if the column contains RND it will generate all the data randomly

createPE = it creates a PE if the value in column is Y
"""

  Background:
    Given I login according to the account selected from table
      | userAccount | useThisAccount |
      | RND         | N              |
      | admin       | Y              |
      | agent       | N              |
      | manager     | N              |
      | frm         | N              |
      | externalFrm | N              |

    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option

  Scenario Outline: Create an (AZ/DSI) product enrollment with DataTable
    Given A dummyValue I enter the first name of the CPC as "<dummyValue>" with and account type "<accountType>" at CustomerLookup page for a Consent
    Given I click on new Account
    When I click on new and I select accountType
      | accountType | useThisAccount |
      | RND         | N              |
      | HCA         | N              |
      | HCP         | N              |
      | CPC         | Y              |
      | EMP         | N              |
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid consentType to get an available product in the product enrollment form
      | consentType | useThisAccount |
      | RND         | Y              |
      | AZ          | N              |
      | DSI         | N              |
    And I click on enroll button
    And I select the created program enrollment
    And I select the "Accounts" menu option
    And I validate the patient account was created

    Examples:
      | dummyValue  | accountType |
      | dummySearch | CPC         |

  @1372_regression
  Scenario Outline: Create an (AZ/DSI) product enrollment with Outline tables
    Given A dummyValue I enter the first name of the CPC as "<dummyValue>" with and account type "<accountType>" at CustomerLookup page for a Consent
    Given I click on new Account
    When I click on new and I select "<accountType>" "<accountKeyValueJSON>" "<fileNameJSON>"
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid "<consentType>" "<consentKeyValueJSON>" "<fileNameJSON>" from Examples table to get an available product in the product enrollment form
    And I click on enroll button
    And I select the created program enrollment
    And I select the "Accounts" menu option
    And I validate the patient account was created

    Examples:
      | dummyValue  | accountType | consentType | accountKeyValueJSON | consentKeyValueJSON | fileNameJSON |
      | dummySearch | CPC         | DSI         | accountType         | consentType         | ConstantData |
      | dummySearch | CPC         | AZ          | accountType         | consentType         | ConstantData |
      | dummySearch | CPC         | DSI         | accountType         | consentType         | ConstantData |


  Scenario Outline: Create an Account with PE to create a Consent at Account level
    Given "<createPE>" it selects which steps will be execute using it from CommonGeneralSteps
    Given "<randomSelection>" I verify if random selection is required using it from CommonGeneralSteps
    Given A dummyValue I enter the first name of the CPC as "<dummyValue>" with and account type "<accountType>" at CustomerLookup page for a Consent
    Given I click on new Account
    When I click on new and I select "<accountType>" "<accountKeyValueJSON>" "<fileNameJSON>"
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid "<consentType>" "<consentKeyValueJSON>" "<fileNameJSON>" from Examples table to get an available product in the product enrollment form
    And I click on enroll button
    And I select the created program enrollment
    And I select the "Accounts" menu option
    Given The "<selectedView>" i select the View to filter using it from CommonGeneralSteps
    And I validate the patient account was created
    Then I click the account created from AccountsPage
    Given A external ID "<externalID>" I search a CPC at customer lookup
    Then I click on the external ID found for CPC at customer lookup
    Then I click on the consent tab to click the new consent button at person account page
    And I select the "<consentTypeOption>" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentStatus>" "<consentDate>" "<consentSource>" "<consentAuth>" at new consent wizard page
    Then I select the consent address in the new consent wizard page
    Then I click on the product enrollment "<product>" from the person account page
    Then I validate that no warning "<consentTypeOption>" "<fileNameJSON>" message is displayed related to the type lacking of a consent at the product enrollment page
    And Close all the Tabs
    And I select the "Accounts" menu option
    Given The "Recently Viewed" i select the View to filter using it from CommonGeneralSteps
    And I search the patient at Recently View to check the Valid PAF column from Accounts Page
    Then I validate the valid PAF "<consentType>" message at valid PAF column at accounts recently viewed page

    Examples:
      | dummyValue  | externalID | selectedView    | accountType | consentType | consentTypeOption   | consentStatus | consentDate | consentSource | consentAuth | product   | accountKeyValueJSON | consentKeyValueJSON | fileNameJSON | randomSelection | createPE |
      | dummySearch | LJijh8A    | Recently Viewed | CPC         | DSI         | DSI Non-Promotional | Active        | 1/25/2021   | Fax           | Self        | Calquence | accountType         | consentType         | ConstantData | N_A             | Y        |


  Scenario: Create an attestation for AZ
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid "AZ" product in the product enrollment form
    And I click on enroll button
    And I validate the product enrollment is displayed
    And I select the Attestation tab option to click on new consent
    And I select the "AZ Provider Attestation" consent type at new consent wizard page
    And I validate the attestation details are displayed
    And I fill the mandatory fields from the consent form
    And I verify the provider details are displayed
    And I select an existing address option
    And I verify the consent details displayed

  Scenario: Create an attestation for DSI
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid "DSI" product in the product enrollment form
    And I click on enroll button
    And I validate the product enrollment is displayed
    And I click on new care team member
    And I search a care team member by name
    And I select a relationship dropdown option
    And I click on create care team member
    And I select the Attestation tab option to click on new consent
    And I select the "DSI FLSP Attestation" consent type at new consent wizard page
    And I validate the attestation details are displayed
    And I fill the DSI FLSP mandatory fields from the consent form
    And I select an existing HCP
    And I verify the DSI consent details displayed