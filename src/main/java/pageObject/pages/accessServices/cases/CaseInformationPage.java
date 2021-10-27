package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;
import java.util.HashMap;
import java.util.List;

public class CaseInformationPage extends CommonFunctions {
    @FindBy(xpath = "//button[@title='Save']")
    private WebElement buttonSave;

    @FindBy(xpath = "(//div[contains(@class,'forceDetailPanelDesktop')])[last()]//*[contains(text(),'New Case')]")
    private WebElement formCaseOptions;

    @FindBy(xpath = "//span[contains(text(),'Case Requested By')]/following::*[@class='select'][1]")
    private WebElement dropdownCaseRequestedBy;

    @FindBy(xpath = "//span[contains(text(),'Case Information')]/following::*[@class='select'][1]")
    private WebElement dropdownChannel;

    @FindBy(xpath = "//*[contains(@class,'uiMenuList--short visible')]//a")
    private List<WebElement> listDropdownOptions;

    @FindBy(xpath = "//span[contains(text(),'Case Status')]/../..//a[@class='select'][1]")
    private WebElement dropdownCaseStatus;

    @FindBy(xpath = "(//span[contains(text(),'Case Sub-Type')]/following::*[@class='select'][1])[last()]")
    private WebElement dropdownSubType;

    @FindBy(xpath = "//span[contains(text(),'IA Formulation Type')]/../..//a[@class='select'][1]")
    private WebElement dropdownIAFormulationType;

    @FindBy(xpath = "//div[contains(text(),'Discussion Topic')]/..//span[@class='slds-truncate']")
    private List<WebElement> listDiscussTopic;

    @FindBy(xpath = "//div[contains(text(),'Discussion Topic')]/..//div[contains(@class,'dueling-list')]//button[@title='Move selection to Chosen']")
    private WebElement buttonIconRightFlagDiscussionTopic;

    @FindBy(xpath = "//ul[@class='lookup__list  visible']")
    private WebElement listAutocomplete;

    @FindBy(xpath = "//input[@title='Search Product Enrollments']")
    private WebElement inputSearchProductEnrollments;

    @FindBy(xpath = "//*[contains(text(),'Card Number')]/../..//input")
    private WebElement inputCardNumber;

    @FindBy(xpath = "//span[contains(@class,'genericError')]")
    private WebElement labelGenericError;

    @FindBy(xpath = "//*[contains(text(),'Product')]/following::*[./text()=\"Product\"]/../..//span[@class='deleteIcon']")
    private WebElement buttonDeleteProduct;

    @FindBy(xpath = "//input[@title='Search Products']")
    private WebElement inputSearchProducts;

    @FindBy(xpath = "//input[@title='Search Cases']")
    private WebElement inputInteractionCase;

    @FindBy(xpath = "//*[contains(text(),'Enrolled Patient')]/following::*//input[@title='Search Accounts']")
    private WebElement inputSearchAccounts;

    private By listAutocompleteElements = By.xpath("//div[contains(@class,'lookup__menu uiAbstractList')]//li[contains(@class,'default uiAutocompleteOption')]//div[contains(@class,'primaryLabel')]");

    private By buttonCreateNewElementList = By.xpath("//div[contains(@class,'lookup__menu uiAbstractList')]//div[contains(@class,'createNew')]//span");

    @FindBy(xpath = "//span[contains(text(),'Product Enrollment')]/../..//span[contains(text(),'PE-')]")
    private WebElement labelProductEnrollment;

    @FindBy(xpath = "//input[@title='Search Patient Insurances']")
    private WebElement inputSearchPatientInsurance;

    private By buttonNewPatientInsurance = By.xpath("//input[@title='Search Patient Insurances']/..//div[contains(@class,'createNew')]");

    @FindBy(xpath = "//div[contains(@class,'changeRecordTypeRightColumn')]//span[contains(text(),'Patient Medical Insurance')]/../..//div[contains(@class,'Left')]")
    private WebElement radioPatientMedicalInsurance;

    @FindBy(xpath = "//div[contains(@class,'modal-footer')]//*[contains(text(),'Next')]")
    private WebElement buttonNextPatientInsurance;

    @FindBy(xpath = "//div[contains(@class,'modal-container')]//span[contains(text(),'Patient')]/../..//input[@title='Search Accounts']")
    private WebElement inputSearchAccount;

