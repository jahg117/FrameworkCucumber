package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    @FindBy(xpath = "//div[contains(text(),'Discussion Topic')]/..//*[@data-key='right']")
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

    public String getProductEnrollment(){
        waitForElementVisibility(label_productEnrollment, 10);
        return getWebElementText(label_productEnrollment);
    }

    public void fillCaseInformationForm() throws Exception {
        selectRandomDropdownOption(dropdown_channel, list_dropdownOptions);
        selectSpecificDropdownOption(dropdown_caseStatus, list_dropdownOptions, "Open");
        selectRandomDropdownOption(dropdown_subType, list_dropdownOptions);
        if(waitForElementListVisible(list_discussTopic, 1)){
            clickAndMoveToElementClickable(getRandomWebElementFromList(list_discussTopic, 10), 10);
            clickAndMoveToElementClickable(button_iconRightFlagDiscussionTopic, 10);
        }
        if(waitForElementVisibility(input_cardNumber, 1)){
            sendKeysAndMoveToElementVisible(input_cardNumber, getRandomNumber(), 3);
        }
    }

    public HashMap<String, String> fillCaseInteractionForm(HashMap<String, String> interactionForm) throws Exception {
        HashMap<String, String> caseInformationForm = new HashMap<>();
        String webElementOption;
        webElementOption = selectDropdownOption(dropdown_caseStatus, list_dropdownOptions, interactionForm.get("CaseStatus"));
        caseInformationForm.put("CaseStatus", webElementOption);
        webElementOption = selectDropdownOption(dropdown_channel, list_dropdownOptions, interactionForm.get("Channel"));
        caseInformationForm.put("Channel", webElementOption);
        return caseInformationForm;
    }

    public void fillSearchProduct(String product) throws Exception {
        sendKeysAndMoveToElementClickable(input_searchProducts, product, 10);
        waitForPresenceOfAllElementsLocatedBy(list_autocompleteElements, 10);
        waitForElementListVisible(getWebElementList(list_autocompleteElements), 10);
        for(WebElement el : getWebElementList(list_autocompleteElements)){
            if(getWebElementText(el).equalsIgnoreCase(product)){
                clickAndMoveToElementClickable(el, 10);
                break;
            }
        }
    }

    public void fillPatientProductEnrollmentFields(String patientName, String productEnrollment) throws Exception {
        if(waitForElementVisibility(input_searchAccounts, 6)) {
            sendKeysAndMoveToElementVisible(input_searchAccounts, patientName, 20);
            waitForElementVisibility(list_autocomplete, 10);
            waitForPresenceOfAllElementsLocatedBy(list_autocompleteElements, 5);
            if(!waitForElementListVisible(getWebElementList(list_autocompleteElements), 5)) {
                sendKeysByActions(Keys.TAB.toString());
                clickAndMoveToElementVisible(input_searchAccounts, 5);
                waitForElementListVisible(getWebElementList(list_autocompleteElements), 5);
            }
            for (WebElement el : getWebElementList(list_autocompleteElements)) {
                if (getWebElementText(el).equalsIgnoreCase(patientName)) {
                    clickAndMoveToElementClickable(el, 10);
                    break;
                }
            }
        }
        if(waitForElementVisibility(input_searchProductEnrollments, 3)) {
            clickAndMoveToElementClickable(input_searchProductEnrollments, 10);
            waitForPresenceOfAllElementsLocatedBy(list_autocompleteElements, 10);
            waitForElementListVisible(getWebElementList(list_autocompleteElements), 10);
            for (WebElement el : getWebElementList(list_autocompleteElements)) {
                if (getWebElementText(el).equalsIgnoreCase(productEnrollment)) {
                    clickAndMoveToElementClickable(el, 10);
                    break;
                }
            }
        }
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
        }else{
            webElementOption = "";
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
        HashMap<String, String> caseInformationForm = new HashMap<>();
        String webElementOption;
        webElementOption = selectDropdownOption(dropdown_channel, list_dropdownOptions, formDetails.get("Channel"));
        caseInformationForm.put("Channel", webElementOption);
        webElementOption = selectDropdownOption(dropdown_caseStatus, list_dropdownOptions, formDetails.get("CaseStatus"));
        caseInformationForm.put("CaseStatus", webElementOption);
        webElementOption = waitForElementVisibility(dropdown_subType, 2) ? selectDropdownOption(dropdown_subType, list_dropdownOptions, formDetails.get("CaseSubType")) : "";
        caseInformationForm.put("CaseSubType", webElementOption);
        if(waitForElementVisibility(input_searchProductEnrollments, 3)) {
            sendKeysAndMoveToElementVisible(input_searchProductEnrollments, formDetails.get("ProductEnrollment"), 3);
            clickAndMoveToElementClickable(By.xpath("//div[@title='" + formDetails.get("ProductEnrollment") + "']"), 10);
        }
         if(waitForElementListVisible(list_discussTopic, 3)){
            if(formDetails.get("DiscussTopic").equalsIgnoreCase("random")){
                WebElement el = getRandomWebElementFromList(list_discussTopic, 10);
                waitForElementVisibility(el, 10);
                scrollToWebElementJS(el);
                webElementOption = getWebElementText(el);
                clickAndMoveToElementClickable(el, 10);
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
        }else{
            webElementOption = "";
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

    public void clickSaveInteraction() throws Exception{
        clickAndMoveToElementClickable(button_save, 10);
    }

    public String clickSaveButton() throws Exception {
        String product = "";
        clickAndMoveToElementClickable(button_save, 10);
        if(waitForElementVisibility(label_genericError, 5)){
            if(waitForElementVisibility(button_iconRightFlagDiscussionTopic,5)){
            clickAndMoveToElementClickable(button_iconRightFlagDiscussionTopic, 5);}
            sendKeysAndMoveToElementVisible(input_searchProducts, "Fasenra",5);
            waitForElementListVisible(getWebElementList(list_autocompleteElements), 10);
            for (WebElement el : getWebElementList(list_autocompleteElements)) {
                if(getWebElementText(el).equalsIgnoreCase("Fasenra")){
                    product = getWebElementText(el);
                    clickAndMoveToElementClickable(el, 10);
                    break;
                }
            }
            clickAndMoveToElementClickable(button_save, 10);
        }
        return product;
    }

    public boolean isCaseOptionPageDisplayed(){
        return waitForElementVisibility(form_caseOptions, 30);
    }

    public String selectDropdownOption(WebElement element, List<WebElement> listElement, String option) throws Exception {
        String textElement = "";
        clickAndMoveToElementClickable(element, 10);
        waitForElementListVisible(listElement, 10);
        if(option.equalsIgnoreCase("random")){
            WebElement el = getRandomWebElementFromListExceptFirst(listElement, 10);
            textElement = getWebElementText(el);
            clickAndMoveToElementClickable(el, 10);
        }else{
            for(WebElement el : listElement){
                if(getWebElementText(el).equalsIgnoreCase(option)){
                    textElement = getWebElementText(el);
                    clickAndMoveToElementClickable(el, 10);
                    break;
                }
            }
        }
        return textElement;
    }

    private void selectRandomDropdownOption(WebElement element, List<WebElement> listElement) throws Exception {
        clickAndMoveToElementClickable(element, 10);
        waitForElementListVisible(listElement, 10);
        clickAndMoveToElementClickable(getRandomWebElementFromListExceptFirst(listElement, 10), 10);
    }

    private void selectSpecificDropdownOption(WebElement element, List<WebElement> listElement, String dropdownOption) throws Exception {
        clickAndMoveToElementClickable(element, 10);
        waitForElementListVisible(listElement, 10);
        for(WebElement el : listElement){
            if(getWebElementText(el).equalsIgnoreCase(dropdownOption)){
                clickAndMoveToElementClickable(el, 10);
                break;
            }
        }
    }
}
