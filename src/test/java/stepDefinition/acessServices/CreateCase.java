package stepDefinition.acessServices;

import base.functions.CommonFunctions;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.testng.Assert;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.*;
import utils.JsonFiles;
import utils.Values;

import java.util.HashMap;
import java.util.List;

public class CreateCase extends ApplicationInstance {

    private CommonData commonData;
    CommonFunctions commonFunctions = new CommonFunctions();
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    public CreateCase(CommonData commonData) {
        this.commonData = commonData;
    }

    @And("^I click on new Case from the product enrollment page")
    public void clickNewCase() throws Exception {
        accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
        accessServices.getProductEnrollmentPage().clickOnNewCase();
    }

    @And("^I click on new Case from the person account page")
    public void clickNewCasePersonAccount() throws Exception {
        accessServices.getPersonAccountPage().clickNewCase();
    }

    @And("^I click on new Case from the person account tab")
    public void clickNewCasePersonAccountTab() throws Exception {
        accessServices.getPersonAccountPage().clickNewCasePersonalAccountPage();
    }

    @And("^I click on new Case from the cases list page")
    public void clickNewCaseListCases() throws Exception {
        Assert.assertTrue(accessServices.getCasesListPage().isCasesListPageDisplayed(), "The Cases List Page was not displayed");
        accessServices.getCasesListPage().clickNewCase();
    }

    @And("^I select the case type option \"([^\"]*)\" from the child case form$")
    public void selectChildCaseTypeOptions(String caseOption) throws Exception {
        accessServices.getNewChildCasePage().isChildCaseFormDisplayed();
        accessServices.getNewChildCasePage().selectCaseOption(caseOption);
    }

    @And("^I select the case type option \"([^\"]*)\"$")
    public void selectCaseTypeOptionsPage(String caseOption) throws Exception {
        Assert.assertTrue(accessServices.getNewCaseOptionsPage().isFormCaseOptionsPageDisplayed(), "The case form option was not displayed");
        accessServices.getNewCaseOptionsPage().selectCaseOption(caseOption);
    }

    @And("^I select the case type \"([^\"]*)\"$")
    public void selectCaseType(String caseOption) throws Exception {
        accessServices.getNewCasePage().isNewCaseFormDisplayed();
        accessServices.getNewCasePage().selectCaseOption(caseOption);
        accessServices.getNewCasePage().clickContinueButton();
    }

    @And("^I fill the patient and product enrollment fields$")
    public void fillPatientProductEnrollment() throws Exception {
        accessServices.getCaseInformationPage().fillPatientProductEnrollmentFields(commonData.patient.getPatientName(), commonData.productEnrollment.getProductEnrollment());
    }

    @And("^I fill the new anonymous case fields \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void fillNewCaseAnonymousFields(String productName, String caseRequested, String channel, String caseStatus, String caseSubType, String discussTopic, String cardNumber) throws Exception {
        HashMap<String, String> caseForm = new HashMap<>();
        try {
            if (commonData.product.getProduct() != null) {
                productName = commonData.product.getProduct();
            }
        } catch (Exception e) {
            if (productName.equalsIgnoreCase(Values.TXT_AZ)
                    || productName.equalsIgnoreCase(Values.TXT_DSI)) {
                JsonFiles file = new JsonFiles();
                file.setFileName(Values.TXT_1372FILENAME);
                productName = file.getRandomFieldArray(productName);
            }
        }
        caseForm.put("ProductName", productName);
        caseForm.put("CaseRequested", caseRequested);
        caseForm.put("Channel", channel);
        caseForm.put("CaseStatus", caseStatus);
        caseForm.put("CaseSubType", caseSubType);
        caseForm.put("DiscussTopic", discussTopic);
        caseForm.put("CardNumber", cardNumber);
        caseForm.put("CaseNumber", commonData.interaction.getInteractionNumber());
        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
        HashMap<String, String> caseFormInformation = accessServices.getCaseInformationPage().fillAnonymousCaseInformationForm(caseForm);
        accessServices.getCaseInformationPage().clickSaveButton();
        accessServices.getUpdateCaseContactWizardPage().closeCaseContactWizardPage();
        commonData.caseForm = new Case(caseFormInformation);
        commonData.product = new Product(productName);
    }

