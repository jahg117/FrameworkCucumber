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

public class NewPatientInsurancePBM extends CommonFunctions {
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

    @FindBy(xpath = "//input[contains(@name,'_BIN_Number')]")
    private WebElement input_binNumber;

    @FindBy(xpath = "//input[contains(@name,'_PCN')]")
    private WebElement input_pcnNumber;

    @FindBy(xpath = "//span[contains(text(),'Show All Results for')]")
    private WebElement label_ShowResults;

    @FindBy(xpath = "//*[contains(text(),'Insurance Plan Name')]/..//span[contains(text(),'Show All Results for')][1]")
    private WebElement label_planNameShowResults;

    protected FileReading fileReading = new FileReading();
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

    /**
     * This method is used to filter if the date shall be created randomly or not
     *
     * @param dataPBM it contains all the date related to fill the PBM form
     * @throws Exception
     */
    public void fillPBMForm(List<String> dataPBM) throws Exception {
        int dataCounter = 0;
        for (String data : dataPBM) {
            if (data.trim().equalsIgnoreCase("RND")) {
                fillPBMFormRandomly(data, dataCounter);
            } else {
                if (data.trim().equalsIgnoreCase("N_A")) {
                    logger.info("The Data At Position : " + dataCounter + "Is Not Required");
                } else {
                    fillPBMForm(data, dataCounter);
                }
            }
            dataCounter++;
        }
        clickAndMoveToElementClickable(button_save, shortWait());
    }

    /**
     * It will create/select randomly all the data need it to create the PBM insurance type form
     *
     * @param dataPBM      it contains all the date related to fill the PBM form
     * @param dataPosition is a int number to identify which data from reord we need to create or select
     * @throws Exception
     */
    public boolean fillPBMFormRandomly(String dataPBM, int dataPosition) throws Exception {
        boolean statusOperation = false;
        try {
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
                    clickAndMoveToElementClickable(input_policyNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(input_policyNumber, String.valueOf(faker.number().randomNumber(5, true)), mediumWait());
                    break;
                case 9:
                    //============Insurance MemberID Number Randomly
                    scrollToWebElementJS(input_memberID);
                    clickAndMoveToElementClickable(input_memberID, mediumWait());
                    sendKeysAndMoveToElementVisible(input_memberID, String.valueOf(faker.number().randomNumber(4, true)), mediumWait());
                    break;

                case 10:
                    //============Insurance BIN Number Randomly
                    scrollToWebElementJS(input_binNumber);
                    clickAndMoveToElementClickable(input_binNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(input_binNumber, String.valueOf(faker.number().randomNumber(6, true)), mediumWait());
                    break;

                case 11:
                    //============Insurance PCN Number Randomly
                    scrollToWebElementJS(input_pcnNumber);
                    clickAndMoveToElementClickable(input_pcnNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(input_pcnNumber, String.valueOf(faker.number().randomNumber(6, true)), mediumWait());
                    break;
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("fillPBMFormRandomly")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "fillPBMFormRandomly");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), dataPBM, dataPosition);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    /**
     * It will fill the PBM form with the data provided in the dataPBM record (specific data)
     *
     * @param dataPBM      it contains all the date related to fill the PBM form
     * @param dataPosition is a int number to identify which data from reord we need to create or select
     * @throws Exception
     */
    public boolean fillPBMForm(String dataPBM, int dataPosition) throws Exception {
        boolean statusOperation = false;
        try {
            switch (dataPosition) {
                case 0:
                    //============Insurance Rank
                    clickWhileCondition(dropdown_insuranceRank, "aria-expanded", "false", mediumWait());
                    getWebElementByAttributeFromList(elementList_insuranceRankList, "data-value", dataPBM);
                    clickAndMoveToElementVisible(getWebElementByAttributeFromList(elementList_insuranceRankList, "data-value", dataPBM), shortWait());
                    break;
                case 1:
                    //============Relationship to Cardholder
                    clickWhileCondition(dropdown_relationshipCardholder, "aria-expanded", "false", mediumWait());
                    clickAndMoveToElementVisible(getWebElementByAttributeFromList(elementList_relationshipCardholderList, "data-value", dataPBM), shortWait());
                    break;
                case 2:
                    //============Cardholder Name
                    clickAndMoveToElementClickable(input_cardHolderName, mediumWait());
                    sendKeysAndMoveToElementVisible(input_cardHolderName, dataPBM, mediumWait());
                    break;
                case 3:
                    //============CardHolder DOB
                    clickAndMoveToElementClickable(input_cardHolderDOB, mediumWait());
                    sendKeysAndMoveToElementVisible(input_cardHolderDOB, dataPBM, mediumWait());
                    break;
                case 4:
                    //============Insurance Payer Name
                    clickWhileCondition(dropdown_insurancePayerName, "aria-expanded", "false", mediumWait());
                    sendKeysAndMoveToElementVisible(dropdown_insurancePayerName, dataPBM, mediumWait());
                    waitForElementVisibility(label_ShowResults, mediumWait());
                    clickAndMoveToElementVisible(elementList_insurancePayerNameList.get(0), mediumWait());
                    break;
                case 5:
                    //============Insurance Plan Name
                    clickWhileCondition(dropdown_insurancePlanName, "aria-expanded", "false", mediumWait());
                    sendKeysAndMoveToElementVisible(dropdown_insurancePlanName, dataPBM, mediumWait());
                    waitForElementVisibility(label_planNameShowResults, mediumWait());
                    clickAndMoveToElementVisible(elementList_insurancePlanNameList.get(0), mediumWait());
                    break;
                case 6:
                    //============Insurance Phone Number
                    clickAndMoveToElementClickable(input_insurancePhoneNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(input_insurancePhoneNumber, dataPBM, mediumWait());
                    break;
                case 7:
                    //============Insurance Group Number
                    clickAndMoveToElementClickable(input_groupNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(input_groupNumber, dataPBM, mediumWait());
                    break;
                case 8:
                    //============Insurance Policy Number
                    clickAndMoveToElementClickable(input_policyNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(input_policyNumber, dataPBM, mediumWait());
                    break;
                case 9:
                    //============Insurance MemberID Number
                    scrollToWebElementJS(input_memberID);
                    clickAndMoveToElementClickable(input_memberID, mediumWait());
                    sendKeysAndMoveToElementVisible(input_memberID, dataPBM, mediumWait());
                    break;
                case 10:
                    //============Insurance BIN Number
                    scrollToWebElementJS(input_binNumber);
                    clickAndMoveToElementClickable(input_binNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(input_binNumber, dataPBM, mediumWait());
                    break;
                case 11:
                    //============Insurance PCN Number
                    scrollToWebElementJS(input_pcnNumber);
                    clickAndMoveToElementClickable(input_pcnNumber, mediumWait());
                    sendKeysAndMoveToElementVisible(input_pcnNumber, dataPBM, mediumWait());
                    break;
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("fillPBMFormRandomly")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "fillPBMFormRandomly");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), dataPBM, dataPosition);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }
}