    @FindBy(xpath = "//span[contains(text(),'Insurance Rank')]/following::*[@class='select'][1]")
    private WebElement dropdownInsuranceRank;

    @FindBy(xpath = "//div[contains(@class,'modal-container')]//span[contains(text(),'Insurance Payer')]/../..//input[@title='Search Accounts']")
    private WebElement inputSearchInsurancePayerName;

    @FindBy(xpath = "//div[contains(@class,'modal-footer')]//button[@title='Save']")
    private WebElement buttonSavePatientInsurance;

    @FindBy(xpath = "//span[contains(text(),'Patient Insurance')]/../..//a[contains(@class,'pill')]")
    private WebElement labelPatientInsuranceResult;

    @FindBy(xpath = "//*[contains(@class,'inlineTitle')]")
    private WebElement labelCaseTitle;

    protected FileReading fileReading = new FileReading();
    private final Logger logger = Logger.getLogger(CommonFunctions.class);
    public static int maxNumberOfTries = 0;

    Class<?> myClass;

    {
        try {
            fileReading.setLog4jFile();
            fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
            maxNumberOfTries = Integer.parseInt(fileReading.getField(Values.TXT_RETRYWHILE));
            myClass = Class.forName(Values.REFLECTION_COMMONFUNCTIONSCLASSPATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getProductEnrollment() throws Exception {
        String statusOperation = "";
        try {
            waitForElementVisibility(labelProductEnrollment, mediumWait());
            statusOperation = getWebElementText(labelProductEnrollment);
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    public boolean fillCaseInformationForm() throws Exception {
        boolean statusOperation = false;
        try {
            selectRandomDropdownOption(dropdownChannel, listDropdownOptions);
            selectSpecificDropdownOption(dropdownCaseStatus, listDropdownOptions, "Open");
            selectRandomDropdownOption(dropdownSubType, listDropdownOptions);
            if (waitForElementListVisible(listDiscussTopic, shortWait())) {
                clickAndMoveToElementClickable(getRandomWebElementFromList(listDiscussTopic, mediumWait()), mediumWait());
                clickAndMoveToElementClickable(buttonIconRightFlagDiscussionTopic, mediumWait());
            }
            if (waitForElementVisibility(inputCardNumber, shortWait())) {
                sendKeysAndMoveToElementVisible(inputCardNumber, getRandomNumber(), shortWait());
            }
            statusOperation = true;
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    public HashMap<String, String> fillCaseInteractionForm(HashMap<String, String> interactionForm) throws Exception {
        HashMap<String, String> statusOperation = new HashMap<>();
        String webElementOption;
        webElementOption = selectDropdownOption(dropdownCaseStatus, listDropdownOptions, interactionForm.get("CaseStatus"));
        statusOperation.put("CaseStatus", webElementOption);
        webElementOption = selectDropdownOption(dropdownChannel, listDropdownOptions, interactionForm.get("Channel"));
        statusOperation.put("Channel", webElementOption);
        return statusOperation;
    }


    public void fillSearchProduct(String product) throws Exception {
        sendKeysAndMoveToElementClickable(inputSearchProducts, product, mediumWait());
        waitForPresenceOfAllElementsLocatedBy(listAutocompleteElements, mediumWait());
        waitForElementListVisible(getWebElementList(listAutocompleteElements), mediumWait());
        clickAndMoveToElementClickable(By.xpath("//div[@title='"+product+"']"), mediumWait());
    }


    public void fillPatientProductEnrollmentFields(String patientName, String productEnrollment) throws Exception {
        if (waitForElementVisibility(inputSearchAccounts, shortWait())) {
            sendKeysAndMoveToElementVisible(inputSearchAccounts, patientName, mediumWait());
            waitForElementVisibility(listAutocomplete, mediumWait());
            waitForPresenceOfAllElementsLocatedBy(listAutocompleteElements, shortWait());
            if (!waitForElementListVisible(getWebElementList(listAutocompleteElements), shortWait())) {
                sendKeysByActions(Keys.TAB.toString());
                clickAndMoveToElementVisible(inputSearchAccounts, shortWait());
                waitForElementListVisible(getWebElementList(listAutocompleteElements), shortWait());
            }
            waitForElementToBeClickableBy(By.xpath("//div[@title='"+patientName+"']"), mediumWait());
            clickAndMoveToElementClickable(By.xpath("//div[@title='"+patientName+"']"), mediumWait());
        }
        if (waitForElementVisibility(inputSearchProductEnrollments, shortWait())) {
            sendKeysAndMoveToElementClickable(inputSearchProductEnrollments, productEnrollment, mediumWait());
            waitForPresenceOfAllElementsLocatedBy(listAutocompleteElements, mediumWait());
            waitForElementListVisible(getWebElementList(listAutocompleteElements), mediumWait());
            waitForElementToBeClickableBy(By.xpath("//div[@title='"+productEnrollment+"']"), mediumWait());
            clickAndMoveToElementClickable(By.xpath("//div[@title='"+productEnrollment+"']"), mediumWait());
        }
    }


    public HashMap<String, String> fillAnonymousCaseInformationForm(HashMap<String, String> formDetails) throws Exception {
        HashMap<String, String> caseInformationForm = new HashMap<>();
        String webElementOption;
        webElementOption = selectDropdownOption(dropdownCaseRequestedBy, listDropdownOptions, formDetails.get("Channel"));
        caseInformationForm.put("CaseRequested", webElementOption);
        webElementOption = selectDropdownOption(dropdownChannel, listDropdownOptions, formDetails.get("Channel"));
        caseInformationForm.put("Channel", webElementOption);
        webElementOption = selectDropdownOption(dropdownCaseStatus, listDropdownOptions, formDetails.get("CaseStatus"));
        caseInformationForm.put("CaseStatus", webElementOption);
        webElementOption = waitForElementVisibility(dropdownSubType, shortWait()) ? selectDropdownOption(dropdownSubType, listDropdownOptions, formDetails.get("CaseSubType")) : "";
        caseInformationForm.put("CaseSubType", webElementOption);
        if(waitForElementVisibility(inputInteractionCase, shortWait())) {
            sendKeysAndMoveToElementVisible(inputInteractionCase, formDetails.get("CaseNumber"), shortWait());
            waitForElementToBeClickableBy(By.xpath("//div[@title='" + formDetails.get("CaseNumber") + "']"), mediumWait());
            clickAndMoveToElementClickable(By.xpath("//div[@title='" + formDetails.get("CaseNumber") + "']"), mediumWait());
        }
        if(waitForElementListVisible(listDiscussTopic, shortWait())){
            if(formDetails.get("DiscussTopic").equalsIgnoreCase(Values.TXT_RANDOM)){
                WebElement el = getRandomWebElementFromList(listDiscussTopic, mediumWait());
                waitForElementVisibility(el, mediumWait());
                scrollToWebElementJS(el);
                webElementOption = getWebElementText(el);
                clickAndMoveToElementClickable(el, mediumWait());
            }else{
                for (WebElement el : listDiscussTopic) {
                    if(getWebElementText(el).equalsIgnoreCase(formDetails.get("DiscussTopic"))){
                        webElementOption = getWebElementText(el);
                        clickAndMoveToElementClickable(el, mediumWait());
                    }
                }
            }
            scrollToWebElementJS(buttonIconRightFlagDiscussionTopic);
            clickAndMoveToElementClickable(buttonIconRightFlagDiscussionTopic, mediumWait());
        }else{
            webElementOption = "";
        }
        if(waitForElementVisibility(inputSearchProducts, shortWait())) {
            sendKeysAndMoveToElementVisible(inputSearchProducts, formDetails.get("ProductName"), shortWait());
            clickAndMoveToElementClickable(By.xpath("//div[@title='" + formDetails.get("ProductName") + "']"), mediumWait());
        }
        caseInformationForm.put("DiscussTopic", webElementOption);
        if(waitForElementVisibility(inputCardNumber, shortWait())){
            webElementOption = formDetails.get("CardNumber").equalsIgnoreCase(Values.TXT_RANDOM) ? getRandomNumber() : formDetails.get("CardNumber");
            caseInformationForm.put("CardNumber", webElementOption);
            sendKeysAndMoveToElementVisible(inputCardNumber, webElementOption, shortWait());
        }else{
            webElementOption = "";
        }
        caseInformationForm.put("CardNumber", webElementOption);
        if(waitForElementVisibility(inputInteractionCase, shortWait())){
            sendKeysAndMoveToElementVisible(inputInteractionCase, formDetails.get("CaseNumber"), shortWait());
            waitForElementVisibility(listAutocomplete, mediumWait());
            waitForElementListVisible(getWebElementList(listAutocompleteElements), mediumWait());
            for(WebElement el : getWebElementList(listAutocompleteElements)){
                if(getWebElementText(el).equalsIgnoreCase(formDetails.get("CaseNumber"))){
                    clickAndMoveToElementClickable(el, mediumWait());
                    break;
                }
            }
            caseInformationForm.put("CaseNumber", formDetails.get("CaseNumber"));
        }
        return caseInformationForm;
    }

    public HashMap<String, String> fillCaseInformationForm(HashMap<String, String> formDetails) throws Exception {
        HashMap<String, String> statusOperation = new HashMap<>();
        String webElementOption;
        waitForElementVisibility(labelCaseTitle, mediumWait());
        String caseName = getWebElementText(labelCaseTitle);
        webElementOption = selectDropdownOption(dropdownChannel, listDropdownOptions, formDetails.get("Channel"));
        statusOperation.put("Channel", webElementOption);
        webElementOption = selectDropdownOption(dropdownCaseStatus, listDropdownOptions, formDetails.get("CaseStatus"));
        statusOperation.put("CaseStatus", webElementOption);
        webElementOption = waitForElementVisibility(dropdownSubType, shortWait()) ? selectDropdownOption(dropdownSubType, listDropdownOptions, formDetails.get("CaseSubType")) : "";
        statusOperation.put("CaseSubType", webElementOption);
        if (waitForElementVisibility(inputSearchProductEnrollments, 1)) {
            sendKeysAndMoveToElementVisible(inputSearchProductEnrollments, formDetails.get("ProductEnrollment"), shortWait());
            waitForElementToBeClickableBy(By.xpath("//div[@title='" + formDetails.get("ProductEnrollment") + "']"), mediumWait());
            clickAndMoveToElementClickable(By.xpath("//div[@title='" + formDetails.get("ProductEnrollment") + "']"), mediumWait());
        }
        if (waitForElementListVisible(listDiscussTopic, 1)) {
            if (formDetails.get("DiscussTopic").equalsIgnoreCase(Values.TXT_RANDOM)) {
                WebElement el = getRandomWebElementFromList(listDiscussTopic, mediumWait());
                waitForElementVisibility(el, mediumWait());
                scrollToWebElementJS(el);
                webElementOption = getWebElementText(el);
                clickAndMoveToElementClickable(el, mediumWait());
            } else {
                for (WebElement el : listDiscussTopic) {
                    if (getWebElementText(el).equalsIgnoreCase(formDetails.get("DiscussTopic"))) {
                        webElementOption = getWebElementText(el);
                        clickAndMoveToElementClickable(el, mediumWait());
                    }
                }
            }
            scrollToWebElementJS(buttonIconRightFlagDiscussionTopic);
            clickAndMoveToElementClickable(buttonIconRightFlagDiscussionTopic, shortWait());
        } else {
            webElementOption = "";
        }
        if(waitForElementVisibility(inputSearchProducts, 1)) {
            sendKeysAndMoveToElementVisible(inputSearchProducts, formDetails.get("ProductName"), shortWait());
            waitForElementToBeClickableBy(By.xpath("//div[@title='" + formDetails.get("ProductName") + "']"), mediumWait());
            clickAndMoveToElementClickable(By.xpath("//div[@title='" + formDetails.get("ProductName") + "']"), mediumWait());
        }
        statusOperation.put("DiscussTopic", webElementOption);
        if (waitForElementVisibility(inputCardNumber, 1)) {
            webElementOption = formDetails.get("CardNumber").equalsIgnoreCase(Values.TXT_RANDOM) ? getRandomNumber() : formDetails.get("CardNumber");
            statusOperation.put("CardNumber", webElementOption);
            sendKeysAndMoveToElementVisible(inputCardNumber, webElementOption, shortWait());
        } else {
            webElementOption = "";
        }
        statusOperation.put("CardNumber", webElementOption);
        if (waitForElementVisibility(inputInteractionCase, 1)) {
            sendKeysAndMoveToElementVisible(inputInteractionCase, formDetails.get("CaseNumber"), shortWait());
            waitForElementVisibility(listAutocomplete, mediumWait());
            waitForElementListVisible(getWebElementList(listAutocompleteElements), mediumWait());
            waitForElementToBeClickableBy(By.xpath("//div[@title='" + formDetails.get("CaseNumber") + "']"), mediumWait());
            clickAndMoveToElementClickable(By.xpath("//div[@title='" + formDetails.get("CaseNumber") + "']"), mediumWait());
            statusOperation.put("CaseNumber", formDetails.get("CaseNumber"));
        }
        if(caseName.contains("Insurance Authorization")){
            fillInsuranceAuthorizationForm(formDetails);
        }
        return statusOperation;
    }

    public void fillInsuranceAuthorizationForm(HashMap<String, String> formDetails) throws Exception {
        if(waitForElementClickable(dropdownIAFormulationType, 1)){
            selectDropdownOption(dropdownIAFormulationType, listDropdownOptions, "RND");
        }
        if(waitForElementVisibility(inputSearchPatientInsurance,1)){
            clickAndMoveToElementClickable(inputSearchPatientInsurance, shortWait());
            clickElementClickable(buttonNewPatientInsurance, shortWait());
            clickElementClickable(radioPatientMedicalInsurance, shortWait());
            clickElementClickable(buttonNextPatientInsurance, shortWait());
            if(waitForElementVisibility(inputSearchAccount, 1)) {
                sendKeysAndMoveToElementVisible(inputSearchAccount, formDetails.get("PatientName"), shortWait());
                waitForElementToBeClickableBy(By.xpath("//div[@title='"+formDetails.get("PatientName")+"' and contains(@class,'primary')]"), shortWait());
                clickElementClickable(By.xpath("//div[@title='"+formDetails.get("PatientName")+"' and contains(@class,'primary')]"), shortWait());
            }
        selectDropdownOption(dropdownInsuranceRank, listDropdownOptions, "RND");
            if(waitForElementVisibility(inputSearchInsurancePayerName, 1)) {
                sendKeysAndMoveToElementVisible(inputSearchInsurancePayerName, "TestUAT payerplan", shortWait());
                sendKeysAndMoveToElementVisible(inputSearchInsurancePayerName, Keys.TAB.toString(), shortWait());
                clickAndMoveToElementClickable(inputSearchInsurancePayerName, shortWait());
                if(waitForElementToBeClickableBy(By.xpath("//div[@title='"+"TestUAT payerplan"+"' and contains(@class,'primary')]"), shortWait())){
                    clickElementJS(getWebElement(By.xpath("//div[@title='"+"TestUAT payerplan"+"' and contains(@class,'primary')]")));
                }
            }
            clickElementClickable(buttonSavePatientInsurance, shortWait());
            waitForElementVisibility(labelPatientInsuranceResult, shortWait());
        }
    }

    public void clickSaveInteraction() throws Exception {
        clickAndMoveToElementClickable(buttonSave, mediumWait());
    }

    public void clickSaveButton() throws Exception {
        clickAndMoveToElementClickable(buttonSave, mediumWait());
    }


    public boolean isCaseOptionPageDisplayed() throws Exception {
        return waitForElementVisibility(formCaseOptions, longWait());
    }


    public String selectDropdownOption(WebElement element, List<WebElement> listElement, String option) throws Exception {
        String statusOperation = "";
        clickAndMoveToElementClickable(element, mediumWait());
        waitForElementListVisible(listElement, mediumWait());
        if (option.equalsIgnoreCase(Values.TXT_RANDOM)) {
            WebElement el = getRandomWebElementFromListExceptFirst(listElement, mediumWait());
            statusOperation = getWebElementText(el);
            clickAndMoveToElementClickable(el, mediumWait());
        } else {
            for (WebElement el : listElement) {
                if (getWebElementText(el).equalsIgnoreCase(option)) {
                    statusOperation = getWebElementText(el);
                    clickAndMoveToElementClickable(el, mediumWait());
                    break;
                }
            }
        }
        return statusOperation;
    }


    private void selectRandomDropdownOption(WebElement element, List<WebElement> listElement) throws Exception {
        clickAndMoveToElementClickable(element, mediumWait());
        waitForElementListVisible(listElement, mediumWait());
        clickAndMoveToElementClickable(getRandomWebElementFromListExceptFirst(listElement, mediumWait()), mediumWait());
    }

    private void selectSpecificDropdownOption(WebElement element, List<WebElement> listElement, String dropdownOption) throws Exception {
        clickAndMoveToElementClickable(element, mediumWait());
        if(waitForElementListVisible(listElement, mediumWait())){
            for (WebElement el : listElement) {
                if (getWebElementText(el).equalsIgnoreCase(dropdownOption)) {
                    clickAndMoveToElementClickable(el, mediumWait());
                    break;
                }
            }
        }
    }
}
