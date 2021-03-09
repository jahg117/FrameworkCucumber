package stepDefinition.acessServices;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObject.ApplicationInstance;

public class CreateAnAccount extends ApplicationInstance {

    //============HCA
    @Given("A dummyValue I enter the name of the facility as {string} with and account type {string} at CustomerLookup page")
    public void doDummySearchHCA(String dummyValue, String accountType) throws Exception {
        accessServices.getCustomerLookupPage().doDummySearch(dummyValue, accountType);
    }

    @Then("^I click on new Account at CustomerLookUp Page$")
    public void clickNewAccount() throws Exception {
        accessServices.getAccessServicesHomePage().isAccessServicesTitleVisible();
        accessServices.getCustomerLookupPage().clickNewAccount();
    }

    @Then("I fill the form with the values {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} at HCA Wizard Page")
    public void fieldsAccountFormHCA(String identifier, String npi, String nameHCA, String email, String phoneOrFax, String addressLine1, String state, String city, String zipCode, String country, String randomRecord) throws Exception {
        accessServices.getNewHCAWizardPage().isNewHCAWizardFormDisplayed();
        accessServices.getNewHCAWizardPage().validateAndCreateHCA(identifier, npi, nameHCA, email, phoneOrFax, addressLine1, state, city, zipCode, country, randomRecord);
    }

    //============HCP
    @Given("A dummyValue I enter the first name of the HCP as {string} with and account type {string} at CustomerLookup page")
    public void doDummySearchHCP(String dummyValue, String accountType) throws Exception {
        accessServices.getCustomerLookupPage().doDummySearch(dummyValue, accountType);
    }

    @Given("An account record type for HCP {string} i click continue button at New Account page")
    public void selectHCPRecordType(String hcpOption) throws Exception {
        accessServices.getNewAccountPage().selectRecordType(hcpOption);
    }

    @Then("I fill the form with the values {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} at HCP Wizard Page")
    public void fieldsAccountFormHCP(String identifier, String npi, String firstName, String middleName, String lastName, String dateOfBird, String email, String phoneOrFax, String addressLine1, String state, String city, String zipCode, String country, String randomRecord) throws Exception {
        accessServices.getNewHCPWizardPage().isNewHCPWizardFormDisplayed();
        accessServices.getNewHCPWizardPage().validateAndCreateHCP(identifier, npi, firstName, middleName, lastName, dateOfBird, email, phoneOrFax, addressLine1, state, city, zipCode, country, randomRecord);
    }

    //============CPC
    @Given("A dummyValue I enter the first name of the CPC as {string} with and account type {string} at CustomerLookup page")
    public void doDummySearchCPC(String dummyValue, String accountType) throws Exception {
        accessServices.getCustomerLookupPage().doDummySearch(dummyValue, accountType);
    }

    @Given("An account record type for CPC {string} i click continue button at New Account page")
    public void selectCPCRecordType(String cpcOption) throws Exception {
        accessServices.getNewAccountPage().selectRecordType(cpcOption);
    }

    @Then("I fill the form with the values {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} at CPC Wizard Page")
    public void fieldsAccountFormCPC(String identifier, String firstName, String middleName, String lastName, String dateOfBird, String careGiver, String email, String phoneOrFax, String addressLine1, String state, String city, String zipCode, String country, String randomRecord) throws Exception {
        accessServices.getNewCPCWizardPage().isNewCPCWizardFormDisplayed();
        accessServices.getNewCPCWizardPage().validateAndCreateCPC(identifier, firstName, middleName, lastName, dateOfBird, careGiver, email, phoneOrFax, addressLine1, state, city, zipCode, country, randomRecord);
    }

    //============EMPLOYEE
    @Given("A dummyValue I enter the first name of the Employee as {string} with and account type {string} at CustomerLookup page")
    public void doDummySearchEmployee(String dummyValue, String accountType) throws Exception {
        accessServices.getCustomerLookupPage().doDummySearch(dummyValue, accountType);
    }

    @Given("An account record type for Employee {string} i click continue button at New Account page")
    public void selectEmployeeRecordType(String empOption) throws Exception {
        accessServices.getNewAccountPage().selectRecordType(empOption);
    }

    @Then("I fill the form with the values {string} {string} {string} {string} {string} at Employee Wizard Page")
    public void fieldsAccountFormEmployee(String identifier, String firstName, String middleName, String lastName, String randomRecord) throws Exception {
        accessServices.getNewEmployeeWizardPage().isNewEmployeeWizardFormDisplayed();
        accessServices.getNewEmployeeWizardPage().validateAndCreateEmployee(identifier, firstName, middleName, lastName, randomRecord);
    }

    @Given("A {string} i select the view to filter using theProducts page")
    public void selectProductView(String productView) throws Exception {
        accessServices.getProductsPage().selectProductView(productView);
    }


}
