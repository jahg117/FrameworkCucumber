@productMasterCheckOperation
Feature: Setup configurable consents

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Products" menu option

  Scenario Outline: Create a new Product Master Service Provided validation from Products page
    Given The product select view title "<selectProductView>" i select the product view filter at Products page
    Then I search and select for the "<productName>" at Products page
    Then I get the services provided list at Products page
    Then I search the "Access Services" app
    Then I close all open tabs
    And I select the "Product Enrollments" menu option
    Given The product select view title "<productEnrollmentView>" i select the product view filter at Products page
    Given a product enrollment i search the PE "<productEnrollment>" and click it
    Then I click on new case to validate the services provided for the product selected

    Examples:
      | selectProductView | productEnrollmentView | productName | productEnrollment | searchFromFile |
      | All Products      | All                   | FASENRA     | PE-000035         | Y              |

