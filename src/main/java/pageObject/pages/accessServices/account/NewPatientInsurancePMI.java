package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;
import java.util.List;

public class NewPatientInsurancePMI extends CommonFunctions {
    @FindBy(xpath = "//*[@name='SaveEdit']")
    private WebElement buttonSave;

    @FindBy(xpath = "//label[contains(text(),'Insurance Rank')]/..//*[@role='combobox']")
    private WebElement dropdownInsuranceRank;

    @FindBy(xpath = "//*[contains(text(),'Insurance Rank')]/..//*[@role='option'][@data-value][not(@data-value='')]")
    private List<WebElement> elementListInsuranceRankList;

    @FindBy(xpath = "//*[contains(text(),'Relationship to Cardholder')]/..//input[1]")
    private WebElement dropdownRelationshipCardholder;

    @FindBy(xpath = "//*[contains(text(),'Relationship to Cardholder')]/..//input[1]/following::*[@role='option'][@data-value][not(@data-value='')]")
    private List<WebElement> elementListRelationshipCardholderList;

    @FindBy(xpath = "//input[contains(@name,'_Cardholder_Name')]")
    private WebElement inputCardHolderName;

    @FindBy(xpath = "//input[contains(@name,'_Cardholder_DOB')]")
    private WebElement inputCardHolderDOB;

    @FindBy(xpath = "//*[contains(text(),'Insurance Payer Name')]/..//*[@placeholder='Search Accounts...']")
    private WebElement dropdownInsurancePayerName;

    @FindBy(xpath = "//*[contains(text(),'Insurance Payer Name')]/..//*[@role='listbox']/..//ul//*[@data-value]")
    private List<WebElement> elementListInsurancePayerNameList;

    @FindBy(xpath = "//*[contains(text(),'Insurance Plan Name')]/..//*[contains(@placeholder,'Search Payer')]")
    private WebElement dropdownInsurancePlanName;

    @FindBy(xpath = "//*[contains(text(),'Insurance Plan Name')]/..//*[@role='listbox']/..//ul//*[@data-value]")
    private List<WebElement> elementListInsurancePlanNameList;

    @FindBy(xpath = "//input[contains(@name,'_Insurance_Phone_Number')]")
    private WebElement inputInsurancePhoneNumber;

    @FindBy(xpath = "//input[contains(@name,'_Medical_Group_Number_')]")
    private WebElement inputGroupNumber;

    @FindBy(xpath = "//input[contains(@name,'_Cardholder_ID_')]")
    private WebElement inputMemberID;

    @FindBy(xpath = "//input[contains(@name,'_Policy_Number_')]")
    private WebElement inputPolicyNumber;

    @FindBy(xpath = "//span[contains(text(),'Show All Results for')]")
    private WebElement labelShowResults;

    @FindBy(xpath = "//*[contains(text(),'Insurance Plan Name')]/..//span[contains(text(),'Show All Results for')][1]")
    private WebElement labelPlanNameShowResults;

    Faker faker = new Faker();
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

