@refactor
Feature: Patient Data Creation
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
consentDate = it contains the date for the new consent i.e. "3/31/2021", (can be random, or especific with mm/dd/yyyy or Today)
consentSource = it contains the type of source for the new consent i.e. "Fax", (can be random)
consentAuth = it contains the type of authorization for the new consent creation i.e. "Self", (can be random)
insuranceType = it contains the different type of insurance that can be created i.e "NOPI","PMI","PBM", if the whole three
types needs to be created in the same account please to put in the "insuranceType" column the following:
NOPI,PMI,PBM with this it will create the three types of insurances if only 2 are required shall be like
NOPI,PMI.

RelationShip Options (HCA-HCP)
  * PP = Prescribing Physician
  * PF = Prescribing Facility
  * TP = Treating Physician
  * TF = Treating Facility
  * PH = Pharmacist
  * PM = Pharmacy
  * OS = Office Staff
  * OT = Other
  * OF = Other Facility

ConsentType Options
  * DFM = DSI Family Consent
  * DCC = DSI Copay Consent
  * APC = AZ Promotional Consent
  * DPC = DSI Promotional Consent
  * AFP = AZ Family Consent
  * ANP = AZ Non-Promotional Consent
  * DNC = DSI Non-Promotional Consent
  * ACC = AZ Copay Consent

CareTeam Member Type Options
  * hca (related to facilities)
  * hcp (related to prescribers)

dataPMI = array  that contains the data need it to create a PMI insurance

dataPBM = array  that contains the data need it to create a PBM insurance

Case Status For Interactions
  * Open
  * Closed

Note: For PMI and PBM if some data is not required it can be ignore, putting in the record layout N_A and if some data needs to be
      random you shall put in the respective index the word RND.
"""

  Scenario Outline: Patient Data Creation
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    And I select the "Customer Lookup" menu option
    And I click on new Account
    And I click on new and I select "Consumer/Patient/Caregiver" account
    And I fill the fields from the account form PDC Using "<accData>"
    And I click on new Case from the person account page
    And I select the case type option "Interaction"
    And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
    And I validate the interaction case number is displayed
    And I close the last sub tab
    And I create product enrollments "<productEnrollment>" with cases "<cases>" "<channel>" "<caseStatus>" "<caseSubType>" "<discussTopic>" "<cardNumber>" and care team members "<ctmData>"
    And I click on the consent tab to click the new consent button at person account page
    And I select the "<azConType>" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentData>" at new consent wizard page for PDC
    And I select the consent address in the new consent wizard page
    And I validate the consent ID is displayed
    And I close the last sub tab
    And I click on the consent tab to click the new consent button at person account page
    And I select the "<dsiConType>" consent type at new consent wizard page
    And I fill the selected consent type form with the following data "<consentData>" at new consent wizard page for PDC
    And I select the consent address in the new consent wizard page
    And I validate the consent ID is displayed
    And I close the last sub tab

    Examples: Patient Creation Table
      | user  | channel | caseStatus | caseSubType | discussTopic | cardNumber | productEnrollment | cases                       | accData                                                                                       | insuranceType | dataPMI                                                                                                              | dataPBM                                                                                                                       | ctmData                                                                             | consentData                 | azConType | dsiConType | irData         |
      | admin | RND     | Open       | RND         | RND          | RND        | Saphnelo,Fasenra  | P0:RND_RND,P1:Asset Request | P0:AutTest_,P1:RND,P2:RND,P3:RND,P4:Mobile,P5:RND,P6:RND,P7:RND,P8:RND,P9:RND,P10:RND,P11:RND | N_A,PMI,N_A   | P0:RND,P1:Self,P2:N_A,P3:24/11/2021,P4:UAT Test Payer 1,P5:UAT Payer Test Plan 1,P6:13019658622,P7:RND,P8:RND,P9:RND | P0:RND,P1:Self,P2:N_A,P3:N_A,P4:UAT Test Payer 1,P5:UAT Payer Test Plan 1,P6:13019658622,P7:RND,P8:RND,P9:RND,P10:N_A,P11:N_A | P0:hca_hcp,P1:internal.facility@hospital.com_hcp.specialty@astrazeneca.com,P2:PF_PP | Active, Today, RND, Self    | ANP       | DNC        | P0:RND,P1:Open |