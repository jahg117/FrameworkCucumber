package pageObject.pages.accessServices.Cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.util.HashMap;
import java.util.List;

public class CaseInformationPage extends CommonFunctions {
    @FindBy(xpath = "//button[@title='Save']")
    private WebElement button_save;

    @FindBy(xpath = "(//div[contains(@class,'forceDetailPanelDesktop')])[last()]//*[contains(text(),'New Case')]")
    private WebElement form_caseOptions;

    @FindBy(xpath = "//span[contains(text(),'Case Information')]/following::*[@class='select'][1]")
    private WebElement dropdown_channel;

    @FindBy(xpath = "//*[contains(@class,'uiMenuList--short visible')]//a")
    private List<WebElement> list_dropdownOptions;

    @FindBy(xpath = "//span[contains(text(),'Case Status')]/following::*[@class='select'][1]")
    private WebElement dropdown_caseStatus;

    @FindBy(xpath = "//span[contains(text(),'Case Sub-Type')]/following::*[@class='select'][1]")
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

    public void fillCaseInformationForm(HashMap<String, String> formDetails) throws Exception {
        selectDropdownOption(dropdown_channel, list_dropdownOptions, formDetails.get("Channel"));
        selectDropdownOption(dropdown_caseStatus, list_dropdownOptions, formDetails.get("CaseStatus"));
        selectDropdownOption(dropdown_subType, list_dropdownOptions, formDetails.get("CaseSubType"));
        if(waitForElementListVisible(list_discussTopic, 1)){
            if(formDetails.get("DiscussTopic").equalsIgnoreCase("random")){
                clickAndMoveToElementClickable(getRandomWebElementFromList(list_discussTopic, 10), 10);
            }else{
                for (WebElement el : list_discussTopic) {
                    if(getWebElementText(el).equalsIgnoreCase(formDetails.get("DiscussTopic"))){
                        clickAndMoveToElementClickable(el, 10);
                    }
                }
            }
            clickAndMoveToElementClickable(button_iconRightFlagDiscussionTopic, 10);
        }
        if(waitForElementVisibility(input_cardNumber, 1)){
            String number = formDetails.get("CardNumber").equalsIgnoreCase("random") ? getRandomNumber() : formDetails.get("CardNumber");
            sendKeysAndMoveToElementVisible(input_cardNumber, number, 3);
        }
    }

    public void clickSaveButton() throws Exception {
        clickAndMoveToElementClickable(button_save, 10);
    }

    public boolean isCaseOptionPageDisplayed(){
        return waitForElementVisibility(form_caseOptions, 30);
    }

    public void selectDropdownOption(WebElement element, List<WebElement> listElement, String option) throws Exception {
        clickAndMoveToElementClickable(element, 10);
        waitForElementListVisible(listElement, 10);
        if(option.equalsIgnoreCase("random")){
            clickAndMoveToElementClickable(getRandomWebElementFromListExceptFirst(listElement, 10), 10);
        }else{
            for(WebElement el : listElement){
                if(getWebElementText(el).equalsIgnoreCase(option)){
                    clickAndMoveToElementClickable(el, 10);
                    break;
                }
            }
        }
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
