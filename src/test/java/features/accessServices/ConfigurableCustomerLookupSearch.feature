@regressionJR
Feature: Setup configurable consents

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
      | CPC         | N_A        | Doña      | Federica   | Camaneya | 15/02/2020  | TEST      | TEST  | 1111111111 | Emiratos Arabelos | AL    | RND  | 90211   | US      | Y                 |


  Scenario Outline: Create a new Employee Search from CustomerLookup page
    Given The account type "<accountType>" i select the filter to perform a search at CustomerLookup page
    Given A value or set of value to create a Employee Search I enter "<externalID>" "<firstName>" "<middleName>" "<lastName>" "<searchAllFromFile>" at CustomerLookup page
    Then I validate results from search

    Examples:
      | accountType | externalID | firstName | middleName | lastName | searchAllFromFile |
      | EMP         | N_A        | AlucheDos | El Muerto  | Camaney  | N                 |