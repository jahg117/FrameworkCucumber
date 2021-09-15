package stepDefinition.acessServices;


import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.internal.annotations.IAnnotationFinder;
import pageObject.ApplicationInstance;
import stepDefinition.shareData.*;
import utils.JsonFiles;
import utils.Values;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CreateProductEnrollment extends ApplicationInstance {
    private CommonData commonData;
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    private String product;
    private static String executionFlag = Values.REPLACETO_EMPTY;
    CommonFunctions commonFunctions = new CommonFunctions();

    public CreateProductEnrollment(CommonData commonData) throws Exception {
        this.commonData = commonData;
    }

    /**
     * Used to assign the value of searchFromFile to the executionFlag that will help to know if it is necessary to create a PE or used an specific one
     *
     * @param searchFromFile contains the value, that will be used as boolean, i.e. if it is come "", empty or with the value og "N_A" it will not create a product enrollment
     */
    @Given("{string} it selects which steps will be execute")
    public void selectStepsToExecute(String searchFromFile) throws Exception {
        executionFlag = searchFromFile;
    }


    @Given("^I click on new Account$")
    public void clickNewAccount() throws Exception {
        Faker faker = new Faker();
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_N_VALUE)
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    accessServices.getAccessServicesHomePage().isAccessServicesTitleVisible();
                    if (!commonData.userDetails.getUsername().equalsIgnoreCase(Values.TXT_ADMIN)) {
                        accessServices.getCustomerLookupPage().doDummySearch(faker.name().firstName(), Values.TXT_HCA);
                    }
                    accessServices.getCustomerLookupPage().clickNewAccount();
                } else {
                    logger.info(Values.TXT_MSGDOESNOTREQUIREDEXECUTE + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            accessServices.getAccessServicesHomePage().isAccessServicesTitleVisible();
            if (!commonData.userDetails.getUsername().equalsIgnoreCase(Values.TXT_ADMIN)) {
                accessServices.getCustomerLookupPage().doDummySearch(faker.name().firstName(), Values.TXT_HCA);
            }
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
        Assert.assertTrue(accessServices.getCreateNewEnrollmentPage().isProductEnrollmentPageDisplayed(), Values.TXT_PRODUCTNOTDISPLAYMESSAGE);
        String consentType = accessServices.getNewAccountPage().selectAccountTypeFromList(consentTypeList);
        product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(consentType);
    }
    //========================================

    @Then("^I fill the mandatory fields from the account form$")
    public void mandatoryFieldsAccountForm() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
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

    @Then("^I fill the fields from the account form$")
    public void fillFieldsAccountForm(DataTable dataTable) throws Exception {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        HashMap<String, String> patientTable = new HashMap<>();
        for (Map<String, String> el : list) {
            patientTable.put("name", el.get("name"));
            patientTable.put("fax", el.get("fax"));
            patientTable.put("phoneType", el.get("phoneType"));
            patientTable.put("zipcode", el.get("zipcode"));
        }
        accessServices.getNewPatientConsumerCaregiverPage().isConsumerPatientCaregiverFormDisplayed();
        HashMap<String, String> patientDetails = accessServices.getNewPatientConsumerCaregiverPage().fillPatientConsumerCaregiverForm(patientTable);
        accessServices.getNewPatientConsumerCaregiverPage().clickSaveButton();
        commonData.patient = new Patient(patientDetails);
    }

    @When("^I click on new and I select \"([^\"]*)\" account$")
    public void selectAccountType(String accountType) throws Exception {
        accessServices.getNewAccountPage().selectRecordType(accountType);
    }

    @And("^I click on new product enrollment button$")
    public void clickNewProductEnrollment() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
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
        Assert.assertTrue(accessServices.getCreateNewEnrollmentPage().isProductEnrollmentPageDisplayed(), Values.TXT_PRODUCTNOTDISPLAYMESSAGE);
        product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(productType);
        commonData.product = new Product(product);
    }

    @And("^I click on enroll button$")
    public void clickEnrollButton() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
                } else {
                    logger.info(Values.TXT_MSGDOESNOTREQUIREDEXECUTE + commonData.globalShareData.getExecutionFlag().trim());
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
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), Values.TXT_PRODUCTNOTDISPLAYMESSAGE);
                    String productEnrollment = accessServices.getPersonAccountPage().getProductEnrollmentNumber(product);
                    Assert.assertTrue(accessServices.getPersonAccountPage().isRedIconDisplayed(product), "The red icon is displayed");
                    String newProduct = accessServices.getPersonAccountPage().clickProductEnrollmentAdded(product);
                    Assert.assertTrue(product.equalsIgnoreCase(newProduct), "The product enrollment are matching");
                    Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), Values.TXT_PRODUCTNOTDISPLAYMESSAGE);
                    Assert.assertEquals(productEnrollment, accessServices.getProductEnrollmentPage().getProductEnrollmentNumber(), "The product enrollment number is not matching");
                } else {
                    logger.info(Values.TXT_MSGDOESNOTREQUIREDEXECUTE + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), Values.TXT_PRODUCTNOTDISPLAYMESSAGE);
            String productEnrollment = accessServices.getPersonAccountPage().getProductEnrollmentNumber(product);
            Assert.assertTrue(accessServices.getPersonAccountPage().isRedIconDisplayed(product), "The red icon is displayed");
            String newProduct = accessServices.getPersonAccountPage().clickProductEnrollmentAdded(product);
            Assert.assertTrue(product.equalsIgnoreCase(newProduct), "The product enrollment are matching");
            Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), Values.TXT_PRODUCTNOTDISPLAYMESSAGE);
            Assert.assertEquals(productEnrollment, accessServices.getProductEnrollmentPage().getProductEnrollmentNumber(), "The product enrollment number is not matching");
        }
    }

    @And("^I create a list of product enrollments with a care team member$")
    public void createProductEnrollmentFlow(DataTable dataTable) throws Exception {
        JsonFiles file = new JsonFiles();
        file.setFileName("CareTeamMember");
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> el : list) {
            String product = el.get("ProductEnrollment");
            accessServices.getPersonAccountPage().clickNewProductEnrollment();
            commonData.product = new Product(accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(product));
            accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
            if (accessServices.getProductEnrollmentPage().getProductEnrollmentNumber().equalsIgnoreCase(Values.REPLACETO_EMPTY)) {
                try {
                    accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
                } catch (Exception e) {
                }
            }
            accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
            for (int i = 0; i < file.getFieldArray("email").size(); i++) {
                String email = file.getFieldArray("email").get(i).toString();
                String type = file.getFieldArray("type").get(i).toString();
                String relation = file.getFieldArray("relationship").get(i).toString();
                accessServices.getProductEnrollmentPage().clickNewCareTeamMember();
                accessServices.getCustomerLookupPage().doDummySearch(email, type);
                accessServices.getCustomerLookupPage().selectCareTeamMemberAddressDetails();
                accessServices.getCustomerLookupPage().selectRelationshipOption(relation);
                accessServices.getCustomerLookupPage().selectCaseContactOption();
                accessServices.getCustomerLookupPage().clickCreateCareTeamMember();
                accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
            }
            accessServices.getSubTabsPage().closeSubTab(0);
        }
    }

    @And("^I enter a product enrollment in the product enrollment form$")
    public void createProductEnrollment(DataTable dataTable) throws Exception {
        int peCount = 0;
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
                    ArrayList<String> productEnrollments = new ArrayList<>();
                    for (Map<String, String> el : list) {
                        String product = el.get("ProductEnrollment");
                        accessServices.getPersonAccountPage().clickNewProductEnrollment();
                        commonData.product = new Product(accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(product));
                        accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
                        accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
                        productEnrollments.add(accessServices.getProductEnrollmentPage().getProductEnrollmentNumber());
                        accessServices.getSubTabsPage().closeLastSubTab();
                    }
                    accessServices.getPersonAccountPage().clickViewAllProgramEnrollments();
                    accessServices.getProductEnrollmentsTablePage().isProductEnrollmentsPageDisplayed();

                    for (String pe : productEnrollments) {
                        for (int i = 0; i <= productEnrollments.size() - 1; i++) {
                            if (pe.equalsIgnoreCase(productEnrollments.get(i))) {
                                logger.info(pe + " Matched: " + productEnrollments.get(i));
                                peCount = peCount + 1;
                                break;
                            }
                        }
                    }
                    Assert.assertEquals(peCount, productEnrollments.size(), "The list of product enrollments Matched");
                    //Assert.assertEquals(productEnrollments, accessServices.getProductEnrollmentsTablePage().getProductEnrollmentsList(), "The list of product enrollments is not matching");
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
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
            for (String pe : productEnrollments) {
                for (int i = 0; i <= productEnrollments.size() - 1; i++) {
                    if (pe.equalsIgnoreCase(productEnrollments.get(i))) {
                        logger.info(pe + " Matched: " + productEnrollments.get(i));
                        peCount = peCount + 1;
                        break;
                    }
                }
            }
            Assert.assertEquals(peCount, productEnrollments.size(), "The list of product enrollments Matched");
            //Assert.assertEquals(productEnrollments, accessServices.getProductEnrollmentsTablePage().getProductEnrollmentsList(), "The list of product enrollments is not matching");
        }
    }


    @And("^I validate the product enrollment is displayed")
    public void productEnrollmentDisplayed() throws Exception {
        Assert.assertTrue(accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed(), Values.TXT_PRODUCTNOTDISPLAYMESSAGE);
        commonData.productEnrollment = new ProductEnrollment(accessServices.getProductEnrollmentPage().getProductEnrollmentNumber());
    }

    @When("I click on new and I select from table the {string} account")
    public void selectFromTableAccountType(String accountType) throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    accountType = accessServices.getNewAccountPage().assignCorrectAccountTypeValue(accountType);
                    accessServices.getNewAccountPage().selectRecordType(accountType);
                } else {
                    logger.info("Does not required to be executed Since Flag: " + commonData.globalShareData.getExecutionFlag());
                }
            }

        } catch (InvocationTargetException | NullPointerException e) {
            accountType = accessServices.getNewAccountPage().assignCorrectAccountTypeValue(accountType);
            accessServices.getNewAccountPage().selectRecordType(accountType);
        }
    }

    @Then("^I Validate the product enrollment is displayed at Product Enrollments table")
    public void getProductEnrollmentsUsingTable() throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    commonData.productEnrollment = new ProductEnrollment(accessServices.getProductEnrollmentsTablePage().getProductEnrollmentsList().get(0));
                } else {
                    logger.info("Does not required to be executed Since Flag: " + commonData.globalShareData.getExecutionFlag());
                }

            }
        } catch (NullPointerException e) {
            commonData.productEnrollment = new ProductEnrollment(accessServices.getProductEnrollmentsTablePage().getProductEnrollmentsList().get(0));
        }
    }

    //======================================== USING SCENARIO OUTLINE
    @When("I click on new and I select {string} {string} {string}")
    public void iClickOnNewAndISelect(String accountType, String accountKeyJSON, String fileNameJSON) throws
            Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim()) || accountType.trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
                        accountType = accessServices.getNewAccountPage().randomSelectionJSONFile(accountKeyJSON, fileNameJSON);
                    }
                    String dropdownOption = accessServices.getNewAccountPage().assignCorrectAccountTypeValue(accountType);
                    accessServices.getNewAccountPage().selectRecordType(dropdownOption);
                } else {
                    logger.info(Values.TXT_MSGDOESNOTREQUIREDEXECUTE + commonData.globalShareData.getExecutionFlag().trim());
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            String dropdownOption = accessServices.getNewAccountPage().assignCorrectAccountTypeValue(accountType);
            accessServices.getNewAccountPage().selectRecordType(dropdownOption);
        }
    }

    @And("I enter a valid {string} {string} {string} from Examples table to get an available product in the product enrollment form")
    public void fillMandatoryFieldsProgramEnrollmentOutLine(String consentType, String consentKeyJSON, String
            fileNameJSON) throws Exception {
        try {
            if (commonData.globalShareData.getExecutionFlag() != null) {
                if (commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.REPLACETO_EMPTY) || commonData.globalShareData.getExecutionFlag().trim().isEmpty()
                        || !commonData.globalShareData.getExecutionFlag().trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                    if (commonData.globalShareData.getRandomSelectionFlag().trim().equalsIgnoreCase(Values.TXT_RANDOM.trim()) || consentType.trim().equalsIgnoreCase(Values.TXT_RANDOM.trim())) {
                        consentType = accessServices.getNewAccountPage().randomSelectionJSONFile(consentKeyJSON, fileNameJSON);
                    }
                    commonData.consentType = new ConsentType(consentType);
                    Assert.assertTrue(accessServices.getCreateNewEnrollmentPage().isProductEnrollmentPageDisplayed(), Values.TXT_PRODUCTNOTDISPLAYMESSAGE);
                    product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(consentType);
                    commonData.product = new Product(product);
                } else {
                    logger.info("Does not required to be executed Since Flag: " + executionFlag);
                }
            }
        } catch (InvocationTargetException | NullPointerException e) {
            commonData.consentType = new ConsentType(consentType);
            Assert.assertTrue(accessServices.getCreateNewEnrollmentPage().isProductEnrollmentPageDisplayed(), Values.TXT_PRODUCTNOTDISPLAYMESSAGE);
            product = accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(consentType);
            commonData.product = new Product(product);
        }
    }
    //========================================

    /**
     * Used to assign empty value to the executionFlag in case there is more than one execute of the script
     */
    @Then("delete the value of the executionFlag")
    public void deleteTheValueOfTheExecutionFlag() throws Exception {
        executionFlag = Values.REPLACETO_EMPTY;
    }

    //==================================================================PatientDataCreation.feature Steps Definition
    @And("I fill the fields from the account form PDC Using {string}")
    public void fillFieldsAccountFormPDC(String accData) throws Exception {
        accessServices.getNewPatientConsumerCaregiverPage().isConsumerPatientCaregiverFormDisplayed();
        HashMap<String, String> patientDetails = accessServices.getNewPatientConsumerCaregiverPage().fillPatientConsumerCaregiverFormPDC(accData);
        accessServices.getNewPatientConsumerCaregiverPage().clickSaveButton();
        commonData.patient = new Patient(patientDetails);
    }

    @And("I create product enrollments {string} with cases {string} {string} {string} {string} {string} {string} and care team members {string}")
    public void createProductEnrollmentFlowPDC(String productEnrollment, String cases, String channel, String caseStatus, String caseSubType, String discussTopic, String cardNumber, String ctmData) throws Exception {
        List<String> productList = accessServices.getProductEnrollmentPage().getProductEnrollmentList(productEnrollment);
        List<List<String>> casesList = accessServices.getPersonAccountPage().getCasesList(cases);
        String productName = "";
        int index = 0, indexy = 0;
        for (String product : productList) {
            productName = product;
            accessServices.getPersonAccountPage().clickNewProductEnrollment();
            accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(product);
            accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
            if (accessServices.getProductEnrollmentPage().getProductEnrollmentNumber().equalsIgnoreCase(Values.REPLACETO_EMPTY)) {
                try {
                    accessServices.getCreateNewEnrollmentPage().clickEnrollButton();

                } catch (Exception e) {
                }
            }
            accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
            commonData.productEnrollment = new ProductEnrollment(accessServices.getProductEnrollmentPage().getProductEnrollmentNumber());

            //////// cases
            for (int i = 0; i < casesList.get(index).size(); i++) {
                if (!casesList.get(index).get(indexy).equalsIgnoreCase("NA")) {
                    accessServices.getProductEnrollmentPage().clickOnNewCase();
                    accessServices.getNewCasePage().isNewCaseFormDisplayed();
                    accessServices.getNewCasePage().selectCaseOption(casesList.get(index).get(indexy));
                    accessServices.getNewCasePage().clickContinueButton();
                    ////////////
                    HashMap<String, String> caseForm = new HashMap<>();
                    caseForm.put("ProductName", productName);
                    caseForm.put("Channel", channel);
                    caseForm.put("CaseStatus", caseStatus);
                    caseForm.put("CaseSubType", caseSubType);
                    caseForm.put("DiscussTopic", discussTopic);
                    caseForm.put("CardNumber", cardNumber);
                    caseForm.put("CaseNumber", commonData.interaction.getInteractionNumber());
                    caseForm.put("ProductEnrollment", commonData.productEnrollment.getProductEnrollment());
                    accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
                    accessServices.getCaseInformationPage().fillCaseInformationForm(caseForm);
                    accessServices.getCaseInformationPage().clickSaveButton();
                    accessServices.getUpdateCaseContactWizardPage().closeCaseContactWizardPage();
                    accessServices.getCasePage().isCasePageDisplayed();
                    accessServices.getSubTabsPage().closeLastSubTab();
                }
                indexy++;
            }

            //////////care team member
            List<List<String>> ctmListOfList = commonFunctions.splitIntoLists(ctmData = ctmData.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY), Values.REGEX_COMMA, Values.TXT_UNDERSCORE);

            for (int i = 0; i < ctmListOfList.get(1).size(); i++) {
                String type = ctmListOfList.get(0).get(i);
                String email = ctmListOfList.get(1).get(i);
                String relation = ctmListOfList.get(2).get(i);
                accessServices.getProductEnrollmentPage().clickNewCareTeamMember();
                accessServices.getCustomerLookupPage().doDummySearch(email, type);
                accessServices.getCustomerLookupPage().selectCareTeamMemberAddressDetails();
                accessServices.getCustomerLookupPage().selectRelationshipOption(relation);
                accessServices.getCustomerLookupPage().selectCaseContactOption();
                accessServices.getCustomerLookupPage().clickCreateCareTeamMember();
                accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
            }
            accessServices.getSubTabsPage().closeSubTab(0);

            indexy = 0;
            index++;
            accessServices.getSubTabsPage().closeLastSubTab();
        }
    }

    @And("I create a list of product enrollments with a care team member Using for PDC")
    public void createProductEnrollmentFlowPDC(DataTable dataTable) throws Exception {
        JsonFiles file = new JsonFiles();
        file.setFileName("CareTeamMember");
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> el : list) {
            String product = el.get("ProductEnrollment");
            accessServices.getPersonAccountPage().clickNewProductEnrollment();
            commonData.product = new Product(accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(product));
            accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
            if (accessServices.getProductEnrollmentPage().getProductEnrollmentNumber().equalsIgnoreCase(Values.REPLACETO_EMPTY)) {
                try {
                    accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
                } catch (Exception e) {
                }
            }
            accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();

            String ctmData = commonData.globalShareData.getRecordData();
            List<List<String>> ctmListOfList = commonFunctions.splitIntoLists(ctmData = ctmData.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY), Values.REGEX_COMMA, Values.TXT_UNDERSCORE);

            for (int i = 0; i < ctmListOfList.get(1).size(); i++) {
                String type = ctmListOfList.get(0).get(i);
                String email = ctmListOfList.get(1).get(i);
                String relation = ctmListOfList.get(2).get(i);
                accessServices.getProductEnrollmentPage().clickNewCareTeamMember();
                accessServices.getCustomerLookupPage().doDummySearch(email, type);
                accessServices.getCustomerLookupPage().selectCareTeamMemberAddressDetails();
                accessServices.getCustomerLookupPage().selectRelationshipOption(relation);
                accessServices.getCustomerLookupPage().selectCaseContactOption();
                accessServices.getCustomerLookupPage().clickCreateCareTeamMember();
                accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
            }
            accessServices.getSubTabsPage().closeSubTab(0);
        }
    }

    @Then("I create the Care Team Member data using {string} for PDC")
    public void createCTMForPDC(String ctmData) {
        commonData.globalShareData = new GlobalShareData(ctmData);
        commonData.globalShareData.getRecordData();
    }

    @And("I create a list of product enrollments with {string} with a care team member Using {string}")
    public void createPEAndCTM(String drugs, String ctmData) throws Exception {
        List<String> drugList = commonFunctions.splitRegex(drugs, Values.REGEX_COMMA);
        if (!drugList.get(0).trim().startsWith(Values.IDX_VAL_P0 + Values.TXT_COLON)) {
            for (String drug : drugList) {
                if (!(drug = commonFunctions.searchIntoArray(drug, Values.ARRAY_DRUGLIST)).equalsIgnoreCase(Values.TXT_EMPTY)) {
                    if (drug.trim().startsWith(Values.IDX_VAL_P1 + Values.TXT_COLON)) {
                        drug = Values.ARRAY_DRUGLIST[commonFunctions.getRandomNumberByLimits(2, Values.ARRAY_DRUGLIST.length)];
                    }
                    accessServices.getPersonAccountPage().clickNewProductEnrollment();
                    commonData.product = new Product(accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(drug.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY)));
                    accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
                    if (accessServices.getProductEnrollmentPage().getProductEnrollmentNumber().equalsIgnoreCase(Values.REPLACETO_EMPTY)) {
                        try {
                            accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
                        } catch (Exception e) {
                        }
                    }
                    accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
                    List<List<String>> ctmListOfList = commonFunctions.splitIntoLists(ctmData = ctmData.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY), Values.REGEX_COMMA, Values.TXT_UNDERSCORE);
                    if (ctmListOfList.size() == 1 && (ctmListOfList.get(0).get(0).equalsIgnoreCase(Values.TXT_NOTAPPLY) || ctmListOfList.get(0).get(0).equalsIgnoreCase(Values.TXT_N_VALUE))) {
                        logger.info(Values.TXT_NOINSURANCE);
                    } else {
                        for (int i = 0; i < ctmListOfList.get(1).size(); i++) {
                            String type = ctmListOfList.get(0).get(i);
                            String email = ctmListOfList.get(1).get(i);
                            String relation = ctmListOfList.get(2).get(i);
                            accessServices.getProductEnrollmentPage().clickNewCareTeamMember();
                            accessServices.getCustomerLookupPage().doDummySearch(email, type);
                            accessServices.getCustomerLookupPage().selectCareTeamMemberAddressDetails();
                            accessServices.getCustomerLookupPage().selectRelationshipOption(relation);
                            accessServices.getCustomerLookupPage().selectCaseContactOption();
                            accessServices.getCustomerLookupPage().clickCreateCareTeamMember();
                            accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
                        }
                    }
                    accessServices.getSubTabsPage().closeSubTab(0);
                } else {
                    logger.info(Values.TXT_NOPECREATED + drug);
                }
            }
        } else {
            logger.info(Values.TXT_NOPENEEDIT);
        }


    }


    @And("I create a list of product enrollments with {string} {string} {string} {string}")
    public void createPEAndCTM(String drugs, String ctmData, String cases, String caseData) throws Exception {
        List<String> drugList = commonFunctions.splitRegex(drugs, Values.REGEX_COMMA);
        List<List<String>> casesList = accessServices.getPersonAccountPage().getCasesList(cases);
        List<String> caseDataList;
        String currentDrug = "";
        String drugName = "";
        String caseTypeOption = "";
        HashMap<String, String> caseForm = new HashMap<>();
        int index = 0, indexy = 0;
//=======NONE PE WILL BE CREATED
        if (drugList.size() >= 1 && ((!drugList.get(0).equalsIgnoreCase(Values.TXT_NOTAPPLY)) && !drugList.get(0).equalsIgnoreCase(Values.IDX_VAL_P0))) {

//=========================================================================================PE CREATION LOGIC
            for (String drug : drugList) {
                currentDrug = drug;
                if (!drug.trim().startsWith(Values.IDX_VAL_P0)) {
                    if (!(drug = commonFunctions.searchIntoArray(drug, Values.ARRAY_DRUGLIST)).equalsIgnoreCase(Values.TXT_EMPTY)) {
                        if (drug.trim().startsWith(Values.IDX_VAL_P1 + Values.TXT_COLON) || drug.trim().equalsIgnoreCase(Values.TXT_RANDOM)) {
                            drug = Values.ARRAY_DRUGLIST[commonFunctions.getRandomNumberByLimits(3, Values.ARRAY_DRUGLIST.length)];
                        }
                        accessServices.getPersonAccountPage().clickNewProductEnrollment();
                        commonData.product = new Product(accessServices.getCreateNewEnrollmentPage().fillProductEnrollmentForm(drugName = drug.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY)));
                        accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
                        if (accessServices.getProductEnrollmentPage().getProductEnrollmentNumber().equalsIgnoreCase(Values.REPLACETO_EMPTY)) {
                            try {
                                accessServices.getCreateNewEnrollmentPage().clickEnrollButton();
                            } catch (Exception e) {
                            }
                        }
                        accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
                        commonData.productEnrollment = new ProductEnrollment(accessServices.getProductEnrollmentPage().getProductEnrollmentNumber());

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
                                accessServices.getProductEnrollmentPage().clickNewCareTeamMember();
                                accessServices.getCustomerLookupPage().doDummySearch(email, type);
                                accessServices.getCustomerLookupPage().selectCareTeamMemberAddressDetails();
                                accessServices.getCustomerLookupPage().selectRelationshipOption(relation);
                                accessServices.getCustomerLookupPage().selectCaseContactOption();
                                accessServices.getCustomerLookupPage().clickCreateCareTeamMember();
                                accessServices.getProductEnrollmentPage().isProductEnrollmentPageDisplayed();
                            }
                        }

//===========================================================================================CASE CREATION FROM PE LOGIC
                        if (casesList.size() == 1 && casesList.get(0).get(0).trim().equalsIgnoreCase(Values.TXT_N_VALUE)) {
                            logger.info(Values.TXT_NOCASENEEDIT);
                        } else {
                            for (int i = 0; i < casesList.get(index).size(); i++) {
                                if (!casesList.get(index).get(indexy).equalsIgnoreCase(Values.IDX_VAL_P0)) {
                                    caseDataList = Arrays.asList(caseData.trim().split(Values.REGEX_COMMA));
                                    accessServices.getProductEnrollmentPage().clickOnNewCase();
                                    accessServices.getNewCasePage().isNewCaseFormDisplayed();
                                    if ((caseTypeOption = commonFunctions.searchIntoArray(casesList.get(index).get(indexy), Values.ARRAY_CASETYPELIST)).equalsIgnoreCase(Values.TXT_EMPTY)) {
                                        logger.info(Values.TXT_NOCASETYPEFOUND);
                                    } else {
                                        if (casesList.get(index).get(indexy).trim().startsWith(Values.IDX_VAL_P1 + Values.TXT_COLON)) {
                                            accessServices.getNewCasePage().selectCaseOption(commonFunctions.searchIntoArray(Values.IDX_VAL_P1, Values.ARRAY_CASETYPELIST).replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY));
                                        } else {
                                            accessServices.getNewCasePage().selectCaseOption(caseTypeOption.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY));
                                        }
                                        accessServices.getNewCasePage().clickContinueButton();
                                        caseForm.put("ProductName", drugName);
                                        caseForm.put("Channel", caseDataList.get(0));
                                        caseForm.put("CaseStatus", caseDataList.get(1));
                                        caseForm.put("CaseSubType", caseDataList.get(2));
                                        caseForm.put("DiscussTopic", caseDataList.get(3));
                                        caseForm.put("CardNumber", caseDataList.get(4));
                                        caseForm.put("CaseNumber", commonData.interaction.getInteractionNumber());
                                        caseForm.put("ProductEnrollment", commonData.productEnrollment.getProductEnrollment());
                                        accessServices.getCaseInformationPage().isCaseOptionPageDisplayed();
                                        accessServices.getCaseInformationPage().fillCaseInformationForm(caseForm);
                                        accessServices.getCaseInformationPage().clickSaveButton();
                                        accessServices.getUpdateCaseContactWizardPage().closeCaseContactWizardPage();
                                        accessServices.getCasePage().isCasePageDisplayed();
                                        accessServices.getSubTabsPage().closeLastSubTab();
                                    }
                                } else {
                                    accessServices.getSubTabsPage().closeSubTab(0);//closing PE Tab
                                }
                                indexy++;
                            }
                            index++;
                        }
                    }
                }
                //NO PE FOR THIS DRUG WILL BE CREATED
                else {
                    logger.info(Values.TXT_NOPENEEDIT + currentDrug);
                }
                indexy = 0;
                accessServices.getSubTabsPage().closeLastSubTab();

            }
        }
        //NONE PROGRAM ENROLLMENT NEED IT
        else {
            logger.info(Values.TXT_NOPENEEDIT);
        }
    }
}
