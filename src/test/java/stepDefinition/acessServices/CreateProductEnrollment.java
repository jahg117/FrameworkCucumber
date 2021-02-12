package stepDefinition.acessServices;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;
import stepDefinition.shareData.Patient;
import stepDefinition.shareData.ProductEnrollment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateProductEnrollment extends ApplicationInstance {
    private CommonData commonData;
    private String product;

    public CreateProductEnrollment(CommonData commonData){
        this.commonData = commonData;
    }
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
        boolean page = accessServices.getNewPatientConsumerCaregiverPage().isConsumerPatientCaregiverFormDisplayed();
        Assert.assertTrue(page, "The Patient/Consumer/Caregiver page was not displayed");
        HashMap<String, String> patientDetails = accessServices.getNewPatientConsumerCaregiverPage().fillPatientConsumerCaregiverForm();
        accessServices.getNewPatientConsumerCaregiverPage().clickSaveButton();
        commonData.patient = new Patient(patientDetails);
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
        Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
        String productEnrollment = accessServices.getPersonAccountPage().getProductEnrollmentNumber(product);
        Assert.assertTrue(accessServices.getPersonAccountPage().isRedIconDisplayed(product), "The red icon is displayed");
        String newProduct = accessServices.getPersonAccountPage().clickProductEnrollmentAdded(product);
        Assert.assertEquals(product,newProduct, "The product enrollment is not matching");
        Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(),"The product enrollment page was not displayed");
        Assert.assertEquals(productEnrollment, accessServices.getProductEnrollmentPage().getProductEnrollmentNumber(), "The product enrollment number is not matching");
    }
    @And("^I enter a product enrollment in the product enrollment form$")
    public void createProductEnrollment(DataTable dataTable) throws Exception{
        List<Map<String , String>> list = dataTable.asMaps(String.class, String.class);
        ArrayList<String> productEnrollments = new ArrayList<>();
        for(Map<String, String> el : list){
            String product = el.get("ProductEnrollment");
            accessServices.getPersonAccountPage().clickNewProductEnrollment();
            product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(product);
            accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
            accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
            productEnrollments.add(accessServices.getProductEnrollmentPage().getProductEnrollmentNumber());
            accessServices.getSubTabsPage().closeSubTab(0);
        }
        accessServices.getPersonAccountPage().clickViewAllProgramEnrollments();
        accessServices.getProductEnrollmentsTablePage().isProductEnrollmentsPageDisplayed();
        Assert.assertEquals(productEnrollments, accessServices.getProductEnrollmentsTablePage().getProductEnrollmentsList(), "The list of product enrollments is not matching");
    }

    @And("^I validate the product enrollment is displayed")
    public void productEnrollmentDisplayed() {
        Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
        commonData.productEnrollment = new ProductEnrollment(accessServices.getProductEnrollmentPage().getProductEnrollmentNumber());
    }

}
