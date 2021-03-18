@accountsOperation
Feature: Setup configurable consents

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option



  Scenario Outline: Create a new Facility Account from CustomerLookup page
    Given A dummyValue I enter the name of the facility as "<dummyValue>" with and account type "<accountType>" at CustomerLookup page
    Then I click on new Account at CustomerLookUp Page
    Then I fill the form with the values "<identifier>" "<npi>" "<nameHCA>" "<email>" "<phoneOrFax>" "<addressLine1>" "<state>" "<city>" "<zipCode>" "<country>" "<randomRecord>" at HCA Wizard Page

    Examples:
      | dummyValue | accountType | identifier | npi | nameHCA        | email | phoneOrFax | addressLine1 | state | city         | zipCode | country       | randomRecord |
      | dummy      | HCA         | AUT_       | RND | DonJuanCamaney | N_A   | 4491234567 | RND          | AA    | Guadalakjara | RND     | United States | RND          |


  Scenario Outline: Create a new HCP Account from CustomerLookup page
    Given A dummyValue I enter the first name of the HCP as "<dummyValue>" with and account type "<accountType>" at CustomerLookup page
    Then I click on new Account at CustomerLookUp Page
    Given An account record type for HCP "<accountRecordType>" i click continue button at New Account page
    Then I fill the form with the values "<identifier>" "<npi>" "<firstName>" "<middleName>" "<lastName>" "<dateOfBird>" "<email>" "<phoneOrFax>" "<addressLine1>" "<state>" "<city>" "<zipCode>" "<country>" "<randomRecord>" at HCP Wizard Page

    Examples:
      | dummyValue | accountType | accountRecordType    | identifier | npi | firstName | middleName | lastName | dateOfBird  | email | phoneOrFax | addressLine1     | state | city | zipCode | country       | randomRecord |
      | pimpinela  | HCP         | Health Care Provider | AUT_HCP_   | RND | Don       | Juan       | Camaney  | Feb 9, 2021 | AA    | 6666666666 | Emiratos Latinos | AA    | RND  | 90210   | United States | RND          |



  Scenario Outline: Create a new CPC Account from CustomerLookup page
    Given A dummyValue I enter the first name of the CPC as "<dummyValue>" with and account type "<accountType>" at CustomerLookup page
    Then I click on new Account at CustomerLookUp Page
    Given An account record type for CPC "<accountRecordType>" i click continue button at New Account page
    Then I fill the form with the values "<identifier>" "<firstName>" "<middleName>" "<lastName>" "<dateOfBird>" "<careGiver>" "<email>" "<phoneOrFax>" "<addressLine1>" "<state>" "<city>" "<zipCode>" "<country>" "<randomRecord>" at CPC Wizard Page

    Examples:
      | dummyValue | accountType | accountRecordType          | identifier | firstName | middleName | lastName | dateOfBird | careGiver | email | phoneOrFax | addressLine1      | state | city | zipCode | country       | randomRecord |
      | pimpinela  | CPC         | Consumer/Patient/Caregiver | AUT_CPC_   | Doña      | Federica   | Camaneya | 15/02/2020 | RND       | RND   | 1111111111 | Emiratos Arabelos | AL    | RND  | 90211   | United States | RND          |


  Scenario Outline: Create a new Employee Account from CustomerLookup page
    Given A dummyValue I enter the first name of the Employee as "<dummyValue>" with and account type "<accountType>" at CustomerLookup page
    Then I click on new Account at CustomerLookUp Page
    Given An account record type for Employee "<accountRecordType>" i click continue button at New Account page
    Then I fill the form with the values "<identifier>" "<firstName>" "<middleName>" "<lastName>" "<randomRecord>" at Employee Wizard Page

    Examples:
      | dummyValue | accountType | accountRecordType | identifier | firstName | middleName | lastName | randomRecord |
      | dummy      | EMP         | Internal AZ       | AUT_EMP_   | AlucheDos | El Muerto  | Camaney  | RND          |