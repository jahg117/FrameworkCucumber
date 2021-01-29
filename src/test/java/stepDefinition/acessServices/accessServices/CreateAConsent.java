package stepDefinition.acessServices.accessServices;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObject.ApplicationInstance;
import org.testng.Assert;

public class CreateAConsent extends ApplicationInstance {

    @Given("^A External ID \"([^\"]*)\" I Search A CPC At Customer Lookup$")
    public void searchCPCByID(String cpcID) throws Exception {
        accessServices.getCustomerLookupPage().searchCPCByID(cpcID);
    }

    @Then("^I Click On The External ID Found For CPC At Customer Lookup$")
    public void selectAndClickAZID() throws Exception {
        accessServices.getCustomerLookupPage().selectAndClickAZID();
    }

    @Then("^I Click On The Consent Tab To Click The New Consent Button At Person Account Page$")
    public void clickOnNewConsent() throws Exception {
        accessServices.getPersonAccountPage().clickOnNewConsent();
    }

    @And("^I Select The \"([^\"]*)\" Consent Type At New Consent Wizard Page$")
    public void selectConsentType(String consentType) throws Exception {
        accessServices.getNewConsentWizard().selectConsentType(consentType);
    }

    @And("^I Fill The Selected Consent Type Form At New Consent Wizard Page$")
    public void fillConsentForm() throws Exception {
        accessServices.getNewConsentWizard().fillConsentForm("Active","1/25/2021","","");
    }

    @Then("^I Select The Consent Address In The New Consent Wizard Page$")
    public void selectConsentAddress() throws Exception {
        accessServices.getNewConsentWizard().selectConsentAddress(true, 0);
    }

    @Then("^I Click On The Product Enrollment \"([^\"]*)\" From The Person Account Page$")
    public void clickProductEnrollmentAdded(String product) throws Exception{
        accessServices.getPersonAccountPage().switchToTab(0);
        accessServices.getPersonAccountPage().clickOnProgramEnrollments();
        accessServices.getPersonAccountPage().clickProductEnrollmentAdded(product);
    }

    @Then("^I Validate That No Warning \"([^\"]*)\" Message Is Displayed Related To The Type Lacking Of A Consent At The Product Enrollment Page$")
    public void validatePEDSIMessage(String messagePE) throws Exception{
        boolean messageCosentType = accessServices.getProductEnrollmentPage().validatePEDSIMessage(messagePE);
        Assert.assertEquals(messageCosentType, false, "There Is Still A Warning Message With The Following History " + messagePE);
    }

    @Then("^I Validate The Valid PAF \"([^\"]*)\" Message At Valid PAF Column At Accounts Recently Viewed Page$")
    public void validateValidPAFValue(String validPAF) throws Exception{
        Assert.assertTrue(accessServices.getAccountsRecentlyViewedPage().validateValidPAFValue(validPAF), "The Valid PAF Column Message '" + validPAF + "' Matched");
    }
}
