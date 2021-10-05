package pageObject.pages.accessServices.wizard;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;
import utils.Values;

import java.util.HashMap;
import java.util.List;

public class NewEmployeeWizardPage extends CommonFunctions {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CommonFunctions.class);
    @FindBy(xpath = "//*[contains(text(),'Sub Type')]/..//*[@role='none']")
    private WebElement dropdown_subType;

    @FindBy(xpath = "//*[contains(text(),'Sub Type')]/..//span[@title][not (@title='--None--')]")
    private List<WebElement> dropdown_subTypeList;

    @FindBy(xpath = "//a[@data-label='System Information']")
    private WebElement label_systemInfo;

    @FindBy(xpath = "//a[@data-label='Account History']")
    private WebElement label_accountHistory;

    @FindBy(xpath = "(//*[contains(text(),'Account ID')]//..)[1]")
    private WebElement label_externalID;

    @FindBy(xpath = "//span[normalize-space(text())='Logged out']")
    private WebElement button_loggedOut;

    @FindBy(xpath = "//*[contains(text(),\"Last Modified By\")]/following::a[1]")
    private WebElement linkButton_lastModifiedBy;

    @FindBy(xpath = "//input[@placeholder='First Name']")
    private WebElement input_firstName;

    @FindBy(xpath = "//input[@placeholder='Middle Name']")
    private WebElement input_middleName;

    @FindBy(xpath = "//input[@placeholder='Last Name']")
    private WebElement input_lastName;

    @FindBy(xpath = "//*[@title='Save']")
    private WebElement button_saveAccount;

    /**
     * This method is use only to ensure that the element has been displayed correctly, more use to know if a page is ready
     *
     * @return it returns a boolean value in case the form has been displayed
     * @throws Exception Selenium exception
     * @author J.Ruano
     */
    public boolean isNewEmployeeWizardFormDisplayed() throws Exception {
        return waitForElementVisibility(dropdown_subType, longWait());
    }

    /**
     *
     * It validates if the new Account will be created completely random or specific data at columns in ConfigurableCustomerLookup.feature tables, can contains
     * specific data or "RND" if that columns requires random data, or "N_A" if it does not need to be filled
     *
     * @param identifier   it is used to add a label at the begining of the first name, use to identify the records created by Automation, it can have any value
     * @param firstName    it contains the first name for the new Employee account (required)
     * @param middleName   it contains the middle name for the new Employee account (optional)
     * @param lastName     it contains the last name for the new Employee account (required)
     * @param randomRecord value used to know if the account will be fill with random data "RND" when it is random
     * @throws Exception Selenium exception
     * @author J.Ruano
     */
    public void validateAndCreateEmployee(String identifier, String firstName, String middleName, String lastName, String randomRecord) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(Values.KEYVALUE_EMPLOYEERECORD);
        HashMap<String, String> employeeDetailsStoreData;

        boolean allDataRND = false;
        if (randomRecord.trim().equalsIgnoreCase(Values.TXT_RANDOM)) {
            employeeDetailsStoreData = fullEmployeeFormRND(identifier);
        } else {
            employeeDetailsStoreData = hibrydEmployeeForm(identifier, firstName, middleName, lastName);
            fillingHybridEmployeeForm(employeeDetailsStoreData);
        }
        scrollMethodToWebElement(button_saveAccount);
        clickSaveButton(button_saveAccount);
        //employeeDetailsStoreData.put("externalID", getExternalID());DO NOT DELETE THIS LINE WAITING FOR CONFIRMATION IF ID WILL BE CREATED
        jsonFile.storeDataIntoJSON(employeeDetailsStoreData);
    }

    /**
     * This method is to create a new Employee account completely with random data
     *
     * @param identifier it is used to add a label at the beginning of the first name, use to identify the records created by Automation, it can have any value
     * @return it returns a HashMap with string data used to fill the Employee form
     * @throws Exception Selenium exception
     * @author J.Ruano
     */
    public HashMap<String, String> fullEmployeeFormRND(String identifier) throws Exception {
        Faker faker = new Faker();

        //============Storing Data Into employeeDetails
        HashMap<String, String> employeeDetails = new HashMap<>();
        employeeDetails.put(Values.KEYVALUE_FIRSTNAME, identifier + faker.name().firstName());
        employeeDetails.put(Values.KEYVALUE_MIDDLENAME, faker.name().firstName());
        employeeDetails.put(Values.KEYVALUE_LASTNAME, faker.name().lastName() + Values.CHAR_UNDERSCORE + generateTimeStamp(Values.DATEFORMAT_MMM_DD_HH_MM));

        //============Populating The Employee Data
        clickAndMoveToElementVisible(dropdown_subType, mediumWait());
        clickAndMoveToElementClickable(getRandomWebElementIgnoreText(dropdown_subTypeList, Values.VALUESTOSEARCH_NONE), mediumWait());
        clickAndMoveToElementVisible(input_firstName, mediumWait());
        input_firstName.clear();
        sendKeysAndMoveToElementVisible(input_firstName, employeeDetails.get(Values.KEYVALUE_FIRSTNAME), mediumWait());
        clickAndMoveToElementVisible(input_middleName, mediumWait());
        input_middleName.clear();
        sendKeysAndMoveToElementVisible(input_middleName, employeeDetails.get(Values.KEYVALUE_MIDDLENAME), mediumWait());
        clickAndMoveToElementVisible(input_lastName, mediumWait());
        input_lastName.clear();
        sendKeysAndMoveToElementVisible(input_lastName, employeeDetails.get(Values.KEYVALUE_LASTNAME), mediumWait());
        return employeeDetails;
    }


    /**
     * This method is used to prepare the data for the Employee account creation, it handle if the data will be generated randomly or it will contains an specific
     * data from the ConfigurableCustomerLookup feature
     *
     * @param identifier it is used to add a label at the begining of the first name, use to identify the records created by Automation, it can have any value
     * @param firstName  it contains the first name for the new Employee account (required)
     * @param middleName it contains the middle name for the new Employee account (optional)
     * @param lastName   it contains the last name for the new Employee account (required)
     * @return it returns a HashMap with string data used to fill the Employee form
     * @throws Exception Selenium exception
     * @author J.Ruano
     */
    private HashMap<String, String> hibrydEmployeeForm(String identifier, String firstName, String middleName, String lastName) throws Exception {

        //============Storing Data Into employeeDetails
        HashMap<String, String> employeeDetails = new HashMap<>();
        employeeDetails.put(Values.KEYVALUE_FIRSTNAME, identifier + hibrydEmployeeFormFilter(firstName, Values.KEYVALUE_FIRSTNAME));
        employeeDetails.put(Values.KEYVALUE_MIDDLENAME, hibrydEmployeeFormFilter(middleName, Values.KEYVALUE_MIDDLENAME));
        employeeDetails.put(Values.KEYVALUE_LASTNAME, hibrydEmployeeFormFilter(lastName, Values.KEYVALUE_LASTNAME) + Values.CHAR_UNDERSCORE + generateTimeStamp(Values.DATEFORMAT_MMM_DD_HH_MM));
        return employeeDetails;
    }

    /**
     * This method is to check anc create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param employeeValue it will contains the data for each field to be populated
     * @param nameOfField   it is used to know which field will be evaluated
     * @return it will return the valued used to fill the Employee form
     * @throws Exception Selenium exception
     * @author J.Ruano
     */
    public String hibrydEmployeeFormFilter(String employeeValue, String nameOfField) throws Exception {
        String returnedValue = "";
        Faker faker = new Faker();
        switch (nameOfField) {
            case Values.KEYVALUE_FIRSTNAME:
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(Values.TXT_RANDOM)) {
                    returnedValue = faker.name().firstName();
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            case Values.KEYVALUE_MIDDLENAME:
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(Values.TXT_RANDOM)) {
                    returnedValue = faker.funnyName().name();
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            case Values.KEYVALUE_LASTNAME:
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(Values.TXT_RANDOM)) {
                    returnedValue = faker.name().lastName() + Values.CHAR_UNDERSCORE + generateTimeStamp(Values.DATEFORMAT_MMM_DD_HH_MM);
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            default:
                logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                break;
        }
        return returnedValue;
    }

    /**
     * It is used to fulfill the Employee New Account form, validating which elements are going to be fill
     *
     * @param employeeDetails it contains the list of all the fields with their respective data
     * @throws Exception Selenium exception
     * @author J.Ruano
     */
    public void fillingHybridEmployeeForm(HashMap<String, String> employeeDetails) throws Exception {
        clickAndMoveToElementVisible(dropdown_subType, mediumWait());
        clickAndMoveToElementClickable(getRandomWebElementIgnoreText(dropdown_subTypeList, Values.VALUESTOSEARCH_NONE), mediumWait());

        clickAndMoveToElementVisible(input_firstName, mediumWait());
        input_firstName.clear();
        sendKeysAndMoveToElementVisible(input_firstName, employeeDetails.get(Values.KEYVALUE_FIRSTNAME), mediumWait());

        if (!employeeDetails.get(Values.KEYVALUE_MIDDLENAME).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(input_middleName, employeeDetails.get(Values.KEYVALUE_MIDDLENAME), mediumWait());
        }
        clickAndMoveToElementVisible(input_lastName, mediumWait());
        input_lastName.clear();
        sendKeysAndMoveToElementVisible(input_lastName, employeeDetails.get(Values.KEYVALUE_LASTNAME), mediumWait());
    }

    /**
     * It retrieves the Account ID generated for the new Employee account created
     *
     * @return the Account ID added to the new Employee account
     * @throws Exception Selenium exception
     */
    public String getExternalID() throws Exception {
        waitForElementVisibility(label_systemInfo, longWait());
        clickMethod(label_systemInfo);
        if (waitForElementClickable(linkButton_lastModifiedBy, mediumWait())) {
            scrollMethodToWebElement(linkButton_lastModifiedBy);
        } else {
            waitForElementVisibility(linkButton_lastModifiedBy, mediumWait());
            scrollMethodToWebElement(linkButton_lastModifiedBy);
        }
        clickMethod(label_accountHistory);
        clickAndMoveToElementClickable(label_systemInfo, shortWait());
        waitForElementPresenceBy(Values.BYPATH_AZID, shortWait());
        scrollToElementByCoordinates(label_externalID);
        return label_externalID.getText().replace(Values.TXT_ACCOUNTID,Values.REPLACETO_EMPTY ).trim();
    }

    /**
     * It clicks the save button after all the data required for the Employee account is populated
     *
     * @throws Exception Selenium exception
     */
    public void clickSaveButton() throws Exception {
        clickAndMoveToElementClickable(button_saveAccount, mediumWait());
    }
}