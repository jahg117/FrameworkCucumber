package stepDefinition.acessServices.accessServices;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObject.ApplicationInstance;
import org.testng.Assert;
import stepDefinition.shareData.CommonData;
import stepDefinition.shareData.ConsentType;

public class CreateAConsent extends ApplicationInstance {
    private CommonData commonData;

    public CreateAConsent(CommonData commonData){
        this.commonData = commonData;
    }

    @Given("^A external ID \"([^\"]*)\" I search a CPC at customer lookup$")
    public void searchCPCByID(String cpcID) throws Exception {
        accessServices.getCustomerLookupPage().searchCPCByID(cpcID);
    }

    @Then("^I click on the external ID found for CPC at customer lookup$")
    public void selectAndClickAZID() throws Exception {
        accessServices.getCustomerLookupPage().selectAndClickAZID();
    }

    @Then("^I click cn the consent tab to click the new consent button at person account page$")
    public void clickOnNewConsent() throws Exception {
        accessServices.getPersonAccountPage().clickOnNewConsent();
    }

    @And("^I select the \"([^\"]*)\" consent type at new consent wizard page$")
    public void selectConsentType(String consentType) throws Exception {
        accessServices.getNewConsentPage().selectConsentType(consentType);
        commonData.consentType = new ConsentType(consentType);
    }

    @And("^I fill the selected consent type form at new consent wizard page$")
    public void fillConsentForm() throws Exception {
        accessServices.getNewConsentWizard().fillConsentForm("Active","1/25/2021","","");
    }

    @Then("^I select the consent address in the new consent wizard page$")
    public void selectConsentAddress() throws Exception {
        accessServices.getNewConsentWizard().selectConsentAddress(true, 0);
    }

    @Then("^I click on the product enrollment \"([^\"]*)\" from the person account page$")
    public void clickProductEnrollmentAdded(String product) throws Exception{
        accessServices.getPersonAccountPage().switchToTab(0);
        accessServices.getPersonAccountPage().clickOnProgramEnrollments();
        accessServices.getPersonAccountPage().clickProductEnrollmentAdded(product);
    }

    @Then("^I validate that no warning \"([^\"]*)\" message is displayed related to the type lacking of a consent at the product enrollment page$")
    public void validatePEDSIMessage(String messagePE) throws Exception{
        boolean messageCosentType = accessServices.getProductEnrollmentPage().validatePEDSIMessage(messagePE);
        Assert.assertEquals(messageCosentType, false, "There Is Still A Warning Message With The Following History " + messagePE);
    }

    @Then("^I validate the valid PAF \"([^\"]*)\" message at valid PAF column at accounts recently viewed page$")
    public void validateValidPAFValue(String validPAF) throws Exception{
        Assert.assertTrue(accessServices.getAccountsRecentlyViewedPage().validateValidPAFValue(validPAF), "The Valid PAF Column Message '" + validPAF + "' Matched");
    }
}
