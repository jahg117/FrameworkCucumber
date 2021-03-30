@createData
  Feature: Patients

    Scenario Outline: Create Patients
      Given I login as an "<user>" user
      When the salesforce page is displayed
      Then I search the "Access Services" app
      And I select the "Customer Lookup" menu option
      And I click on new Account
      And I click on new and I select "Consumer/Patient/Caregiver" account
      And I fill the mandatory fields from the account form
      And I save the displayed patient ID
      And I create a list of product enrollments
        | ProductEnrollment |
        | Fasenra           |
        | Calquence         |
        | Enhertu           |
        | Koselugo          |
        | Lokelma           |
        | Hifrenzo          |
      And I click on new Case from the person account page
      And I select the case type option "Interaction"
      And I fill the new interaction mandatory fields "<channel>" "<caseStatus>"
      And I validate the interaction case number is displayed
      Examples:
        | user    | productName | caseType | channel    | caseStatus | caseSubType | discussTopic | cardNumber |
        | admin   | AZ          | random   | random     | Open       | random      | random       | random     |