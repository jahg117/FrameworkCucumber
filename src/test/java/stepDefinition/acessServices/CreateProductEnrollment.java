package stepDefinition.acessServices;

import base.functions.CommonFunctions;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.it.Ma;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateProductEnrollment extends ApplicationInstance {
    private CommonData commonData;
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    private String product;
    private static String executionFlag = "";

    public CreateProductEnrollment(CommonData commonData) {
        this.commonData = commonData;
    }

    /**
     * Used to assign the value of searchFromFile to the executionFlag that will help to know if it is necessary to create a PE or used an specific one
     *
     * @param searchFromFile contains the value, that will be used as boolean, i.e. if it is come "", empty or with the value og "N_A" it will not create a product enrollment
     */
    @Given("{string} it selects which steps will be execute")
    public void selectStepsToExecute(String searchFromFile) {
        executionFlag = searchFromFile;
    }


    @Given("^I click on new Account$")
    public void clickNewAccount() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    accessServices.getAccessServicesHomePage().isAccessServicesTitleVisible();
                    accessServices.getCustomerLookupPage().clickNewAccount();
                } else {
                    logger.info("Does not required to be executed Since Flag Contains : " + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getAccessServicesHomePage().isAccessServicesTitleVisible();
            accessServices.getCustomerLookupPage().clickNewAccount();
        }

    }

    //======================================== USING DATATABLE
    @When("^I click on new and I select accountType$")
    public void selectAccountType(DataTable dataTable) throws Exception {
        List<Map<String, String>> accountTypeList = dataTable.asMaps(String.class, String.class);
        String dropdownOption = accessServices.getNewAccountPage().selectAccountTypeFromList(accountTypeList);
        dropdownOption = accessServices.getNewAccountPage().assignCorrectAccountTypeValue(dropdownOption);
        accessServices.getNewAccountPage().selectRecordType(dropdownOption);
    }

    @And("^I enter a valid consentType to get an available product in the product enrollment form$")
    public void fillMandatoryFieldsProgramEnrollmentUsingTable(DataTable dataTable) throws Exception {
        List<Map<String, String>> consentTypeList = dataTable.asMaps(String.class, String.class);
        Assert.assertTrue(accessServices.getCreateNewEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
        String consentType = accessServices.getNewAccountPage().selectAccountTypeFromList(consentTypeList);
        product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(consentType);
    }
    //========================================

    @Then("^I fill the mandatory fields from the account form$")
    public void mandatoryFieldsAccountForm() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    boolean page = accessServices.getNewPatientConsumerCaregiverPage().isConsumerPatientCaregiverFormDisplayed();
                    Assert.assertTrue(page, "The Patient/Consumer/Caregiver page was not displayed");
                    HashMap<String, String> patientDetails = accessServices.getNewPatientConsumerCaregiverPage().fillPatientConsumerCaregiverForm();
                    accessServices.getNewPatientConsumerCaregiverPage().clickSaveButton();
                    commonData.patient = new Patient(patientDetails);
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            boolean page = accessServices.getNewPatientConsumerCaregiverPage().isConsumerPatientCaregiverFormDisplayed();
            Assert.assertTrue(page, "The Patient/Consumer/Caregiver page was not displayed");
            HashMap<String, String> patientDetails = accessServices.getNewPatientConsumerCaregiverPage().fillPatientConsumerCaregiverForm();
            accessServices.getNewPatientConsumerCaregiverPage().clickSaveButton();
            commonData.patient = new Patient(patientDetails);
        }
    }

    @When("^I click on new and I select \"([^\"]*)\" account$")
    public void selectAccountType(String accountType) throws Exception {
        accessServices.getNewAccountPage().selectRecordType(accountType);
    }

    @And("^I click on new product enrollment button$")
    public void clickNewProductEnrollment() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    accessServices.getPersonAccountPage().clickNewProductEnrollment();
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getPersonAccountPage().clickNewProductEnrollment();
        }
    }

    @And("^I enter a valid \"([^\"]*)\" product in the product enrollment form$")
    public void fillMandatoryFieldsProgramEnrollment(String productType) throws Exception {
        Assert.assertTrue(accessServices.getCreateNewEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
        product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(productType);
    }

    @And("^I click on enroll button$")
    public void clickEnrollButton() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
                } else {
                    logger.info("Does not required to be executed Since Flag Contains : " + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
        }
    }

    @And("^I select the created program enrollment$")
    public void selectProgramEnrollment() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
                    String productEnrollment = accessServices.getPersonAccountPage().getProductEnrollmentNumber(product);
                    Assert.assertTrue(accessServices.getPersonAccountPage().isRedIconDisplayed(product), "The red icon is displayed");
                    String newProduct = accessServices.getPersonAccountPage().clickProductEnrollmentAdded(product);
                    Assert.assertEquals(product, newProduct, "The product enrollment is not matching");
                    Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
                    Assert.assertEquals(productEnrollment, accessServices.getProductEnrollmentPage().getProductEnrollmentNumber(), "The product enrollment number is not matching");
                } else {
                    logger.info("Does not required to be executed Since Flag Contains : " + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
            String productEnrollment = accessServices.getPersonAccountPage().getProductEnrollmentNumber(product);
            Assert.assertTrue(accessServices.getPersonAccountPage().isRedIconDisplayed(product), "The red icon is displayed");
            String newProduct = accessServices.getPersonAccountPage().clickProductEnrollmentAdded(product);
            Assert.assertEquals(product, newProduct, "The product enrollment is not matching");
            Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
            Assert.assertEquals(productEnrollment, accessServices.getProductEnrollmentPage().getProductEnrollmentNumber(), "The product enrollment number is not matching");
        }
    }

    @And("^I enter a product enrollment in the product enrollment form$")
    public void createProductEnrollment(DataTable dataTable) throws Exception {
        if (executionFlag.trim().equalsIgnoreCase("") || executionFlag.isEmpty() || !executionFlag.trim().equalsIgnoreCase("N_A")) {
            List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
            ArrayList<String> productEnrollments = new ArrayList<>();
            for (Map<String, String> el : list) {
                String product = el.get("ProductEnrollment");
                accessServices.getPersonAccountPage().clickNewProductEnrollment();
                commonData.product = new Product(accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(product));
                accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
                accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
                productEnrollments.add(accessServices.getProductEnrollmentPage().getProductEnrollmentNumber());
                accessServices.getSubTabsPage().closeSubTab(0);
            }
            accessServices.getPersonAccountPage().clickViewAllProgramEnrollments();
            accessServices.getProductEnrollmentsTablePage().isProductEnrollmentsPageDisplayed();
            Assert.assertEquals(productEnrollments, accessServices.getProductEnrollmentsTablePage().getProductEnrollmentsList(), "The list of product enrollments is not matching");
        } else {
            logger.info("Does not required to be executed Since Flag: " + executionFlag);
        }
    }

    @And("^I validate the product enrollment is displayed")
    public void productEnrollmentDisplayed() {
        Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
        commonData.productEnrollment = new ProductEnrollment(accessServices.getProductEnrollmentPage().getProductEnrollmentNumber());
    }

    @When("I click on new and I select from table the {string} account")
    public void selectFromTableAccountType(String accountType) throws Exception {
        if (executionFlag.trim().equalsIgnoreCase("") || executionFlag.isEmpty() || !executionFlag.trim().equalsIgnoreCase("N_A")) {
            accountType = accessServices.getNewAccountPage().assignCorrectAccountTypeValue(accountType);
            accessServices.getNewAccountPage().selectRecordType(accountType);
        } else {
            logger.info("Does not required to be executed Since Flag: " + executionFlag);
        }
    }

    @Then("^I Validate the product enrollment is displayed at Product Enrollments table")
    public void getProductEnrollmentsUsingTable() {
        if (executionFlag.trim().equalsIgnoreCase("") || executionFlag.isEmpty() || !executionFlag.trim().equalsIgnoreCase("N_A")) {
            commonData.productEnrollment = new ProductEnrollment(accessServices.getProductEnrollmentsTablePage().getProductEnrollmentsList().get(0));
        } else {
            logger.info("Does not required to be executed Since Flag: " + executionFlag);
        }
    }

    //======================================== USING SCENARIO OUTLINE
    @When("I click on new and I select {string} {string} {string}")
    public void iClickOnNewAndISelect(String accountType, String accountKeyJSON, String fileNameJSON) throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase("RND".trim()) || accountType.trim().equalsIgnoreCase("RND".trim())) {
                        accountType = accessServices.getNewAccountPage().randomSelectionJSONFile(accountKeyJSON, fileNameJSON);
                    }
                    String dropdownOption = accessServices.getNewAccountPage().assignCorrectAccountTypeValue(accountType);
                    accessServices.getNewAccountPage().selectRecordType(dropdownOption);
                } else {
                    logger.info("Does not required to be executed Since Flag Contains : " + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            String dropdownOption = accessServices.getNewAccountPage().assignCorrectAccountTypeValue(accountType);
            accessServices.getNewAccountPage().selectRecordType(dropdownOption);
        }
    }

    @And("I enter a valid {string} {string} {string} from Examples table to get an available product in the product enrollment form")
    public void fillMandatoryFieldsProgramEnrollmentOutLine(String consentType, String consentKeyJSON, String fileNameJSON) throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase("RND".trim()) || consentType.trim().equalsIgnoreCase("RND".trim())) {
                        consentType = accessServices.getNewAccountPage().randomSelectionJSONFile(consentKeyJSON, fileNameJSON);
                    }
                    commonData.consentType = new ConsentType(consentType);
                    Assert.assertTrue(accessServices.getCreateNewEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
                    product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(consentType);
                    commonData.product = new Product(product);
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            commonData.consentType = new ConsentType(consentType);
            Assert.assertTrue(accessServices.getCreateNewEnrollmentPage().isProductEnrollmentPageDisplayed(), "The product enrollment page was not displayed");
            product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(consentType);
            commonData.product = new Product(product);
        }
    }
    //========================================

    /**
     * Used to assign empty value to the executionFlag in case there is more than one execute of the script
     */
    @Then("delete the value of the executionFlag")
    public void deleteTheValueOfTheExecutionFlag() {
        executionFlag = "";
    }
}
