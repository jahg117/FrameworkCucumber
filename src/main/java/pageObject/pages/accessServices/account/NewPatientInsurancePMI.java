package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewPatientInsurancePMI extends CommonFunctions {
    @FindBy(xpath = "//*[@name='SaveEdit']")
    private WebElement button_save;

    @FindBy(xpath = "//input[contains(@data-position-id,'lgcp')]")
    private WebElement dropdown_insuranceRank;

    @FindBy(xpath = "//*[contains(text(),'Insurance Rank')]/..//*[@role='option'][@data-value]")
    private List<WebElement> elementList_insuranceRankList;

    @FindBy(xpath = "//*[contains(text(),'Relationship to Cardholder')]/..//input[1]")
    private WebElement dropdown_relationshipCardholder;

    @FindBy(xpath = "//*[contains(text(),'Relationship to Cardholder')]/..//input[1]/following::*[@role='option'][@data-value]")
    private List<WebElement> elementList_relationshipCardholderList;

    @FindBy(xpath = "//*[contains(text(),'Insurance Payer Name')]/..//*[@placeholder='Search Accounts...']")
    private WebElement dropdown_insurancePayerName;

    @FindBy(xpath = "//*[contains(text(),'Insurance Payer Name')]/..//*[@role='listbox']/..//ul//*[@data-value]")
    private List<WebElement> elementList_insurancePayerNameList;

    @FindBy(xpath = "//*[contains(text(),'Insurance Plan Name')]/..//*[contains(@placeholder,'Search Payer')]")
    private WebElement dropdown_insurancePlanName;

    @FindBy(xpath = "//*[contains(text(),'Insurance Plan Name')]/..//*[@role='listbox']/..//ul//*[@data-value]")
    private List<WebElement> elementList_insurancePlanNameList;

    @FindBy(xpath = "//input[contains(@name,'_Insurance_Phone_Number')]")
    private WebElement input_insurancePhoneNumber;

    @FindBy(xpath = "//input[contains(@name,'_Medical_Group_Number_')]")
    private WebElement input_groupNumber;

    @FindBy(xpath = "//input[contains(@name,'_Cardholder_ID_')]")
    private WebElement input_memberID;

    @FindBy(xpath = "//input[contains(@name,'_Policy_Number_')]")
    private WebElement input_policyNumber;




    public void clickOnSaveNoPI() throws Exception {
        clickAndMoveToElementClickable(button_save, mediumWait());
    }

    public void fillPMIForm(String payerName, String phoneNumber,String groupNumber,String policyNumber,String memberID) throws Exception {
        clickWhileCondition(dropdown_insuranceRank, "aria-expanded", "false", mediumWait());
        getRandomWebElementFromList(elementList_relationshipCardholderList, mediumWait()).getAttribute("data-value");

        clickWhileCondition(dropdown_relationshipCardholder, "aria-expanded", "false", mediumWait());
        getRandomWebElementFromList(elementList_relationshipCardholderList, mediumWait()).getAttribute("data-value");

        clickWhileCondition(dropdown_insurancePayerName, "aria-expanded", "false", mediumWait());
        sendKeysAndMoveToElementVisible(dropdown_insurancePayerName, payerName, mediumWait());
        clickAndMoveToElementVisible(elementList_insurancePayerNameList.get(0), mediumWait());

        clickWhileCondition(dropdown_insurancePlanName, "aria-expanded", "false", mediumWait());
        sendKeysAndMoveToElementVisible(dropdown_insurancePlanName, payerName, mediumWait());
        clickAndMoveToElementVisible(elementList_insurancePlanNameList.get(0), mediumWait());

        clickAndMoveToElementClickable(input_insurancePhoneNumber, mediumWait());
        sendKeysAndMoveToElementVisible(input_insurancePhoneNumber, phoneNumber, mediumWait());

        clickAndMoveToElementClickable(input_groupNumber, mediumWait());
        sendKeysAndMoveToElementVisible(input_groupNumber, groupNumber, mediumWait());

        clickAndMoveToElementClickable(input_policyNumber, mediumWait());
        sendKeysAndMoveToElementVisible(input_policyNumber, policyNumber, mediumWait());

        clickAndMoveToElementClickable(input_memberID, mediumWait());
        sendKeysAndMoveToElementVisible(input_memberID, memberID, mediumWait());


    }

}