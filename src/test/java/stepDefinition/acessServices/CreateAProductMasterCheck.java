package stepDefinition.acessServices;


import base.functions.CommonFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;
import stepDefinition.shareData.ProductEnrollment;
import stepDefinition.shareData.ProductServicesProvided;

import java.util.List;

public class CreateAProductMasterCheck extends ApplicationInstance {
    private CommonData commonData;
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    public CreateAProductMasterCheck(CommonData commonData) {
        this.commonData = commonData;
    }

    //============PRODUCT MASTER CHECK By ACCESS SERVICES>PRODUCT SELECTION
    @Given("The product select view title {string} i select the product view filter at Products page")
    public void selectProductView(String productView) throws Exception {
        accessServices.getProductsPage().selectProductView(productView);
    }

    @Then("I search and select for the {string} {string} at Products page")
    public void searchProductByName(String productName, String searchFromFile) throws Exception {
        if (searchFromFile.trim().equalsIgnoreCase("Y".trim())) {
            accessServices.getProductsPage().searchProductOrPE(productName = commonData.product.getProduct().trim());
        } else {
            accessServices.getProductsPage().searchProductOrPE(productName);
        }
        Assert.assertTrue(accessServices.getProductsPage().searchAndClickProductFromResults(productName), "Items Were Displayed");
    }

    @Then("I get the services provided list at Products page")
    public void getServicesProvidedList() throws Exception {
        commonData.productServicesProvided = new ProductServicesProvided(accessServices.getProductsPage().getServicesProvidedList());
    }

    //============PRODUCT ENROLLMENTS MASTER CHECK By ACCESS SERVICES>PRODUCT ENROLLMENTS SELECTION
    @Given("a product enrollment i search the PE {string} {string} and click it")
    public void searchPE(String productEnrollment, String searchFromFile) throws Exception {
        if (searchFromFile.trim().equalsIgnoreCase("Y".trim())) {
            accessServices.getProductsPage().searchProductOrPE(productEnrollment = commonData.productEnrollment.getProductEnrollment().trim());
        } else {
            accessServices.getProductsPage().searchProductOrPE(productEnrollment);
        }
        //accessServices.getProductsPage().searchProductOrPE(productEnrollment);//TBD FROM WHERE WE GETTING THE PE PE-005677 PE-000035
        Assert.assertTrue(accessServices.getProductEnrollmentPage().searchAndClickPEFromResults(productEnrollment), "No items to display with PE: " + productEnrollment);
        accessServices.getProductEnrollmentPage().clickOnNewCase();
    }

    @Then("I click on new case to validate the services provided for the product selected")
    public void validateServicesProvidedAvailable() throws Exception {
        Assert.assertTrue(accessServices.getNewCasePage().validateServicesProvidedAvailable(commonData.productServicesProvided.getServicesProvidedList()), "All Services Provided Did Matched");
    }

    @Then("I close all open tabs")
    public void closeOpenTabs() throws Exception {
        salesforce.getHomePage().closeOpenTabs();
    }

    @And("I close the last tab")
    public void closeLastTab() throws Exception {
        salesforce.getHomePage().closeLastTabSF(15);
    }
}
