package stepDefinition.acessServices.us1372;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObject.ApplicationInstance;

public class CreateAZProductEnrollmentConsent extends ApplicationInstance {

    @Given("^I click on new Account$")
    public void clickNewAccount() throws Exception {
        accessServices.getAccessServicesHomePage().isAccessServicesTitleVisible();
        accessServices.getCustomerLookupPage().clickNewAccount();
    }

    @When("^I click on new and I select \"([^\"]*)\" account$")
    public void selectAccountType(String accountType) throws Exception{
        accessServices.getNewAccountPage().selectRecordType(accountType);
    }

    @Then("^I fill the mandatory fields from the account form$")
    public void mandatoryFieldsAccountForm() throws Exception{
        accessServices.getNewPatientConsumerCaregiverPage().fillPatientConsumerCaregiverForm();
    }

    @And("^I click on new product enrollment button$")
    public void clickNewProductEnrollment() throws Exception{
        accessServices.getPersonAccountPage().clickNewProductEnrollment();
    }

    @And("^I fill the mandatory fields from the new program enrollment form$")
    public void fillMandatoryFieldsProgramEnrollment() throws Exception{
        accessServices.getCreateNewEnrollmentPage().enterProduct();
        accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
    }

    @And("^I select the created program enrollment$")
    public void clickCreatedProgramEnrollment() throws Exception{
        accessServices.getPersonAccountPage().clickProductEnrollmentAdded("FASENRA");
        accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
    }
}