    @And("^I fill the new case mandatory fields \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void fillNewCaseMandatoryFields(String caseName, String productName, String channel, String caseStatus, String caseSubType, String discussTopic, String cardNumber) throws Exception {
        HashMap<String, String> caseForm = new HashMap<>();
        if (commonData.product.getProduct() != null) {
            productName = commonData.product.getProduct();
        }
        caseForm.put("ProductName", productName);
        caseForm.put("Channel", channel);
        caseForm.put("CaseStatus", caseStatus);
        caseForm.put("CaseSubType", caseSubType);
        caseForm.put("DiscussTopic", discussTopic);
        caseForm.put("CardNumber", cardNumber);
        caseForm.put("CaseNumber", commonData.interaction.getInteractionNumber());
        caseForm.put("ProductEnrollment", commonData.productEnrollment.getProductEnrollment());
        caseForm.put("CaseName", caseName);
        caseForm.put("PatientName", commonData.patient.getPatientName());
        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
        HashMap<String, String> caseFormInformation = accessServices.getCaseInformationPage().fillCaseInformationForm(caseForm);
        accessServices.getCaseInformationPage().clickSaveButton();
        accessServices.getUpdateCaseContactWizardPage().closeCaseContactWizardPage();
        commonData.caseForm = new Case(caseFormInformation);
        commonData.product = new Product(productName);
    }

    @And("^I fill the child case mandatory fields \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void fillChildCaseMandatoryFields(String productType, String channel, String caseStatus, String caseSubType, String discussTopic, String cardNumber) throws Exception {
        HashMap<String, String> caseForm = new HashMap<>();
        caseForm.put("Channel", channel);
        caseForm.put("CaseStatus", caseStatus);
        caseForm.put("CaseSubType", caseSubType);
        caseForm.put("DiscussTopic", discussTopic);
        caseForm.put("CardNumber", cardNumber);
        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
        String getProduct = accessServices.getNewProductEnrollmentForm().getProduct(productType);
        accessServices.getCaseInformationPage().fillPatientProductEnrollmentFields(commonData.patient.getPatientName(), commonData.productEnrollment.getProductEnrollment());
        accessServices.getCaseInformationPage().fillSearchProduct(getProduct);
        HashMap<String, String> caseFormInformation = accessServices.getCaseInformationPage().fillCaseInformationForm(caseForm);
        accessServices.getCaseInformationPage().clickSaveButton();
        accessServices.getUpdateCaseContactWizardPage().closeCaseContactWizardPage();
        commonData.caseForm = new Case(caseFormInformation);
        commonData.product = new Product(getProduct);
    }

    @And("^I fill the new interaction mandatory fields \"([^\"]*)\" \"([^\"]*)\"$")
    public void fillNewInteractionMandatoryFields(String channel, String caseStatus) throws Exception {
        HashMap<String, String> interaction = new HashMap<>();
        interaction.put("Channel", channel);
        interaction.put("CaseStatus", caseStatus);
        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
        HashMap<String, String> interactionForm = accessServices.getCaseInformationPage().fillCaseInteractionForm(interaction);
        accessServices.getCaseInformationPage().clickSaveInteraction();
        commonData.interaction = new Interaction(interactionForm);
    }

    @And("^I click on the child case button$")
    public void clickChildCase() throws Exception {
        accessServices.getCasePage().isCasePageDisplayed();
        accessServices.getCasePage().clickChildCaseButton();
    }

    @And("^I validate the correct case interaction information displayed$")
    public void validateCaseInteractionInformation() throws Exception {
        accessServices.getCasePage().isCasePageDisplayed();
        if (!accessServices.getCasePage().getPatientName().equalsIgnoreCase(Values.REPLACETO_EMPTY)) {
            Assert.assertEquals(accessServices.getCasePage().getPatientName(), commonData.patient.getPatientName(), "The patient name is not matching");
        }
        Assert.assertEquals(accessServices.getCasePage().getCaseStatus(), commonData.interaction.getCaseStatus(), "The case status is not matching");
        Assert.assertEquals(accessServices.getCasePage().getChannel(), commonData.interaction.getChannel(), "The channel is not matching");
    }

