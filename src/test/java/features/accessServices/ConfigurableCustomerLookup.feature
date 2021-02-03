Feature: Setup configurable consents

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option

  @regression
  Scenario Outline: Create a new Account from Customer Lookup page
    Given Account type
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid "AZ" product in the product enrollment form
    And I click on enroll button
    And I select the created program enrollment
    And I select the "Accounts" menu option
    And I validate that the patient account was created

    Examples:
      | dummyName | facilityName | title |

  Scenario: Create a consent for DSI
    Given A External ID "EBcEenr" I Search A CPC At Customer Lookup
    Then I Click On The External ID Found For CPC At Customer Lookup
    Then I Click On The Consent Tab To Click The New Consent Button At Person Account Page
    And I Select The "DSI Promotional Consent" Consent Type At New Consent Wizard Page
    And I Fill The Selected Consent Type Form At New Consent Wizard Page
    Then I Select The Consent Address In The New Consent Wizard Page
    Then I Click On The Product Enrollment "Enhertu" From The Person Account Page
    Then I Validate That No Warning "No DSI Non-Promotional Consent is on file." Message Is Displayed Related To The Type Lacking Of A Consent At The Product Enrollment Page
    And I select the "Accounts" menu option
    Then I Validate The Valid PAF "AstraZeneca" Message At Valid PAF Column At Accounts Recently Viewed Page
