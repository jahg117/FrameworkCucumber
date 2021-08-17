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
import utils.Values;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class CreateAConsent extends ApplicationInstance {
    private CommonData commonData;
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    private static String executionFlag = Values.REPLACETO_EMPTY;
    JsonFiles jsonFiles = new JsonFiles();
    CommonFunctions commonFunctions = new CommonFunctions();


    public CreateAConsent(CommonData commonData) throws Exception {
        this.commonData = commonData;
    }

    /**
     * Used to assign the value of searchFromFile to the executionFlag that will help to know if it is necessary to create a PE or used an specific one
     *
     * @param searchFromFile contains the value, that will be used as boolean, i.e. if it is come "", empty or with the value og "N_A" it will not create a product enrollment
     */
    @Given("{string} it selects which steps will be execute for consent purpose")
    public void selectStepsToExecuteConsentFeature(String searchFromFile) throws Exception {
        executionFlag = searchFromFile;
    }


    @Given("^A external ID \"([^\"]*)\" I search a CPC at customer lookup$")
    public void searchCPCByID(String cpcID) throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    logger.info(Values.TXT_MSGDOESNOTREQUIREDEXECUTE + commonData.globalShareData.getExecutionFlag().trim());
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
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    logger.info(Values.TXT_MSGDOESNOTREQUIREDEXECUTE + commonData.globalShareData.getExecutionFlag().trim());
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
        consentTypeOption = accessServices.getConsentPage().consentTypeFilter(consentTypeOption);
        try {
            if (commonData.globalShareData.getRandomSelectionFlag() != null) {
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
                    consentTypeOption = commonData.globalShareData.getRandomSelectionFlag().trim();
                }
            }
            accessServices.getNewConsentPage().selectConsentType(consentTypeOption);
            commonData.consentType = new ConsentType(consentTypeOption);
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getNewConsentPage().selectConsentType(consentTypeOption);
            commonData.consentType = new ConsentType(consentTypeOption);
        }
    }

    @And("I fill the selected consent type form with the following data {string} {string} {string} {string} at new consent wizard page")
    //REVIEW LOGIC
    public void fillConsentForm(String consentStatus, String consentDate, String consentSource, String consentAuth) throws Exception {
        try {
            if (commonData.globalShareData.getRandomSelectionFlag() != null) {
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim()) || consentStatus.trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
                    consentStatus = commonData.globalShareData.getRandomSelectionFlag().trim();
                }
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim()) || consentDate.trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
                    consentDate = commonData.globalShareData.getRandomSelectionFlag().trim();
                }
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim()) || consentSource.trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
                    consentSource = commonData.globalShareData.getRandomSelectionFlag().trim();
                }
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim()) || consentAuth.trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
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

    @Then("^I select the first consent address in the new consent wizard page$")
    public void selectFirstConsentAddress() throws Exception {
        accessServices.getNewConsentWizard().selectConsentAddress(false, 0);
    }

    @Then("^I select the second consent address in the new consent wizard page$")
    public void selectSecondConsentAddress() throws Exception {
        accessServices.getNewConsentWizard().selectConsentAddress(false, 1);
    }

    @Then("^I select the third consent address in the new consent wizard page$")
    public void selectThirdConsentAddress() throws Exception {
        accessServices.getNewConsentWizard().selectConsentAddress(false, 2);
    }

    @And("^I validate that the consent expiration date is correct$")
    public void validateConsentExpirationDateIsCorrect() throws Exception {
        Assert.assertEquals(accessServices.getConsentPage().getConsentDateValidation(), true, "The Consent expiration date is not matching according to the selected state");
        accessServices.getSubTabsPage().closeLastSubTab();
    }

    @Then("I click on the product enrollment {string} from the person account page")
    public void clickProductEnrollmentAdded(String product) throws Exception {
        jsonFiles.setFileName("1372_EnrollmentProducts");
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
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
        String messageJSONKey = Values.REPLACETO_EMPTY;
        String messagePE = Values.REPLACETO_EMPTY;
        jsonFiles.setFileName("ConstantData");
        boolean messageConsentType = false;
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    if (commonData.consentType.getConsentType() != null || !commonData.consentType.getConsentType().isEmpty()) {
                        messageJSONKey = commonData.consentType.getConsentType().trim().replaceAll(Values.REGEX_WITHESPACE, Values.REPLACETO_EMPTY);
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
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    if (commonData.consentType.getConsentType() != null || !commonData.consentType.getConsentType().isEmpty()) {
                        if (commonData.consentType.getConsentType().trim().contains(Values.TXT_DSI)) {
                            validPAF = Values.TXT_DSI;
                        } else {
                            if (commonData.consentType.getConsentType().trim().contains(Values.TXT_ASTRAZENECA)) {
                                validPAF = Values.TXT_ASTRAZENECA;
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
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
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
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    accessServices.getAccountsPage().clickAccountCreated(commonData.patient.getPatientName());
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getAccountsPage().clickAccountCreated(commonData.patient.getPatientName());
        }
    }

    @Then("I click the account created from AccountsPage PDC")
    public void clickOnAccountCreatedPDC() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    accessServices.getAccountsPage().clickAccountCreated(commonData.patient.getPatientNamePDC());
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getAccountsPage().clickAccountCreated(commonData.patient.getPatientNamePDC());
        }
    }


    @And("I validate the consent ID is displayed")
    public void consentIDDisplayed() throws Exception {
        accessServices.getConsentPage().getConsentID();
    }

    @And("I fill the selected consent type form with the following data {string} at new consent wizard page for PDC")
    public void fillConsentForm(String consentData) throws Exception {
        List<String> consentDataList = commonFunctions.splitRegex(consentData = consentData.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY), Values.REGEX_COMMA);
        try {
            if (commonData.globalShareData.getRandomSelectionFlag() != null) {
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim()) || consentDataList.get(0).trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
                    consentDataList.set(0, commonData.globalShareData.getRandomSelectionFlag().trim());
                }
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim()) || consentDataList.get(1).trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
                    consentDataList.set(1, commonData.globalShareData.getRandomSelectionFlag().trim());
                }
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim()) || consentDataList.get(2).trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
                    consentDataList.set(2, commonData.globalShareData.getRandomSelectionFlag().trim());
                }
                if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim()) || consentDataList.get(3).trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
                    consentDataList.set(3, commonData.globalShareData.getRandomSelectionFlag().trim());
                }
                accessServices.getNewConsentWizard().fillConsentForm(consentDataList.get(0), consentDataList.get(0), consentDataList.get(0), consentDataList.get(0));
            } else {
                accessServices.getNewConsentWizard().fillConsentForm(consentDataList.get(0), consentDataList.get(1), consentDataList.get(2), consentDataList.get(3));
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getNewConsentWizard().fillConsentForm(consentDataList.get(0), consentDataList.get(1), consentDataList.get(2), consentDataList.get(3));
        }
    }

    @Given("the {string} i create the consent type and validate it for {string}")
    public void createConsent(String consentData,String consent) throws Exception {
        String[] consentDataList = consentData.split(Values.REGEX_COMMA);
        if (consentDataList.length == 1 && consentDataList[0].equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            logger.info(Values.TXT_NOINSURANCE);
        } else {
            accessServices.getPersonAccountPage().clickOnNewConsent();
            String consentTypeOption = accessServices.getConsentPage().consentTypeFilter(consentDataList[0],consent);
            accessServices.getNewConsentPage().selectConsentType(consentTypeOption);
            commonData.consentType = new ConsentType(consentTypeOption);
            accessServices.getNewConsentWizard().createConsentData(consentData);
            accessServices.getNewConsentWizard().selectConsentAddress(true, 0);
            accessServices.getConsentPage().getConsentID();
            accessServices.getSubTabsPage().closeLastSubTab();
        }
    }
}
