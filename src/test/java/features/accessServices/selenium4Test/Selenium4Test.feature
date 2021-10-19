@S4Test
Feature: Selenium 4 Tests
"""
//============Script Purpose:

"""

  Scenario Outline: Selenium 4 Tests Scenarios
    Given I login as an "<user>" user
    When the salesforce page is displayed
    Then I search the "Access Services" app
    Then I wait to take a screenshot from salesforce Logo
    And I select the "Customer Lookup" menu option
    And I click on new Account
    And I click on new and I select "Consumer/Patient/Caregiver" account
    And I fill the fields from the account form PDC Using "<accData>"






    Examples: Patient Creation Table
    ====================================================COMMENTS========================================================
    ====================================================ACCOUNDATA======================================================
    accData = P0:Identifier,P1:First Name,P2:Last Name,P3:First Phone/Fax,P4:First Phone/Fax Type(Mobile, HomeFax etc...),
    P5:Second Phone/Fax,P6:Second Phone/Fax Type(Mobile, HomeFax etc...),P7:ZipCode,P8:EmailDomain,P9:Address,P10:Date,P11:City
    e.g.
    P0:N_A,P1:CuterfasoDos,P2:MocherfasoDos,P3:RND,P4:RND,P5:RND,P6:RND,P7:RND,P8:RND,P9:RND,P10:RND,P11:RND
    ====================================================INSURANCE PMI===================================================
    dataPMI = P0:Insurance Rank,P1:Relationship To CardHolder,P2:Cardholder Name,P3:Cardholder DOB,P4:Insurance Payer Name,
    P5:Insurance Plan Name,P6:Insurance Phone Number,P7:Group Number,P8:Policy ID,P9:Member ID
    e.g.
    P0:RND,P1:Self,P2:N_A,P3:N_A,P4:UAT Test Payer 1,P5:UAT Payer Test Plan 1,P6:13019658622,P7:RND,P8:RND,P9:RND
    ====================================================INSURANCE PBM===================================================
    dataPBM = P0:Insurance Rank,P1:Relationship To CardHolde,P2:Cardholder Name,P3:Cardholder DOB,P4:Insurance Payer Name,
    P5:Insurance Plan Name,P6:Insurance Phone Number,P7:Group Number,P8:Policy ID,P9:Member ID,P10:BIN NUMBER,P11:PCN
    e.g.
    P0:RND,P1:Self,P2:N_A,P3:N_A,P4:UAT Test Payer 1,P5:UAT Payer Test Plan 1,P6:13019658622,P7:RND,P8:RND,P9:RND,P10:N_A,P11:N_A
    ====================================================CARETEAM MEMBER DATA============================================
    ctmData = P0:Careteam member type,P1:Careteam member type Email,P2:Careteam member RelationShip
    e.g.
    P0:hca_hcp,P1:internal.facility_hcp.specialty,P2:TF_PP
    Note: After the underscore (_) means that the script will be execute with that option too
    ====================================================CONSENT DATA====================================================
    consentData = P0:Consent Status,P1:Consent Date(mm/dd/yyyy),P2:Consent Source,P3:Consent Authorization
    e.g.
    P0:Active,P1:30/07/2021,P2:RND,P3:Self
    ====================================================INTERACTION DATA================================================
    irData = P0:Channel, P1:CaseStatus
    e.g.
    P0:RND,P1:Active
    ====================================================================================================================
    CONFIG DATA:

      | user      | drugPE | cases | caseData             | insuranceType | irCase | consentDataAZ                | consentDataDSI               | accData                                                                                                                           | dataPMI                                                                                              | dataPBM                                                                                                                   | ctmData                                                | irData         |
      | UAT_admin | P30    | P10   | RND,Open,RND,RND,RND | N_A,N_A,PBM   | P2     | ANP,Active,Today,Fax,Self,P3 | N_A,Active,Today,Fax,Self,P0 | P0:AutSPPData_,P1:Patient,P2:RND,P3:RND,P4:RND,P5:Home Phone,P6:2403061405,P7:Home Fax,P8:RND,P9:RND,P10:RND,P11:12121991,P12:RND | P0:RND,P1:N_A,P2:N_A,P3:N_A,P4:UAT Test Payer 1,P5:UAT Payer Test Plan 1,P6:N_A,P7:N_A,P8:N_A,P9:N_A | P0:Primary,P1:Self,P2:N_A,P3:N_A,P4:UAT Test Payer 1,P5:UAT Payer Test Plan 1,P6:N_A,P7:N_A,P8:N_A,P9:N_A,P10:N_A,P11:N_A | P0:hca_hcp,P1:internal.facility_hcp.specialty,P2:PF_PP | P0:RND,P1:Open |



















