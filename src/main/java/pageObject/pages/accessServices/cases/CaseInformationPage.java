package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class CaseInformationPage extends CommonFunctions {
    @FindBy(xpath = "//button[@title='Save']")
    private WebElement button_save;

    @FindBy(xpath = "(//div[contains(@class,'forceDetailPanelDesktop')])[last()]//*[contains(text(),'New Case')]")
    private WebElement form_caseOptions;

    @FindBy(xpath = "//span[contains(text(),'Case Requested By')]/following::*[@class='select'][1]")
    private WebElement dropdown_caseRequestedBy;

    @FindBy(xpath = "//span[contains(text(),'Case Information')]/following::*[@class='select'][1]")
    private WebElement dropdown_channel;

    @FindBy(xpath = "//*[contains(@class,'uiMenuList--short visible')]//a")
    private List<WebElement> list_dropdownOptions;

    @FindBy(xpath = "//span[contains(text(),'Case Status')]/../..//a[@class='select'][1]")
    private WebElement dropdown_caseStatus;

    @FindBy(xpath = "(//span[contains(text(),'Case Sub-Type')]/following::*[@class='select'][1])[last()]")
    private WebElement dropdown_subType;

    @FindBy(xpath = "//div[contains(text(),'Discussion Topic')]/..//span[@class='slds-truncate']")
    private List<WebElement> list_discussTopic;

    @FindBy(xpath = "//div[contains(text(),'Discussion Topic')]/..//div[contains(@class,'dueling-list')]//button[@title='Move selection to Chosen']")
    private WebElement button_iconRightFlagDiscussionTopic;

    @FindBy(xpath = "//ul[@class='lookup__list  visible']")
    private WebElement list_autocomplete;

    @FindBy(xpath = "//input[@title='Search Product Enrollments']")
    private WebElement input_searchProductEnrollments;

    @FindBy(xpath = "//*[contains(text(),'Card Number')]/../..//input")
    private WebElement input_cardNumber;

    @FindBy(xpath = "//span[contains(@class,'genericError')]")
    private WebElement label_genericError;

    @FindBy(xpath = "//*[contains(text(),'Product')]/following::*[./text()=\"Product\"]/../..//span[@class='deleteIcon']")
    private WebElement button_deleteProduct;

    @FindBy(xpath = "//input[@title='Search Products']")
    private WebElement input_searchProducts;

    @FindBy(xpath = "//input[@title='Search Cases']")
    private WebElement input_interactionCase;

    @FindBy(xpath = "//*[contains(text(),'Enrolled Patient')]/following::*//input[@title='Search Accounts']")
    private WebElement input_searchAccounts;

    private By list_autocompleteElements = By.xpath("//div[contains(@class,'lookup__menu uiAbstractList')]//li[contains(@class,'default uiAutocompleteOption')]//div[contains(@class,'primaryLabel')]");

    private By button_createNewElementList = By.xpath("//div[contains(@class,'lookup__menu uiAbstractList')]//div[contains(@class,'createNew')]//span");

    @FindBy(xpath = "//span[contains(text(),'Product Enrollment')]/../..//span[contains(text(),'PE-')]")
    private WebElement label_productEnrollment;

    protected FileReading fileReading = new FileReading();
    private final Logger logger = Logger.getLogger(CommonFunctions.class);
    public static int maxNumberOfTries = 0;

    Class<?> myClass;

    {
        try {
            fileReading.setLog4jFile();
            fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
            maxNumberOfTries = Integer.parseInt(fileReading.getField(Values.TXT_RETRYWHILE));
            myClass = Class.forName("base.functions" + "." + "CommonFunctions");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getProductEnrollment() throws Exception {
        String statusOperation = "";
        try {
            waitForElementVisibility(label_productEnrollment, mediumWait());
            statusOperation = getWebElementText(label_productEnrollment);
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("getProductEnrollment")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "getProductEnrollment");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public boolean fillCaseInformationForm() throws Exception {
        boolean statusOperation = false;
        try {
            selectRandomDropdownOption(dropdown_channel, list_dropdownOptions);
            selectSpecificDropdownOption(dropdown_caseStatus, list_dropdownOptions, "Open");
            selectRandomDropdownOption(dropdown_subType, list_dropdownOptions);
            if (waitForElementListVisible(list_discussTopic, shortWait())) {
                clickAndMoveToElementClickable(getRandomWebElementFromList(list_discussTopic, mediumWait()), mediumWait());
                clickAndMoveToElementClickable(button_iconRightFlagDiscussionTopic, mediumWait());
            }
            if (waitForElementVisibility(input_cardNumber, shortWait())) {
                sendKeysAndMoveToElementVisible(input_cardNumber, getRandomNumber(), shortWait());
            }
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("fillCaseInformationForm")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "fillCaseInformationForm");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public HashMap<String, String> fillCaseInteractionForm(HashMap<String, String> interactionForm) throws Exception {
        HashMap<String, String> statusOperation = new HashMap<>();
        String webElementOption;
        try {
            webElementOption = selectDropdownOption(dropdown_caseStatus, list_dropdownOptions, interactionForm.get("CaseStatus"));
            statusOperation.put("CaseStatus", webElementOption);
            webElementOption = selectDropdownOption(dropdown_channel, list_dropdownOptions, interactionForm.get("Channel"));
            statusOperation.put("Channel", webElementOption);
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("fillCaseInteractionForm")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "fillCaseInteractionForm");
                        statusOperation = (HashMap<String, String>) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), interactionForm);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public boolean fillSearchProduct(String product) throws Exception {
        boolean statusOperation = false;
        try {
            sendKeysAndMoveToElementClickable(input_searchProducts, product, mediumWait());
            waitForPresenceOfAllElementsLocatedBy(list_autocompleteElements, mediumWait());
            waitForElementListVisible(getWebElementList(list_autocompleteElements), mediumWait());
            for (WebElement el : getWebElementList(list_autocompleteElements)) {
                if (getWebElementText(el).equalsIgnoreCase(product)) {
                    clickAndMoveToElementClickable(el, mediumWait());
                    break;
                }
            }
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("fillSearchProduct")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "fillSearchProduct");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), product);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public boolean fillPatientProductEnrollmentFields(String patientName, String productEnrollment) throws Exception {
        boolean statusOperation = false;
        try {
            if (waitForElementVisibility(input_searchAccounts, shortWait())) {
                sendKeysAndMoveToElementVisible(input_searchAccounts, patientName, mediumWait());
                waitForElementVisibility(list_autocomplete, mediumWait());
                waitForPresenceOfAllElementsLocatedBy(list_autocompleteElements, shortWait());
                if (!waitForElementListVisible(getWebElementList(list_autocompleteElements), shortWait())) {
                    sendKeysByActions(Keys.TAB.toString());
                    clickAndMoveToElementVisible(input_searchAccounts, shortWait());
                    waitForElementListVisible(getWebElementList(list_autocompleteElements), shortWait());
                }
                for (WebElement el : getWebElementList(list_autocompleteElements)) {
                    if (getWebElementText(el).equalsIgnoreCase(patientName)) {
                        clickAndMoveToElementClickable(el, mediumWait());
                        break;
                    }
                }
                statusOperation = true;
            }
            if (waitForElementVisibility(input_searchProductEnrollments, shortWait())) {
                clickAndMoveToElementClickable(input_searchProductEnrollments, mediumWait());
                waitForPresenceOfAllElementsLocatedBy(list_autocompleteElements, mediumWait());
                waitForElementListVisible(getWebElementList(list_autocompleteElements), mediumWait());
                for (WebElement el : getWebElementList(list_autocompleteElements)) {
                    if (getWebElementText(el).equalsIgnoreCase(productEnrollment)) {
                        clickAndMoveToElementClickable(el, mediumWait());
                        break;
                    }
                }
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), patientName, productEnrollment);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public HashMap<String, String> fillAnonymousCaseInformationForm(HashMap<String, String> formDetails) throws Exception {
        HashMap<String, String> caseInformationForm = new HashMap<>();
        String webElementOption;
        webElementOption = selectDropdownOption(dropdown_caseRequestedBy, list_dropdownOptions, formDetails.get("Channel"));
        caseInformationForm.put("CaseRequested", webElementOption);
        webElementOption = selectDropdownOption(dropdown_channel, list_dropdownOptions, formDetails.get("Channel"));
        caseInformationForm.put("Channel", webElementOption);
        webElementOption = selectDropdownOption(dropdown_caseStatus, list_dropdownOptions, formDetails.get("CaseStatus"));
        caseInformationForm.put("CaseStatus", webElementOption);
        webElementOption = waitForElementVisibility(dropdown_subType, 2) ? selectDropdownOption(dropdown_subType, list_dropdownOptions, formDetails.get("CaseSubType")) : "";
        caseInformationForm.put("CaseSubType", webElementOption);
        if(waitForElementVisibility(input_interactionCase, 3)) {
            sendKeysAndMoveToElementVisible(input_interactionCase, formDetails.get("CaseNumber"), 3);
            clickAndMoveToElementClickable(By.xpath("//div[@title='" + formDetails.get("CaseNumber") + "']"), 10);
        }
        if(waitForElementListVisible(list_discussTopic, 3)){
            if(formDetails.get("DiscussTopic").equalsIgnoreCase("random")){
                WebElement el = getRandomWebElementFromList(list_discussTopic, 10);
                waitForElementVisibility(el, 10);
                scrollToWebElementJS(el);
                webElementOption = getWebElementText(el);
                clickAndMoveToElementClickable(el, 10);
                clickElementJS(el);
            }else{
                for (WebElement el : list_discussTopic) {
                    if(getWebElementText(el).equalsIgnoreCase(formDetails.get("DiscussTopic"))){
                        webElementOption = getWebElementText(el);
                        clickAndMoveToElementClickable(el, 10);
                    }
                }
            }
            scrollToWebElementJS(button_iconRightFlagDiscussionTopic);
            clickAndMoveToElementClickable(button_iconRightFlagDiscussionTopic, 10);
            clickElementJS(button_iconRightFlagDiscussionTopic);
        }else{
            webElementOption = "";
        }
        if(waitForElementVisibility(input_searchProducts, 3)) {
            sendKeysAndMoveToElementVisible(input_searchProducts, formDetails.get("ProductName"), 3);
            clickAndMoveToElementClickable(By.xpath("//div[@title='" + formDetails.get("ProductName") + "']"), 10);
        }
        caseInformationForm.put("DiscussTopic", webElementOption);
        if(waitForElementVisibility(input_cardNumber, 1)){
            webElementOption = formDetails.get("CardNumber").equalsIgnoreCase("random") ? getRandomNumber() : formDetails.get("CardNumber");
            caseInformationForm.put("CardNumber", webElementOption);
            sendKeysAndMoveToElementVisible(input_cardNumber, webElementOption, 3);
        }else{
            webElementOption = "";
        }
        caseInformationForm.put("CardNumber", webElementOption);
        if(waitForElementVisibility(input_interactionCase, 1)){
            sendKeysAndMoveToElementVisible(input_interactionCase, formDetails.get("CaseNumber"), 5);
            waitForElementVisibility(list_autocomplete, 10);
            waitForElementListVisible(getWebElementList(list_autocompleteElements), 10);
            for(WebElement el : getWebElementList(list_autocompleteElements)){
                if(getWebElementText(el).equalsIgnoreCase(formDetails.get("CaseNumber"))){
                    clickAndMoveToElementClickable(el, 10);
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
        try {
            webElementOption = selectDropdownOption(dropdown_channel, list_dropdownOptions, formDetails.get("Channel"));
            statusOperation.put("Channel", webElementOption);
            webElementOption = selectDropdownOption(dropdown_caseStatus, list_dropdownOptions, formDetails.get("CaseStatus"));
            statusOperation.put("CaseStatus", webElementOption);
            webElementOption = waitForElementVisibility(dropdown_subType, shortWait()) ? selectDropdownOption(dropdown_subType, list_dropdownOptions, formDetails.get("CaseSubType")) : "";
            statusOperation.put("CaseSubType", webElementOption);
            if (waitForElementVisibility(input_searchProductEnrollments, shortWait())) {
                sendKeysAndMoveToElementVisible(input_searchProductEnrollments, formDetails.get("ProductEnrollment"), shortWait());
                clickAndMoveToElementClickable(By.xpath("//div[@title='" + formDetails.get("ProductEnrollment") + "']"), mediumWait());
            }
            if (waitForElementListVisible(list_discussTopic, shortWait())) {
                if (formDetails.get("DiscussTopic").equalsIgnoreCase("random")) {
                    WebElement el = getRandomWebElementFromList(list_discussTopic, mediumWait());
                    waitForElementVisibility(el, mediumWait());
                    scrollToWebElementJS(el);
                    webElementOption = getWebElementText(el);
                    clickAndMoveToElementClickable(el, mediumWait());
                } else {
                    for (WebElement el : list_discussTopic) {
                        if (getWebElementText(el).equalsIgnoreCase(formDetails.get("DiscussTopic"))) {
                            webElementOption = getWebElementText(el);
                            clickAndMoveToElementClickable(el, mediumWait());
                        }
                    }
                }
                scrollToWebElementJS(button_iconRightFlagDiscussionTopic);
                clickAndMoveToElementClickable(button_iconRightFlagDiscussionTopic, mediumWait());
            } else {
                webElementOption = "";
            }
            if(waitForElementVisibility(input_searchProducts, 3)) {
                sendKeysAndMoveToElementVisible(input_searchProducts, formDetails.get("ProductName"), 3);
                clickAndMoveToElementClickable(By.xpath("//div[@title='" + formDetails.get("ProductName") + "']"), 10);
            }
            statusOperation.put("DiscussTopic", webElementOption);
            if (waitForElementVisibility(input_cardNumber, shortWait())) {
                webElementOption = formDetails.get("CardNumber").equalsIgnoreCase("random") ? getRandomNumber() : formDetails.get("CardNumber");
                statusOperation.put("CardNumber", webElementOption);
                sendKeysAndMoveToElementVisible(input_cardNumber, webElementOption, shortWait());
            } else {
                webElementOption = "";
            }
            statusOperation.put("CardNumber", webElementOption);
            if (waitForElementVisibility(input_interactionCase, 1)) {
                sendKeysAndMoveToElementVisible(input_interactionCase, formDetails.get("CaseNumber"), shortWait());
                waitForElementVisibility(list_autocomplete, mediumWait());
                waitForElementListVisible(getWebElementList(list_autocompleteElements), mediumWait());
                for (WebElement el : getWebElementList(list_autocompleteElements)) {
                    if (getWebElementText(el).equalsIgnoreCase(formDetails.get("CaseNumber"))) {
                        clickAndMoveToElementClickable(el, mediumWait());
                        break;
                    }
                }
                statusOperation.put("CaseNumber", formDetails.get("CaseNumber"));
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("fillCaseInformationForm")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "fillCaseInformationForm");
                        statusOperation = (HashMap<String, String>) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), formDetails);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public void clickSaveInteraction() throws Exception {
        clickAndMoveToElementClickable(button_save, mediumWait());
    }

    public String clickSaveButton() throws Exception {
        String statusOperation = "";
        try {
            clickAndMoveToElementClickable(button_save, mediumWait());
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickSaveButton")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickSaveButton");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public boolean isCaseOptionPageDisplayed() throws Exception {
        boolean statusOperation = false;
        try {
            statusOperation = waitForElementVisibility(form_caseOptions, longWait());
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("isCaseOptionPageDisplayed")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "isCaseOptionPageDisplayed");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public String selectDropdownOption(WebElement element, List<WebElement> listElement, String option) throws Exception {
        String statusOperation = "";
        try {
            clickAndMoveToElementClickable(element, mediumWait());
            waitForElementListVisible(listElement, mediumWait());
            if (option.equalsIgnoreCase("random")) {
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
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("selectDropdownOption")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "selectDropdownOption");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), element, listElement, option);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    private boolean selectRandomDropdownOption(WebElement element, List<WebElement> listElement) throws Exception {
        boolean statusOperation = false;
        try {
            clickAndMoveToElementClickable(element, mediumWait());
            waitForElementListVisible(listElement, mediumWait());
            clickAndMoveToElementClickable(getRandomWebElementFromListExceptFirst(listElement, mediumWait()), mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("selectRandomDropdownOption")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "selectRandomDropdownOption");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), element, listElement);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    private boolean selectSpecificDropdownOption(WebElement element, List<WebElement> listElement, String dropdownOption) throws Exception {
        boolean statusOperation = false;
        try {
            clickAndMoveToElementClickable(element, mediumWait());
            waitForElementListVisible(listElement, mediumWait());
            for (WebElement el : listElement) {
                if (getWebElementText(el).equalsIgnoreCase(dropdownOption)) {
                    clickAndMoveToElementClickable(el, mediumWait());
                    break;
                }
            }
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("selectSpecificDropdownOption")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "selectSpecificDropdownOption");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), element, listElement, dropdownOption);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }
}
