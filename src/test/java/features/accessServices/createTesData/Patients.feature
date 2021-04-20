@createData
Feature: Patients
"""
//============Script Purpose:
It creates a Patient with:
Insurance(can be multiple Insurances Type "NOPI","PMI","PBM")
Product Enrollment(can be multiple PE)
Consents
Interaction case

//============Script Configuration:
user = Shall contain the user to login to SalesForce
channel = contains a list of option used to create a Interaction Case can be random
caseStatus = contains the status used for the Interation Case i.e. "Open", (can be random)
consentStatus = it contains the status for the creation of the new Consent i.e. "Active", (can be random)
consentDate = it contains the date for the new consent i.e. "3/31/2021", (can be random)
consentSource = it contains the type of source for the new consent i.e. "Fax", (can be random)
consentAuth = it contains the type of authorization for the new consent creation i.e. "Self", (can be random)
insuranceType = it contains the different type of insurance that can be created i.e "NOPI","PMI","PBM", if the whole three
types needs to be created in the same account please to put in the "insuranceType" column the following:
NOPI,PMI,PBM with this it will create the three types of insurances if only 2 are required shall be like
NOPI,PMI.

dataPMI = array  that contains the data need it to create a PMI insurance the array layout is the following:
Position 0 = Insurance Rank (Dropdown)
Position 1 = Relationship To CardHolder (Dropdown)
Position 2 = Cardholder Name (Input)
Position 3 = Cardholder DOB (Input Date)
Position 4 = Insurance Payer Name (Search Input)
Position 5 = Insurance Plan Name (Search Input)
Position 6 = Insurance Phone Number (Input)
Position 7 = Group Number (Input)
Position 8 = Policy ID (Input)
Position 9 = Member ID (Input)

Example of a PMI Record:
RND,Self,Vandame,4/6/2021,Danaher,Danaher Plan,2403061405,RND,RND,RND

dataPBM = array  that contains the data need it to create a PBM insurance the array layout is the following:
Position 0 = Insurance Rank (Dropdown)
Position 1 = Relationship To CardHolder (Dropdown)
Position 2 = Cardholder Name (Input)
Position 3 = Cardholder DOB (Input Date)
Position 4 = Insurance Payer Name (Search Input)
Position 5 = Insurance Plan Name (Search Input)
Position 6 = Insurance Phone Number (Input)
Position 7 = Group Number (Input)
Position 8 = Policy ID (Input)
Position 9 = Member ID (Input)
Position 10 = BIN Number (Input)
Position 11 = PCN (Input)

Example of a PBM Record:
RND,Self,Pepe Le Pu,4/5/2021,Danaher,Danaher Plan,2403061405,RND,RND,RND,RND,RND

Note: For PMI and PBM if some data is not required it can be ignore, putting in the record layout N_A and if some data needs to be
      random you shall put in the respective index the word RND.
"""

  Scenario Outline: Create Patients T001
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option
    And I click on new Account
    And I click on new and I select "Consumer/Patient/Caregiver" account
    And I fill the fields from the account form
      | name    | fax        | phoneType | zipcode |
      | AutoSPP | 2403061405 | Home Fax  | 06023   |
    And I save the displayed patient ID
    Then I click on Payer tab from PersonAccountPage
    Then I select the "<insuranceType>" and i fill the insurance form with "<dataPMI>" or "<dataPMB>"
    And I create a list of product enrollments
      | ProductEnrollment |
      | Fasenra           |
    And I click on the consent tab to click the new consent button at person account page
    And I select the "AZ Non-Promotional Consent" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentStatus>" "<consentDate>" "<consentSource>" "<consentAuth>" at new consent wizard page
    And I select the consent address in the new consent wizard page
    And I validate the consent ID is displayed
    And I close the last sub tab
    And I click on the consent tab to click the new consent button at person account page
    And I select the "DSI Non-Promotional Consent" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentStatus>" "<consentDate>" "<consentSource>" "<consentAuth>" at new consent wizard page
    And I select the consent address in the new consent wizard page
    And I validate the consent ID is displayed
    And I close the last sub tab
    And I click on new Case from the person account page
    And I select the case type option "Interaction"
    And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
    Examples:
      | user  | channel | caseStatus | consentStatus | consentDate | consentSource | consentAuth | insuranceType | dataPMI                                                      | dataPMB                                                    |
      | admin | random  | Open       | Active        | 4/15/2021   | RND           | Self        | PBM,PMI       | RND,Self,N_A,N_A,Danaher,Danaher Plan,2403061405,RND,RND,RND | RND,Self,N_A,N_A,Danaher,Danaher Plan,2403061405,RND,RND,RND,N_A,N_A |