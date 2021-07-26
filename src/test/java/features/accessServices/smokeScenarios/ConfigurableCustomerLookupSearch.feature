@smoke
Feature: Setup Search configurable
"""
//============Script Purpose:
Search operation for HCA - HCP - CPC and Employee

//============Script Configuration:
For each account there is some specific data use to perform a search below it can be find the description of each column
that contains the value to be search

Fields Available for searching:

accountType Options:
  * HCA = Facility
  * HCP = Prescribers
  * CPC = Consumer/Patients/Caregiver
  * Emp = Internal AZ
can be the following = HCA, HCP, CPC and EMP

externalID Options:
  * Is an ID assigned to each account created

npi options:
  * it is a number use for the  National Provider Identifier can be any number combination
nameHCA Options:
  * it is the name of the HCA that will be search

email options:
  * it contains the email related to the account to be search

phoneOrFax options:
  * it contains the phone or fax numbers assigned to the account that will be search
  e.g.
  4491234567

addressLine1 options:
  * it contains the address assigned to the account that will be search can be any string combination

state options:
  * it contains the code of the state assigned to the account can be the following
  AA, AE, AK, AL, AR, AS, AZ, CA, CO, CT, DC, DE, FM, FL, GA, GU, HI, IA, ID, IL, IN, KS, KY, LA, MA, MD, ME, MH, MI,
  MN, MO, MP, MS, MT, NC, ND, NE, NH, NJ, NM, NV, NY, OH, OK, OR, PA, PR, PW, RI, SC, SD, TN, TX, UT, VA, VI, VT, WA,
  WI, WV, WY

city options:
  * it contains the name of the city to be search can be any string combination

zipCode options:
  * it contains the zipcode can be any number combination

country options:
  * it contains the country for know it supports only United States

searchAllFromFile Options:
  * it will search the data from a file that contains data for accounts previous created just putting SFF in the column
  that will use as data for search.

firstName options:
  * it contains the first name use to create a HCP, CPC or Employee can be any string combination

middleName options:
  * it contains the middle name use to create a HCP, CPC or Employee can be any string combination

lastName options:
  * it contains the last name use to create a HCP, CPC or Employee can be any string combination

dateOfBird options:
  * it contains the date of birth with the following date format for HCP = Feb 9, 2021, for CPC = 15/02/2020

careGiver options:
  * it gives the option to search a careGiver

NOTE: Accounts can be created using:
  * Specific Data (putting the date in the column that we want that data)
  * SFF put in a column will search that specific field from a file that contains previously account created
  * Omitting some data (putting N_A it will ignore that field) just be sure that the field is not mandatory
"""

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option

  Scenario Outline: Create a new Facility Search from CustomerLookup page
    Given The account type "<accountType>" i select the filter to perform a search at CustomerLookup page
    Given A value or set of value to create a Facility Search I enter "<externalID>" "<npi>" "<nameHCA>" "<email>" "<phoneOrFax>" "<addressLine1>" "<state>" "<city>" "<zipCode>" "<country>" "<searchAllFromFile>" at CustomerLookup page
    Then I validate results from search


    Examples:
      | accountType | externalID | npi | nameHCA | email | phoneOrFax | addressLine1 | state | city | zipCode | country | searchAllFromFile |
      | HCA         | N_A        | SFF | SFF     | SFF   | SFF        | SFF          | SFF   | SFF  | SFF     | US      | Y                 |


  Scenario Outline: Create a new HCP Search from CustomerLookup page
    Given The account type "<accountType>" i select the filter to perform a search at CustomerLookup page
    Given A value or set of value to create a HCP Search I enter "<externalID>" "<npi>" "<firstName>" "<middleName>" "<lastName>" "<dateOfBirth>" "<email>" "<phoneOrFax>" "<addressLine1>" "<state>" "<city>" "<zipCode>" "<country>" "<searchAllFromFile>" at CustomerLookup page
    Then I validate results from search

    Examples:
      | accountType | externalID | npi | firstName | middleName | lastName | dateOfBirth | email | phoneOrFax | addressLine1     | state | city | zipCode | country | searchAllFromFile |
      | HCP         | N_A        | SFF | Don       | Juan       | Camaney  | Feb 9, 2021 | AA    | 6666666666 | Emiratos Latinos | AA    | RND  | 90210   | US      | Y                 |


  Scenario Outline: Create a new CPC Search from CustomerLookup page
    Given The account type "<accountType>" i select the filter to perform a search at CustomerLookup page
    Given A value or set of value to create a CPC Search I enter "<externalID>" "<firstName>" "<middleName>" "<lastName>" "<dateOfBirth>" "<careGiver>" "<email>" "<phoneOrFax>" "<addressLine1>" "<state>" "<city>" "<zipCode>" "<country>" "<searchAllFromFile>" at CustomerLookup page
    Then I validate results from search

    Examples:
      | accountType | externalID | firstName | middleName | lastName | dateOfBirth | careGiver | email | phoneOrFax | addressLine1      | state | city | zipCode | country | searchAllFromFile |
      | CPC         | N_A        | Dona      | Federica   | Camaneya | 15/02/2020  | TEST      | TEST  | 1111111111 | Emiratos Arabelos | AL    | RND  | 90211   | US      | Y                 |


  Scenario Outline: Create a new Employee Search from CustomerLookup page
    Given The account type "<accountType>" i select the filter to perform a search at CustomerLookup page
    Given A value or set of value to create a Employee Search I enter "<externalID>" "<firstName>" "<middleName>" "<lastName>" "<searchAllFromFile>" at CustomerLookup page
    Then I validate results from search

    Examples:
      | accountType | externalID | firstName | middleName | lastName | searchAllFromFile |
      | EMP         | N_A        | AlucheDos | El Muerto  | Camaney  | N                 |