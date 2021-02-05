Feature: Setup configurable consents

  Background:
    Given I login as an "admin" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option

  @regression
  Scenario: Create AZ product enrollment consent
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid "AZ" product in the product enrollment form
    And I click on enroll button
    And I select the created program enrollment
    And I select the "Accounts" menu option
    And I validate the patient account was created

  @regression
  Scenario: Create DSI product enrollment consent
    Given I click on new Account
    When I click on new and I select "Consumer/Patient/Caregiver" account
    Then I fill the mandatory fields from the account form
    And I click on new product enrollment button
    And I enter a valid "DSI" product in the product enrollment form
    And I click on enroll button
    And I select the created program enrollment
    And I select the "Accounts" menu option
    And I validate the patient account was created

  Scenario: Create a consent for AZ
    Given A External ID "EBcEenr" I Search A CPC At Customer Lookup
    Then I Click On The External ID Found For CPC At Customer Lookup
    Then I Click On The Consent Tab To Click The New Consent Button At Person Account Page
    And I select the "AZ Non-Promotional" consent type at new consent wizard page
    And I Fill The Selected Consent Type Form At New Consent Wizard Page
    Then I Select The Consent Address In The New Consent Wizard Page
    Then I Click On The Product Enrollment "Calquence" From The Person Account Page
    Then I Validate That No Warning "No AZ Non-Promotional Consent is on file." Message Is Displayed Related To The Type Lacking Of A Consent At The Product Enrollment Page
    And I select the "Accounts" menu option
    Then I Validate The Valid PAF "AstraZeneca" Message At Valid PAF Column At Accounts Recently Viewed Page


  Scenario: Create a consent for DSI
    Given A External ID "EBcEenr" I Search A CPC At Customer Lookup
    Then I Click On The External ID Found For CPC At Customer Lookup
    Then I Click On The Consent Tab To Click The New Consent Button At Person Account Page
    And I select the "DSI Promotional Consent" consent type at new consent wizard page
    And I Fill The Selected Consent Type Form At New Consent Wizard Page
    Then I Select The Consent Address In The New Consent Wizard Page
    Then I Click On The Product Enrollment "Enhertu" From The Person Account Page
    Then I Validate That No Warning "No DSI Non-Promotional Consent is on file." Message Is Displayed Related To The Type Lacking Of A Consent At The Product Enrollment Page
    And I select the "Accounts" menu option
    Then I Validate The Valid PAF "AstraZeneca" Message At Valid PAF Column At Accounts Recently Viewed Page

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
    And I select the Attestation tab option to click on new consent
    And I select the "DSI FLSP Attestation" consent type at new consent wizard page
    And I validate the attestation details are displayed
    And I fill the DSI FLSP mandatory fields from the consent form
    And I select an existing HCP
    And I verify the DSI consent details displayed