    /**
     * This method is used to filter if the date shall be created randomly or not
     *
     * @param dataPMI it contains all the date related to fill the PMI form
     * @return a boolean value
     * @throws Exception
     */
    public boolean fillPMIForm(List<String> dataPMI) throws Exception {
        int dataCounter = 0;
        boolean statusOperation = false;
        try {
            for (String data : dataPMI) {
                if (data.trim().equalsIgnoreCase(Values.TXT_RANDOM)) {
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
            clickAndMoveToElementClickable(buttonSave, shortWait());
            statusOperation = true;
        } catch (Exception e) {
        }
        return statusOperation;
    }


    /**
     * It will create/select randomly all the data need it to create the PMI insurance type form
     *
     * @param dataPMI      it contains all the date related to fill the PMI form
     * @param dataPosition is a int number to identify which data from reord we need to create or select
     * @return a boolean value
     * @throws Exception
     */
    public boolean fillPMIFormRandomly(String dataPMI, int dataPosition) throws Exception {
        boolean statusOperation = false;
        try {
            switch (dataPosition) {
                case 0:
                    //============Insurance Rank Randomly
                    clickWhileCondition(dropdownInsuranceRank, Values.ATTRIBUTE_ARIAEXPANDED_VALUE, Values.ATTRIBUTE_FALSE_VALUE, mediumWait());
                    clickAndMoveToElementVisible(getRandomWebElementFromList(elementListInsuranceRankList, mediumWait()), shortWait());
                    statusOperation = true;
                    break;
                case 1:
                    //============Relationship to Cardholder Randomly
                    clickWhileCondition(dropdownRelationshipCardholder, Values.ATTRIBUTE_ARIAEXPANDED_VALUE, Values.ATTRIBUTE_FALSE_VALUE, mediumWait());
                    clickAndMoveToElementVisible(getRandomWebElementFromList(elementListRelationshipCardholderList, mediumWait()), shortWait());
                    statusOperation = true;
                    break;
                case 2:
                    //============Cardholder Name Randomly
                    clickAndMoveToElementClickable(inputCardHolderName, mediumWait());
                    sendKeysAndMoveToElementVisible(inputCardHolderName, String.valueOf(faker.name().firstName()), mediumWait());
                    statusOperation = true;
                    break;
                case 3:
                    //============CardHolder DOB
                    clickAndMoveToElementClickable(inputCardHolderDOB, mediumWait());
                    sendKeysAndMoveToElementVisible(inputCardHolderDOB, String.valueOf(getRandomDate(Values.DOB_DD_MM_YYYY)), mediumWait());
                    statusOperation = true;
                    break;
                case 4:
                    //============Insurance Payer Name Randomly
                    clickWhileCondition(dropdownInsurancePayerName, Values.ATTRIBUTE_ARIAEXPANDED_VALUE, Values.ATTRIBUTE_FALSE_VALUE, mediumWait());
                    sendKeysAndMoveToElementVisible(dropdownInsurancePayerName, faker.name().firstName(), mediumWait());
                    waitForElementVisibility(labelShowResults, mediumWait());
                    clickAndMoveToElementVisible(elementListInsurancePayerNameList.get(0), mediumWait());
                    statusOperation = true;
                    break;
                case 5:
                    //============Insurance Plan Name Randomly
                    clickWhileCondition(dropdownInsurancePlanName, Values.ATTRIBUTE_ARIAEXPANDED_VALUE, Values.ATTRIBUTE_FALSE_VALUE, mediumWait());
                    sendKeysAndMoveToElementVisible(dropdownInsurancePlanName, faker.name().firstName(), mediumWait());
                    waitForElementVisibility(labelPlanNameShowResults, mediumWait());
                    clickAndMoveToElementVisible(elementListInsurancePlanNameList.get(0), mediumWait());
                    statusOperation = true;
                    break;
                case 6:
                    //============Insurance Phone Number Randomly
                    clickAndMoveToElementClickable(inputInsurancePhoneNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(inputInsurancePhoneNumber, String.valueOf(faker.number().randomNumber(10, true)), mediumWait());
                    statusOperation = true;
                    break;
                case 7:
                    //============Insurance Group Number Randomly
                    clickAndMoveToElementClickable(inputGroupNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(inputGroupNumber, String.valueOf(faker.number().randomNumber(6, true)), mediumWait());
                    statusOperation = true;
                    break;
                case 8:
                    //============Insurance Policy Number Randomly
                    scrollToWebElementJS(inputPolicyNumber);
                    clickAndMoveToElementClickable(inputPolicyNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(inputPolicyNumber, String.valueOf(faker.number().randomNumber(5, true)), mediumWait());
                    statusOperation = true;
                    break;
                case 9:
                    //============Insurance MemberID Number Randomly
                    scrollToWebElementJS(inputMemberID);
                    clickAndMoveToElementClickable(inputMemberID, mediumWait());
                    sendKeysAndMoveToElementVisible(inputMemberID, String.valueOf(faker.number().randomNumber(4, true)), mediumWait());
                    statusOperation = true;
                    break;
                default:
                    logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                    break;
            }
        } catch (Exception e) {
        }
        return statusOperation;
    }


    /**
     * It will fill the PMI form with the data provided in the dataPMI record (specific data)
     *
     * @param dataPMI      it contains all the date related to fill the PMI form
     * @param dataPosition is a int number to identify which data from reord we need to create or select
     * @return
     * @throws Exception
     */
    public boolean fillPMIForm(String dataPMI, int dataPosition) throws Exception {
        boolean statusOperation = false;
        try {
            switch (dataPosition) {
                case 0:
                    //============Insurance Rank Randomly
                    clickWhileCondition(dropdownInsuranceRank, Values.ATTRIBUTE_ARIAEXPANDED_VALUE, Values.ATTRIBUTE_FALSE_VALUE, mediumWait());
                    getWebElementByAttributeFromList(elementListInsuranceRankList, Values.ATTRIBUTE_DATAVALUE_VALUE, dataPMI);
                    clickAndMoveToElementVisible(getWebElementByAttributeFromList(elementListInsuranceRankList, Values.ATTRIBUTE_DATAVALUE_VALUE, dataPMI), shortWait());
                    statusOperation = true;
                    break;
                case 1:
                    //============Relationship to Cardholder Randomly
                    clickWhileCondition(dropdownRelationshipCardholder, Values.ATTRIBUTE_ARIAEXPANDED_VALUE, Values.ATTRIBUTE_FALSE_VALUE, mediumWait());
                    clickAndMoveToElementVisible(getWebElementByAttributeFromList(elementListRelationshipCardholderList, Values.ATTRIBUTE_DATAVALUE_VALUE, dataPMI), shortWait());
                    statusOperation = true;
                    break;
                case 2:
                    //============Cardholder Name Randomly
                    clickAndMoveToElementClickable(inputCardHolderName, mediumWait());
                    sendKeysAndMoveToElementVisible(inputCardHolderName, String.valueOf(faker.number().randomNumber(4, true)), mediumWait());
                    statusOperation = true;
                    break;
                case 3:
                    //============SPECIFIC CardHolder DOB
                    clickAndMoveToElementClickable(inputCardHolderDOB, mediumWait());
                    sendKeysAndMoveToElementVisible(inputCardHolderDOB,dataPMI, mediumWait());
                    statusOperation = true;
                    break;
                case 4:
                    //============Insurance Payer Name Randomly
                    clickWhileCondition(dropdownInsurancePayerName, Values.ATTRIBUTE_ARIAEXPANDED_VALUE, Values.ATTRIBUTE_FALSE_VALUE, mediumWait());
                    sendKeysAndMoveToElementVisible(dropdownInsurancePayerName, dataPMI, mediumWait());
                    waitForElementVisibility(labelShowResults, mediumWait());
                    clickAndMoveToElementVisible(elementListInsurancePayerNameList.get(0), mediumWait());
                    statusOperation = true;
                    break;
                case 5:
                    //============Insurance Plan Name Randomly
                    clickWhileCondition(dropdownInsurancePlanName, Values.ATTRIBUTE_ARIAEXPANDED_VALUE, Values.ATTRIBUTE_FALSE_VALUE, mediumWait());
                    sendKeysAndMoveToElementVisible(dropdownInsurancePlanName, dataPMI, mediumWait());
                    waitForElementVisibility(labelPlanNameShowResults, mediumWait());
                    clickAndMoveToElementVisible(elementListInsurancePlanNameList.get(0), mediumWait());
                    statusOperation = true;
                    break;
                case 6:
                    //============Insurance Phone Number Randomly
                    clickAndMoveToElementClickable(inputInsurancePhoneNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(inputInsurancePhoneNumber, dataPMI, mediumWait());
                    statusOperation = true;
                    break;
                case 7:
                    //============Insurance Group Number Randomly
                    clickAndMoveToElementClickable(inputGroupNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(inputGroupNumber, dataPMI, mediumWait());
                    statusOperation = true;
                    break;
                case 8:
                    //============Insurance Policy Number Randomly
                    scrollToWebElementJS(inputPolicyNumber);
                    clickAndMoveToElementClickable(inputPolicyNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(inputPolicyNumber, dataPMI, mediumWait());
                    statusOperation = true;
                    break;
                case 9:
                    //============Insurance MemberID Number Randomly
                    scrollToWebElementJS(inputMemberID);
                    clickAndMoveToElementClickable(inputMemberID, mediumWait());
                    sendKeysAndMoveToElementVisible(inputMemberID, dataPMI, mediumWait());
                    statusOperation = true;
                    break;
                default:
                    logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                    break;
            }
        } catch (Exception e) {
        }
        return statusOperation;
    }
}