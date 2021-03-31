package stepDefinition.acessServices;

import base.functions.CommonFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.CommonData;
import stepDefinition.shareData.ConsentType;
import utils.JsonFiles;

import java.lang.reflect.InvocationTargetException;

public class CreateAConsent extends ApplicationInstance {
    private CommonData commonData;
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    private static String executionFlag = "";
    JsonFiles jsonFiles = new JsonFiles();


    public CreateAConsent(CommonData commonData) {
        this.commonData = commonData;
    }

    /**
     * Used to assign the value of searchFromFile to the executionFlag that will help to know if it is necessary to create a PE or used an specific one
     *
     * @param searchFromFile contains the value, that will be used as boolean, i.e. if it is come "", empty or with the value og "N_A" it will not create a product enrollment
     */
    @Given("{string} it selects which steps will be execute for consent purpose")
    public void selectStepsToExecuteConsentFeature(String searchFromFile) {
        executionFlag = searchFromFile;
    }


    @Given("^A external ID \"([^\"]*)\" I search a CPC at customer lookup$")
    public void searchCPCByID(String cpcID) throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    logger.info("Does not required to be executed Since Flag Contains : " + commonData.globalShareData.getExecutionFlag().trim());
                } else {
                    accessServices.getCustomerLookupPage().searchCPCByID(cpcID);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getCustomerLookupPage().searchCPCByID(cpcID);
        }
    }

    @Then("^I click on the external ID found for CPC at customer lookup$")
    public void selectAndClickAZID() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    logger.info("Does not required to be executed Since Flag Contains : " + commonData.globalShareData.getExecutionFlag().trim());
                } else {
                    accessServices.getCustomerLookupPage().selectAndClickAZID();
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getCustomerLookupPage().selectAndClickAZID();
        }
    }

    @Then("^I click on the consent tab to click the new consent button at person account page$")
    public void clickOnNewConsent() throws Exception {
        accessServices.getPersonAccountPage().clickOnNewConsent();
    }

    @And("I select the {string} consent type at new consent wizard page")
    public void selectConsentType(String consentTypeOption) throws Exception {
        try {
            if (commonData.globalShareData.getRandomSelectionFlag() != null) {
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase("RND".trim())) {
                    consentTypeOption = commonData.globalShareData.getRandomSelectionFlag().trim();
                }
                accessServices.getNewConsentPage().selectConsentType(consentTypeOption);
                commonData.consentType = new ConsentType(consentTypeOption);
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getNewConsentPage().selectConsentType(consentTypeOption);
            commonData.consentType = new ConsentType(consentTypeOption);
        }
    }

    @And("I fill the selected consent type form with the following data {string} {string} {string} {string} at new consent wizard page")
    public void fillConsentForm(String consentStatus, String consentDate, String consentSource, String consentAuth) throws Exception {
        try {
            if (commonData.globalShareData.getRandomSelectionFlag() != null) {
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase("RND".trim()) || consentStatus.trim().equalsIgnoreCase("RND".trim())) {
                    consentStatus = commonData.globalShareData.getRandomSelectionFlag().trim();
                }
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase("RND".trim()) || consentDate.trim().equalsIgnoreCase("RND".trim())) {
                    consentDate = commonData.globalShareData.getRandomSelectionFlag().trim();
                }
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase("RND".trim()) || consentSource.trim().equalsIgnoreCase("RND".trim())) {
                    consentSource = commonData.globalShareData.getRandomSelectionFlag().trim();
                }
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase("RND".trim()) || consentAuth.trim().equalsIgnoreCase("RND".trim())) {
                    consentAuth = commonData.globalShareData.getRandomSelectionFlag().trim();
                }
                accessServices.getNewConsentWizard().fillConsentForm(consentStatus, consentDate, consentSource, consentAuth);
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getNewConsentWizard().fillConsentForm(consentStatus, consentDate, consentSource, consentAuth);
        }
    }

    @Then("^I select the consent address in the new consent wizard page$")
    public void selectConsentAddress() throws Exception {
        accessServices.getNewConsentWizard().selectConsentAddress(true, 0);
    }

    @Then("I click on the product enrollment {string} from the person account page")
    public void clickProductEnrollmentAdded(String product) throws Exception {
        jsonFiles.setFileName("1372_EnrollmentProducts");
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    if (commonData.product.getProduct() != null || !commonData.product.getProduct().isEmpty()) {
                        product = commonData.product.getProduct().trim();
                    }
                    accessServices.getPersonAccountPage().switchToTab(0);
                    accessServices.getPersonAccountPage().clickOnProgramEnrollments();
                    accessServices.getPersonAccountPage().clickProductEnrollmentAdded(product);
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getPersonAccountPage().switchToTab(0);
            accessServices.getPersonAccountPage().clickOnProgramEnrollments();
            accessServices.getPersonAccountPage().clickProductEnrollmentAdded(product);
        }
    }

    @Then("I validate that no warning {string} {string} message is displayed related to the type lacking of a consent at the product enrollment page")
    public void validatePEDSIMessage(String consentTypeForm, String fileNameJSON) throws Exception {
        String messageJSONKey = "";
        String messagePE = "";
        jsonFiles.setFileName("ConstantData");
        boolean messageConsentType = false;
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    if (commonData.consentType.getConsentType() != null || !commonData.consentType.getConsentType().isEmpty()) {
                        messageJSONKey = commonData.consentType.getConsentType().trim().replaceAll("[(\\s)]", "");
                        messagePE = accessServices.getNewAccountPage().randomSelectionJSONFile(messageJSONKey, fileNameJSON);
                    }
                    accessServices.getProductEnrollmentPage().validatePEDSIMessage(messagePE);
                    Assert.assertEquals(messageConsentType, false, "There Is Still A Warning Message With The Following History " + messagePE);
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getProductEnrollmentPage().validatePEDSIMessage(messagePE);
            Assert.assertEquals(messageConsentType, false, "There Is Still A Warning Message With The Following History " + messagePE);
        }
    }

    @Then("^I validate the valid PAF \"([^\"]*)\" message at valid PAF column at accounts recently viewed page$")
    public void validateValidPAFValue(String validPAF) throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    if (commonData.consentType.getConsentType() != null || !commonData.consentType.getConsentType().isEmpty()) {
                        if (commonData.consentType.getConsentType().trim().contains("DSI")) {
                            validPAF = "DSI";
                        } else {
                            if (commonData.consentType.getConsentType().trim().contains("AstraZeneca")) {
                                validPAF = "AstraZeneca";
                            }
                        }
                    }
                    Assert.assertTrue(accessServices.getAccountsRecentlyViewedPage().validateValidPAFValue(validPAF), "The Valid PAF Column Message '" + validPAF + "' Matched");
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            Assert.assertTrue(accessServices.getAccountsRecentlyViewedPage().validateValidPAFValue(validPAF), "The Valid PAF Column Message '" + validPAF + "' Matched");
        }
    }

    //============CPC
    @Given("A dummyValue I enter the first name of the CPC as {string} with and account type {string} at CustomerLookup page for a Consent")
    public void doDummySearchCPC(String dummyValue, String accountType) throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    accessServices.getCustomerLookupPage().doDummySearch(dummyValue, accountType);
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getCustomerLookupPage().doDummySearch(dummyValue, accountType);
        }
    }

    @Then("I click the account created from AccountsPage")
    public void clickOnAccountCreated() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("") || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase("N_A")) {
                    accessServices.getAccountsPage().clickAccountCreated(commonData.patient.getPatientName());
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getAccountsPage().clickAccountCreated(commonData.patient.getPatientName());
        }
    }
    @And("I validate the consent ID is displayed")
    public void consentIDDisplayed() {
        String consentID = accessServices.getConsentPage().getConsentID();
    }

}