    @And("^I validate the interaction case number is displayed$")
    public void getCaseInteractionNumber() throws Exception {
        accessServices.getCasePage().isCasePageDisplayed();
        String interactionNumber = accessServices.getCasePage().getCaseNumber();
        commonData.interaction = new Interaction(interactionNumber);
    }

    @And("^I validate the correct case information is displayed$")
    public void validateCaseInformation() throws Exception {
        accessServices.getCasePage().isCasePageDisplayed();
        Assert.assertEquals(accessServices.getCasePage().getEnrolledPatient(), commonData.patient.getPatientName(), "The enrolled patient is not matching");
        Assert.assertEquals(accessServices.getCasePage().getCaseStatus(), commonData.caseForm.getCaseStatus(), "The case status is not matching");
        Assert.assertEquals(accessServices.getCasePage().getProductEnrollment(), commonData.productEnrollment.getProductEnrollment(), "The product enrollment is not matching");
        Assert.assertEquals(accessServices.getCasePage().getChannel(), commonData.caseForm.getChannel(), "The channel is not matching");
    }

    @And("^I create a/an \"([^\"]*)\" case$")
    public void createCaseType(String caseOption) throws Exception {
        accessServices.getPersonAccountPage().clickNewProductEnrollment();
        String product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm("fasenra");
        accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
        accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
        String productEnrollment = accessServices.getProductEnrollmentPage().getProductEnrollmentNumber();
        accessServices.getProductEnrollmentPage().clickOnNewCase();
        accessServices.getNewCasePage().isNewCaseFormDisplayed();
        accessServices.getNewCasePage().selectCaseOption(caseOption);
        accessServices.getNewCasePage().clickContinueButton();
        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
        accessServices.getCaseInformationPage().fillCaseInformationForm();
        accessServices.getCaseInformationPage().clickSaveButton();
        accessServices.getUpdateCaseContactWizardPage().closeCaseContactWizardPage();
    }

