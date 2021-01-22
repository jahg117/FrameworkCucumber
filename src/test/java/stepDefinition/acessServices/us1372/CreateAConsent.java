package stepDefinition.acessServices.us1372;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pageObject.ApplicationInstance;

public class CreateAConsent extends ApplicationInstance {

    String product = "";

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

    @And("^I enter a valid \"([^\"]*)\" product in the product enrollment form$")
    public void fillMandatoryFieldsProgramEnrollment(String productType) throws Exception{
        Assert.assertTrue(accessServices.getCreateNewEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
        product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(productType);
    }

    @And("^I click on enroll button$")
    public void clickEnrollButton() throws Exception {
        accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
    }

    @And("^I select the created program enrollment$")
    public void selectProgramEnrollment() throws Exception{
        String productEnrollment = accessServices.getPersonAccountPage().getProductEnrollmentNumber(product);
        Assert.assertTrue(accessServices.getPersonAccountPage().isRedIconDisplayed(product), "The red icon is displayed");
        String newProduct = accessServices.getPersonAccountPage().clickProductEnrollmentAdded(product);
        Assert.assertEquals(product,newProduct, "The product enrollment is not matching");
        Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(),"The product enrollment page was not displayed");
        Assert.assertEquals(productEnrollment, accessServices.getProductEnrollmentPage().getProductEnrollmentNumber(), "The product enrollment number is not matching");
    }
}
