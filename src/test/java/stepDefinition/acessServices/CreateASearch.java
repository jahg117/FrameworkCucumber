package stepDefinition.acessServices;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObject.ApplicationInstance;

import java.util.HashMap;

public class CreateASearch extends ApplicationInstance {

    //============Facility-HCP-CPC-Employee
    @Given("The account type {string} i select the filter to perform a search at CustomerLookup page")
    public void selectSearchFilter(String accountType) throws Exception {
        accessServices.getCustomerLookupPage().selectSearchFilter(accountType);
    }

    //============Facility-HCP-CPC-Employee
    @Then("I validate results from search")
    public void validateResultsFromSearch() throws Exception {
        accessServices.getCustomerLookupPage().validateSearchResults();
    }

    //============FACILITY(HCA)
    @Given("A value or set of value to create a Facility Search I enter {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} at CustomerLookup page")
    public void createAFacilitySearch(String externalID, String npi, String nameHCA, String email, String phoneOrFax, String addressLine1, String stateCode, String city, String zipCode, String country, String searchAllFromFile) throws Exception {
        HashMap<String, String> hcaDetails = accessServices.getCustomerLookupPage().createHCADataUseToSearch(externalID, npi, nameHCA, email, phoneOrFax, addressLine1, stateCode, city, zipCode, country, searchAllFromFile);
        accessServices.getCustomerLookupPage().doAFacilitySearch(hcaDetails);
        accessServices.getCustomerLookupPage().clickOnSearch();
    }


    //============HCP
    @Given("A value or set of value to create a HCP Search I enter {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} at CustomerLookup page")
    public void createHCPDataUseToSearch(String externalID, String npi, String firstName, String middleName, String lastName, String dateOfBirth, String email, String phoneOrFax, String addressLine1, String stateCode, String city, String zipCode, String country, String searchAllFromFile) throws Exception {
        HashMap<String, String> hcpDetails = accessServices.getCustomerLookupPage().createHCPDataUseToSearch(externalID, npi, firstName, middleName, lastName, dateOfBirth, email, phoneOrFax, addressLine1, stateCode, city, zipCode, country, searchAllFromFile);
        accessServices.getCustomerLookupPage().doAHCPSearch(hcpDetails);
        accessServices.getCustomerLookupPage().clickOnSearch();
    }

    //============CPC
    @Given("A value or set of value to create a CPC Search I enter {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} {string} at CustomerLookup page")
    public void createCPCDataUseToSearch(String externalID, String npi, String firstName, String middleName, String lastName, String dateOfBirth, String email, String phoneOrFax, String addressLine1, String stateCode, String city, String zipCode, String country, String searchAllFromFile) throws Exception {
        HashMap<String, String> cpcDetails = accessServices.getCustomerLookupPage().createCPCDataUseToSearch(externalID, npi, firstName, middleName, lastName, dateOfBirth, email, phoneOrFax, addressLine1, stateCode, city, zipCode, country, searchAllFromFile);
        accessServices.getCustomerLookupPage().doACPCSearch(cpcDetails);
        accessServices.getCustomerLookupPage().clickOnSearch();
    }

    //============EMPLOYEE
    @Given("A value or set of value to create a Employee Search I enter {string} {string} {string} {string} {string} at CustomerLookup page")
    public void createEmployeeDataUseToSearch(String externalID, String firstName, String middleName, String lastName, String searchAllFromFile) throws Exception {
        HashMap<String, String> employeeDetails = accessServices.getCustomerLookupPage().createEmployeeDataUseToSearch(externalID, firstName, middleName, lastName,searchAllFromFile);
        accessServices.getCustomerLookupPage().doAEmployeeSearch(employeeDetails);
        accessServices.getCustomerLookupPage().clickOnSearch();
    }
}
