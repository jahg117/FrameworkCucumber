@smoke
Feature: Setup configurable consents
"""
//============Script Purpose:
Creation Of HCA - HCP - CPC and Employee

//============Script Configuration:
For each account there is an number of columns that contains the data available to create that specific account, as mentioned below
there are some option depending of your needs.

accountType Options:
  * HCA = Facility
  * HCP = Prescribers
  * CPC = Consumer/Patients/Caregiver
  * Emp = Internal AZ
can be the following = HCA, HCP, CPC and EMP

accountRecordType Options:
  * Health Care Account
  * Health Care Provider
  * Consumer/Patient/Caregiver
  * Internal AZ
can be the following = Health Care Account, Health Care Provider, Consumer/Patient/Caregiver, Internal AZ

identifier Options:
  * Can be any text, used to identified the accounts created can be any string combination

npi options:
  * it is a number use for the  National Provider Identifier can be any number combination

nameHCA options:
  * it contains the name used to create the HCA can be any string combination

email options:
  * it contains the email can be any

phoneOrFax options:
  * it contains the phone or fax numbers can be any number combination

addressLine1 options:
  * it contains the address can be any string combination

state options:
  * it contains the code of the state can be the following
  AA, AE, AK, AL, AR, AS, AZ, CA, CO, CT, DC, DE, FM, FL, GA, GU, HI, IA, ID, IL, IN, KS, KY, LA, MA, MD, ME, MH, MI,
  MN, MO, MP, MS, MT, NC, ND, NE, NH, NJ, NM, NV, NY, OH, OK, OR, PA, PR, PW, RI, SC, SD, TN, TX, UT, VA, VI, VT, WA,
  WI, WV, WY

city options:
  * it contains the name of the city can be any string combination

zipCode options:
  * it contains the zipcode can be any number combination

country options:
  * it contains the country for know it supports only United States

randomRecord options:
  * can be used to create a whole account with random data, putting RND word

firstName options:
  * it contains the first name use to create a HCP, CPC or Employee can be any string combination

middleName options:
  * it contains the middle name use to create a HCP, CPC or Employee can be any string combination

lastName options:
  * it contains the last name use to create a HCP, CPC or Employee can be any string combination

dateOfBird options:
  * it contains the date of birth with the following date format for HCP = Feb 9, 2021, for CPC = 15/02/2020

careGiver options:
  * it gives the option to select a careGiver when subtype is selected as caregiver it will select random or a valid ctm name

NOTE: Accounts can be created using:
  * Specific Data (putting the date in the column that we want that data)
  * Randomly Specific Data (putting RND word in the column data that we want to be randomly)
  * Complete Randomly (putting the RND in the randomRecord)
  * Omitting some data (putting N_A it will ignore that field) just be sure that the field is not mandatory
"""

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option

  Scenario Outline: Create a new Facility Account from CustomerLookup page

    Given A dummyValue I enter the name of the facility as "<dummyValue>" with and account type "<accountType>" at CustomerLookup page
    Then I click on new Account at CustomerLookUp Page
    Given An account record type for HCA "<accountRecordType>" i click continue button at New Account page
    Then I fill the form with the values "<identifier>" "<npi>" "<nameHCA>" "<email>" "<phoneOrFax>" "<addressLine1>" "<state>" "<city>" "<zipCode>" "<country>" "<randomRecord>" at HCA Wizard Page

    Examples: HCA Account Table
      | dummyValue | accountType | accountRecordType   | identifier | npi | nameHCA        | email | phoneOrFax | addressLine1 | state | city         | zipCode | country       | randomRecord |
      | dummy      | HCA         | Health Care Account | AUT_HCA    | RND | DonJuanCamaney | N_A   | 4491234567 | RND          | AA    | Guadalakjara | RND     | United States | RND          |


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
    Then I fill the form with the values "<identifier>" "<firstName>" "<middleName>" "<lastName>" "<dateOfBird>" "<careGiver>" "<email>" "<emailType>" "<phoneOrFax>" "<addressLine1>" "<state>" "<city>" "<zipCode>" "<country>" "<randomRecord>" at CPC Wizard Page

    Examples:
      | dummyValue | accountType | accountRecordType          | identifier | firstName | middleName | lastName | dateOfBird | careGiver | email | emailType | phoneOrFax | addressLine1      | state | city | zipCode | country       | randomRecord |
      | pimpinela  | CPC         | Consumer/Patient/Caregiver | AUT_CPC_   | Dona      | Federica   | Camaneya | 15/02/2020 | RND       | RND   | Home      | 1111111111 | Emiratos Arabelos | AL    | RND  | 90211   | United States | N_A          |


  Scenario Outline: Create a new Employee Account from CustomerLookup page

    Given A dummyValue I enter the first name of the Employee as "<dummyValue>" with and account type "<accountType>" at CustomerLookup page
    Then I click on new Account at CustomerLookUp Page
    Given An account record type for Employee "<accountRecordType>" i click continue button at New Account page
    Then I fill the form with the values "<identifier>" "<firstName>" "<middleName>" "<lastName>" "<randomRecord>" at Employee Wizard Page

    Examples:
      | dummyValue | accountType | accountRecordType | identifier | firstName | middleName | lastName | randomRecord |
      | dummy      | EMP         | Internal AZ       | AUT_EMP_   | AlucheDos | El Muerto  | Camaney  | RND          |