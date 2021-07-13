@smokeTest
Feature: Setup configurable consents
"""
//============Script Purpose:
The script is to validate that the amount of products related to a drug match with the ones that are displayed when creating
a case from PE

//============Script Configuration:
For each account there is an number of columns that contains the data available to create that specific account, as mentioned below
there are some option depending of your needs.

accountType Options:
  * HCA = Facility
  * HCP = Prescribers
  * CPC = Consumer/Patients/Caregiver
  * Emp = Internal AZ
can be the following = HCA, HCP, CPC and EMP

selectProductView Options:
  * use to select which view will be selected to search the product

productEnrollmentView Options:
  * use to select the view to search the PE view

productName Options:
  * use to search with product will be validated

createPE Options:
  * use in case a creation of a new PE is need it

NOTE:
  * for createPE if there is no need to create a PE it is necessary just to put N_A if there is a need to create the PE
    it is necessary to put Y

"""

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option

  Scenario Outline: Create product enrollment if it is necessary to create a new Product Master Service Provided validation from Products page
    Given "<createPE>" it selects which steps will be execute using it from CommonGeneralSteps
    Given I click on new Account
    When I click on new and I select from table the "<accountType>" account
    Then I fill the mandatory fields from the account form
    And I enter a product enrollment in the product enrollment form
      | ProductEnrollment |
      | DSI               |
    Then I Validate the product enrollment is displayed at Product Enrollments table
    Then I close all open tabs
    And I select the "Products" menu option
    Given The product select view title "<selectProductView>" i select the product view filter at Products page
    Then I search and select for the "<productName>" "<createPE>" at Products page
    Then I get the services provided list at Products page
    Then I search the "Access Services" app
    Then I close all open tabs
    And I select the "Product Enrollments" menu option
    Given The product select view title "<productEnrollmentView>" i select the product view filter at Products page
    Given a product enrollment i search the PE "<productEnrollment>" "<createPE>" and click it
    Then I click on new case to validate the services provided for the product selected
    Then delete the value of the executionFlag

    Examples:
      | accountType | selectProductView | productEnrollmentView | productName | productEnrollment | createPE |
      | CPC         | All Products      | Recently Viewed       | ENHERTU     | PE-001504         | Y        |