    @And("I fill the new interaction mandatory fields {string} for PDC")
    public void fillNewInteractionMandatoryFieldsPDC(String irData) throws Exception {
        List<String> irDataList = commonFunctions.splitRegex(irData = irData.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY), Values.REGEX_COMMA);
        HashMap<String, String> interaction = new HashMap<>();
        interaction.put("Channel", irDataList.get(0));
        interaction.put("CaseStatus", irDataList.get(1));
        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
        HashMap<String, String> interactionForm = accessServices.getCaseInformationPage().fillCaseInteractionForm(interaction);
        accessServices.getCaseInformationPage().clickSaveInteraction();
        commonData.interaction = new Interaction(interactionForm);
    }

    @And("I create a new {string} case with {string}")
    public void createInteractionCaseSPP(String caseType, String caseData) throws Exception {
        List<String> caseTypeList = commonFunctions.splitRegex(caseType, Values.REGEX_COMMA);
        if (caseTypeList.size() == 1 && caseTypeList.get(0).equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            logger.info(Values.TXT_NOINSURANCE);
        } else {
            accessServices.getPersonAccountPage().clickNewCase();
            Assert.assertTrue(accessServices.getNewCaseOptionsPage().isFormCaseOptionsPageDisplayed(), Values.TXT_NOINTERACTIONFORM);
            if (caseType.trim().equalsIgnoreCase(Values.IDX_VAL_P2)) {
                caseType = Values.ARRAY_CASETYPELIST[Integer.parseInt(Values.IDX_VAL_P2.replace(Values.TXT_VALUE_P, Values.REPLACETO_EMPTY))];
            }
            accessServices.getNewCaseOptionsPage().selectCaseOption(caseType);
            List<String> irDataList = commonFunctions.splitRegex(caseData = caseData.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY), Values.REGEX_COMMA);
            HashMap<String, String> interaction = new HashMap<>();
            interaction.put("Channel", irDataList.get(0));
            interaction.put("CaseStatus", irDataList.get(1));
            accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
            HashMap<String, String> interactionForm = accessServices.getCaseInformationPage().fillCaseInteractionForm(interaction);
            accessServices.getCaseInformationPage().clickSaveInteraction();
            commonData.interaction = new Interaction(interactionForm);
            accessServices.getCasePage().isCasePageDisplayed();
            String interactionNumber = accessServices.getCasePage().getCaseNumber();
            commonData.interaction = new Interaction(interactionNumber);
            accessServices.getSubTabsPage().closeLastSubTab();
        }
    }

    @Then("I open the RV case")
    public void iOpenTheRVCase() throws Exception {
        String rvCaseNumber = accessServices.getCasePage().getRVCaseNumber();
        accessServices.getCasePage().searchCaseOnSF(rvCaseNumber);

    }

    @And("I add a new case team member {string} and validate it")
    public void iAddANewCaseTeam(String ctmData) throws Exception {
        //====================================================================================================CTM CREATION LOGIC
//===================CTM WILL BE CREATED ONLY IF A PE IS CREATED
        List<List<String>> ctmListOfList = commonFunctions.splitIntoLists(ctmData = ctmData.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY), Values.REGEX_COMMA, Values.TXT_UNDERSCORE);
        if (ctmListOfList.size() == 1 && (ctmListOfList.get(0).get(0).equalsIgnoreCase(Values.TXT_NOTAPPLY) || ctmListOfList.get(0).get(0).equalsIgnoreCase(Values.TXT_N_VALUE))) {
            logger.info(Values.TXT_NOINSURANCE);
        } else {
            for (int i = 0; i < ctmListOfList.get(1).size(); i++) {
                String type = ctmListOfList.get(0).get(i);
                String email = ctmListOfList.get(1).get(i);
                String relation = ctmListOfList.get(2).get(i);
                accessServices.getCasePage().clickOnAddCaseTeam();
                accessServices.getCustomerLookupPage().doDummySearch(email, type);
                accessServices.getCustomerLookupPage().selectCareTeamMemberAddressDetails();
                String memberRoleCaseTeam = accessServices.getCustomerLookupPage().selectRelationshipOption(relation);
                accessServices.getCustomerLookupPage().selectCaseContactOption();
                accessServices.getCustomerLookupPage().clickCreateCareTeamMember();
                Assert.assertEquals(accessServices.getCasePage().validateAddedCaseTeamRV(memberRoleCaseTeam), true, Values.TXT_CASETEAMADDED + memberRoleCaseTeam);
            }
        }
    }

    @Then("I validate that the patients selected are displayed on the patient section")
    public void iValidatePatientsSelectedOnPatientSection() throws Exception {
        Assert.assertEquals(accessServices.getCasePage().validatePEPID(Values.globalStringList),true, Values.TXT_CASETEAMADDED);
    }

    @Given("a Next treatment date {string} i will update the date in the patient section")
    public void aNextTreatmentDateUpdateInThePatientSection(String nextTreatmentDate) throws Exception {
        Values.globalNTDValidationList.addAll(accessServices.getCasePage().editNextTreatmentDate(nextTreatmentDate, Values.globalStringList));
    }

    @Then("I click on create RV case without selecting any of the patients to validate warning message No patients selected")
    public void iValidateCreateRVCasesMessage() throws Exception {
        Assert.assertEquals(accessServices.getCasePage().validateCreateRVCasesMessage(),true,Values.TXT_NOPATIENTSSELECTED);
    }

    @Then("I select the checkbox for the patients click Create RV case and validate message RV created")
    public void iSelectThePatientsAndValidateMessageRVCreated() throws Exception {
        Assert.assertEquals(accessServices.getCasePage().selectAndCreateRVCases(),true,Values.TXT_SUCCESSFULLCREATIONRVCASES);
    }
}
