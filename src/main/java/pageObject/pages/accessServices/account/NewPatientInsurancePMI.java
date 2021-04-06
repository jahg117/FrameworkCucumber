package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewPatientInsurancePMI extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    Faker faker = new Faker();
    @FindBy(xpath = "//*[@name='SaveEdit']")
    private WebElement button_save;

    @FindBy(xpath = "//label[contains(text(),'Insurance Rank')]/..//*[@role='combobox']")
    private WebElement dropdown_insuranceRank;

    @FindBy(xpath = "//*[contains(text(),'Insurance Rank')]/..//*[@role='option'][@data-value][not(@data-value='')]")
    private List<WebElement> elementList_insuranceRankList;

    @FindBy(xpath = "//*[contains(text(),'Relationship to Cardholder')]/..//input[1]")
    private WebElement dropdown_relationshipCardholder;

    @FindBy(xpath = "//*[contains(text(),'Relationship to Cardholder')]/..//input[1]/following::*[@role='option'][@data-value][not(@data-value='')]")
    private List<WebElement> elementList_relationshipCardholderList;

    @FindBy(xpath = "//input[contains(@name,'_Cardholder_Name')]")
    private WebElement input_cardHolderName;

    @FindBy(xpath = "//input[contains(@name,'_Cardholder_DOB')]")
    private WebElement input_cardHolderDOB;

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

    @FindBy(xpath = "//span[contains(text(),'Show All Results for')]")
    private WebElement label_ShowResults;

    @FindBy(xpath = "//*[contains(text(),'Insurance Plan Name')]/..//span[contains(text(),'Show All Results for')][1]")
    private WebElement label_planNameShowResults;


    /**
     * This method is used to filter if the date shall be created randomly or not
     *
     * @param dataPMI it contains all the date related to fill the PMI form
     * @throws Exception
     */
    public void fillPMIForm(List<String> dataPMI) throws Exception {
        int dataCounter = 0;
        for (String data : dataPMI) {
            if (data.trim().equalsIgnoreCase("RND")) {
                fillPMIFormRandomly(data, dataCounter);
            } else {
                if (data.trim().equalsIgnoreCase("N_A")) {
                    logger.info("The Data At Position : " + dataCounter + "Is Not Required");
                } else {
                    fillPMIForm(data, dataCounter);
                }
            }
            dataCounter++;
        }
        clickAndMoveToElementClickable(button_save, shortWait());
    }

    /**
     * It will create/select randomly all the data need it to create the PMI insurance type form
     *
     * @param dataPMI      it contains all the date related to fill the PMI form
     * @param dataPosition is a int number to identify which data from reord we need to create or select
     * @throws Exception
     */
    public void fillPMIFormRandomly(String dataPMI, int dataPosition) throws Exception {
        switch (dataPosition) {
            case 0:
                //============Insurance Rank Randomly
                clickWhileCondition(dropdown_insuranceRank, "aria-expanded", "false", mediumWait());
                clickAndMoveToElementVisible(getRandomWebElementFromList(elementList_insuranceRankList, mediumWait()), shortWait());
                break;
            case 1:
                //============Relationship to Cardholder Randomly
                clickWhileCondition(dropdown_relationshipCardholder, "aria-expanded", "false", mediumWait());
                clickAndMoveToElementVisible(getRandomWebElementFromList(elementList_relationshipCardholderList, mediumWait()), shortWait());
                break;
            case 2:
                //============Cardholder Name Randomly
                clickAndMoveToElementClickable(input_cardHolderName, mediumWait());
                sendKeysAndMoveToElementVisible(input_cardHolderName, String.valueOf(faker.name().firstName()), mediumWait());
                break;
            case 3:
                //============CardHolder DOB
                clickAndMoveToElementClickable(input_cardHolderDOB, mediumWait());
                sendKeysAndMoveToElementVisible(input_cardHolderDOB, String.valueOf(getRandomDate()), mediumWait());
                break;
            case 4:
                //============Insurance Payer Name Randomly
                clickWhileCondition(dropdown_insurancePayerName, "aria-expanded", "false", mediumWait());
                sendKeysAndMoveToElementVisible(dropdown_insurancePayerName, faker.name().firstName(), mediumWait());
                waitForElementVisibility(label_ShowResults, mediumWait());
                clickAndMoveToElementVisible(elementList_insurancePayerNameList.get(0), mediumWait());
                break;
            case 5:
                //============Insurance Plan Name Randomly
                clickWhileCondition(dropdown_insurancePlanName, "aria-expanded", "false", mediumWait());
                sendKeysAndMoveToElementVisible(dropdown_insurancePlanName, faker.name().firstName(), mediumWait());
                waitForElementVisibility(label_planNameShowResults, mediumWait());
                clickAndMoveToElementVisible(elementList_insurancePlanNameList.get(0), mediumWait());
                break;
            case 6:
                //============Insurance Phone Number Randomly
                clickAndMoveToElementClickable(input_insurancePhoneNumber, mediumWait());
                sendKeysAndMoveToElementVisible(input_insurancePhoneNumber, String.valueOf(faker.number().randomNumber(10, true)), mediumWait());
                break;
            case 7:
                //============Insurance Group Number Randomly
                clickAndMoveToElementClickable(input_groupNumber, mediumWait());
                sendKeysAndMoveToElementVisible(input_groupNumber, String.valueOf(faker.number().randomNumber(6, true)), mediumWait());
                break;
            case 8:
                //============Insurance Policy Number Randomly
                scrollToWebElementJS(input_policyNumber);
                clickAndMoveToElementClickable(input_policyNumber, mediumWait());
                sendKeysAndMoveToElementVisible(input_policyNumber, String.valueOf(faker.number().randomNumber(5, true)), mediumWait());
                break;
            case 9:
                //============Insurance MemberID Number Randomly
                scrollToWebElementJS(input_memberID);
                clickAndMoveToElementClickable(input_memberID, mediumWait());
                sendKeysAndMoveToElementVisible(input_memberID, String.valueOf(faker.number().randomNumber(4, true)), mediumWait());
                break;
        }
    }

    /**
     * It will fill the PMI form with the data provided in the dataPMI record (specific data)
     *
     * @param dataPMI      it contains all the date related to fill the PMI form
     * @param dataPosition is a int number to identify which data from reord we need to create or select
     * @throws Exception
     */
    public void fillPMIForm(String dataPMI, int dataPosition) throws Exception {
        switch (dataPosition) {
            case 0:
                //============Insurance Rank Randomly
                clickWhileCondition(dropdown_insuranceRank, "aria-expanded", "false", mediumWait());
                getWebElementByAttributeFromList(elementList_insuranceRankList, "data-value", dataPMI);
                clickAndMoveToElementVisible(getWebElementByAttributeFromList(elementList_insuranceRankList, "data-value", dataPMI), shortWait());
                break;
            case 1:
                //============Relationship to Cardholder Randomly
                clickWhileCondition(dropdown_relationshipCardholder, "aria-expanded", "false", mediumWait());
                clickAndMoveToElementVisible(getWebElementByAttributeFromList(elementList_relationshipCardholderList, "data-value", dataPMI), shortWait());
                break;
            case 2:
                //============Cardholder Name Randomly
                clickAndMoveToElementClickable(input_cardHolderName, mediumWait());
                sendKeysAndMoveToElementVisible(input_cardHolderName, String.valueOf(faker.number().randomNumber(4, true)), mediumWait());
                break;
            case 3:
                //============CardHolder DOB
                clickAndMoveToElementClickable(input_cardHolderDOB, mediumWait());
                sendKeysAndMoveToElementVisible(input_cardHolderDOB, String.valueOf(getRandomDate()), mediumWait());
                break;
            case 4:
                //============Insurance Payer Name Randomly
                clickWhileCondition(dropdown_insurancePayerName, "aria-expanded", "false", mediumWait());
                sendKeysAndMoveToElementVisible(dropdown_insurancePayerName, dataPMI, mediumWait());
                waitForElementVisibility(label_ShowResults, mediumWait());
                clickAndMoveToElementVisible(elementList_insurancePayerNameList.get(0), mediumWait());
                break;
            case 5:
                //============Insurance Plan Name Randomly
                clickWhileCondition(dropdown_insurancePlanName, "aria-expanded", "false", mediumWait());
                sendKeysAndMoveToElementVisible(dropdown_insurancePlanName, dataPMI, mediumWait());
                waitForElementVisibility(label_planNameShowResults, mediumWait());
                clickAndMoveToElementVisible(elementList_insurancePlanNameList.get(0), mediumWait());
                break;
            case 6:
                //============Insurance Phone Number Randomly
                clickAndMoveToElementClickable(input_insurancePhoneNumber, mediumWait());
                sendKeysAndMoveToElementVisible(input_insurancePhoneNumber, dataPMI, mediumWait());
                break;
            case 7:
                //============Insurance Group Number Randomly
                clickAndMoveToElementClickable(input_groupNumber, mediumWait());
                sendKeysAndMoveToElementVisible(input_groupNumber, dataPMI, mediumWait());
                break;
            case 8:
                //============Insurance Policy Number Randomly
                scrollToWebElementJS(input_policyNumber);
                clickAndMoveToElementClickable(input_policyNumber, mediumWait());
                sendKeysAndMoveToElementVisible(input_policyNumber, dataPMI, mediumWait());
                break;
            case 9:
                //============Insurance MemberID Number Randomly
                scrollToWebElementJS(input_memberID);
                clickAndMoveToElementClickable(input_memberID, mediumWait());
                sendKeysAndMoveToElementVisible(input_memberID, dataPMI, mediumWait());
                break;
        }
    }
}