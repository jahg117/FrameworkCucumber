package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.util.HashMap;
import java.util.List;

public class NewEmployeeWizardPage extends CommonFunctions {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "//*[contains(text(),'Sub Type')]/following::a[@class='select'][1]")
    private WebElement dropdown_subType;

    @FindBy(xpath = "//*[contains(text(),'Sub Type')]/following::ul//li//a[not (@role='tab')][not (@title='--None--')]")
    private List<WebElement> dropdown_subTypeList;

    @FindBy(xpath = "//a[@data-label='System Information']")
    private WebElement label_systemInfo;

    @FindBy(xpath = "//*[contains(text(),'Account ID')]//..")
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
     * @author J.Ruano
     */
    public boolean isNewEmployeeWizardFormDisplayed() {
        return waitForElementVisibility(dropdown_subType, 30);
    }

    /**
     * /**
     * It validates if the new Account will be created completely random or specific data at columns in ConfigurableCustomerLookup.feature tables, can contains
     * specific data or "RND" if that columns requires random data, or "N_A" if it does not need to be filled
     *
     * @param identifier   it is used to add a label at the begining of the first name, use to identify the records created by Automation, it can have any value
     * @param firstName    it contains the first name for the new Employee account (required)
     * @param middleName   it contains the middle name for the new Employee account (optional)
     * @param lastName     it contains the last name for the new Employee account (required)
     * @param randomRecord value used to know if the account will be fill with random data "RND" when it is random
     * @throws Exception
     * @author J.Ruano
     */
    public void validateAndCreateEmployee(String identifier, String firstName, String middleName, String lastName, String randomRecord) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("EmployeeRecord");
        HashMap<String, String> employeeDetailsStoreData = new HashMap<String, String>();

        boolean allDataRND = false;
        if (randomRecord.trim().equalsIgnoreCase("RND")) {
            employeeDetailsStoreData = fullEmployeeFormRND(identifier);
        } else {
            employeeDetailsStoreData = hibrydEmployeeForm(identifier, firstName, middleName, lastName);
            fillingHybridEmployeeForm(employeeDetailsStoreData);
        }
        clickSaveButton();
        employeeDetailsStoreData.put("externalID", getExternalID());
        jsonFile.storeDataIntoJSON(employeeDetailsStoreData);
    }

    /**
     * This method is to create a new Employee account completely with random data
     *
     * @param identifier it is used to add a label at the beginning of the first name, use to identify the records created by Automation, it can have any value
     * @return it returns a HashMap with string data used to fill the Employee form
     * @throws Exception
     * @author J.Ruano
     */
    public HashMap<String, String> fullEmployeeFormRND(String identifier) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("statesUSCodes");
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        Faker faker = new Faker();
        //============Storing Data Into employeeDetails
        HashMap<String, String> employeeDetails = new HashMap<String, String>();
        employeeDetails.put("firstName", identifier + faker.name().firstName());
        employeeDetails.put("middleName", faker.name().firstName());
        employeeDetails.put("lastName", faker.name().lastName() + underScore + generateTimeStamp(dateFormat));


        //============Populating The Employee Data
        clickAndMoveToElementVisible(dropdown_subType, 10);
        clickAndMoveToElementClickable(getRandomWebElementIgnoreText(dropdown_subTypeList, "--None--"), 10);
        clickAndMoveToElementVisible(input_firstName, 10);
        input_firstName.clear();
        sendKeysAndMoveToElementVisible(input_firstName, employeeDetails.get("firstName"), 10);
        clickAndMoveToElementVisible(input_middleName, 10);
        input_middleName.clear();
        sendKeysAndMoveToElementVisible(input_middleName, employeeDetails.get("middleName"), 10);
        clickAndMoveToElementVisible(input_lastName, 10);
        input_lastName.clear();
        sendKeysAndMoveToElementVisible(input_lastName, employeeDetails.get("lastName"), 10);
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
     * @throws Exception
     * @author J.Ruano
     */
    private HashMap<String, String> hibrydEmployeeForm(String identifier, String firstName, String middleName, String lastName) throws Exception {
        Faker faker = new Faker();
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        //============Storing Data Into employeeDetails
        HashMap<String, String> employeeDetails = new HashMap<String, String>();
        employeeDetails.put("firstName", identifier + hibrydEmployeeFormFilter(firstName, "firstName"));
        employeeDetails.put("middleName", hibrydEmployeeFormFilter(middleName, "middleName"));
        employeeDetails.put("lastName", hibrydEmployeeFormFilter(lastName, "lastName") + underScore + generateTimeStamp(dateFormat));
        return employeeDetails;
    }

    /**
     * This method is to check anc create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param employeeValue it will contains the data for each field to be populated
     * @param nameOfField   it is used to know which field will be evaluated
     * @return it will return the valued used to fill the Employee form
     * @throws Exception
     * @author J.Ruano
     */
    public String hibrydEmployeeFormFilter(String employeeValue, String nameOfField) throws Exception {
        WebElement backUpWElement = null;
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        String notApply = "N_A";
        String randomOption = "RND";
        String returnedValue = "";
        Faker faker = new Faker();
        switch (nameOfField) {
            case "firstName":
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.name().firstName();
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            case "middleName":
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.funnyName().name();
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            case "lastName":
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.name().lastName() + underScore + generateTimeStamp(dateFormat);
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;
        }
        return returnedValue;
    }

    /**
     * It is used to fulfill the Employee New Account form, validating which elements are going to be fill
     *
     * @param employeeDetails it contains the list of all the fields with their respective data
     * @throws Exception
     * @author J.Ruano
     */
    public void fillingHybridEmployeeForm(HashMap<String, String> employeeDetails) throws Exception {
        String notApply = "N_A";
        Faker faker = new Faker();
        clickAndMoveToElementVisible(dropdown_subType, 10);
        clickAndMoveToElementClickable(getRandomWebElementIgnoreText(dropdown_subTypeList, "--None--"), 10);

        clickAndMoveToElementVisible(input_firstName, 10);
        input_firstName.clear();
        sendKeysAndMoveToElementVisible(input_firstName, employeeDetails.get("firstName"), 10);

        if (!employeeDetails.get("middleName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_middleName, employeeDetails.get("middleName"), 10);
        }
        clickAndMoveToElementVisible(input_lastName, 10);
        input_lastName.clear();
        sendKeysAndMoveToElementVisible(input_lastName, employeeDetails.get("lastName"), 10);
    }

    /**
     * It retrieves the Account ID generated for the new Employee account created
     *
     * @return the Account ID added to the new Employee account
     * @throws Exception
     */
    public String getExternalID() throws Exception {
        waitForElementVisibility(label_systemInfo, 20);
        clickMethod(label_systemInfo);
        if (waitForElementClickable(linkButton_lastModifiedBy, 10)) {
            scrollMethodToWebElement(linkButton_lastModifiedBy);
        } else {
            waitForElementVisibility(linkButton_lastModifiedBy, 10);
            scrollMethodToWebElement(linkButton_lastModifiedBy);
        }
        return label_externalID.getText().replace("Account ID", "").trim();
    }

    /**
     * It clicks the save button after all the data required for the Employee account is populated
     *
     * @throws Exception
     */
    public void clickSaveButton() throws Exception {
        clickAndMoveToElementClickable(button_saveAccount, 10);
    }
}