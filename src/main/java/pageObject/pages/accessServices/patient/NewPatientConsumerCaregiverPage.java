package pageObject.pages.accessServices.patient;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.JsonFiles;
import utils.Values;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

public class NewPatientConsumerCaregiverPage extends CommonFunctions {

    @FindBy(xpath = "//*[@data-component-id='ACS_PatientWizardParentComponent']")
    private WebElement form_patientConsumerCaregiver;

    @FindBy(xpath = "//select[@data-name='salutation']")
    private WebElement dropdown_prefix;

    @FindBy(xpath = "//input[@data-name='first']")
    private WebElement input_firstName;

    @FindBy(xpath = "//input[@data-name='last']")
    private WebElement input_lastName;

    @FindBy(xpath = "//input[@data-name='pname']")
    private WebElement input_informalName;

    @FindBy(xpath = "//input[@data-name='dob']")
    private WebElement input_dateOfBirth;

    @FindBy(xpath = "//input[@data-name='email']")
    private WebElement input_emailAddress;

    @FindBy(xpath = "//input[@data-name='Zip']")
    private WebElement input_zipCode;

    @FindBy(xpath = "//input[@data-name='number']")
    private WebElement input_phoneNumber;

    @FindBy(xpath = "//input[@placeholder='Search Accounts...']")
    private WebElement input_searchAccounts;

    @FindBy(xpath = "//input[@placeholder='Search Places']")
    private WebElement input_searchPlaces;

    @FindBy(xpath = "//input[@data-name='street1']")
    private WebElement input_addressLine1;

    @FindBy(xpath = "//input[@data-name='city']")
    private WebElement input_city;

    @FindBy(xpath = "//div[@role='listbox']//li")
    private List<WebElement> label_searchOptions;

    @FindBy(xpath = "//*[contains(text(),'Email Type')]/following::*[@name='optionSelect']")
    private WebElement dropdown_emailType;

    @FindBy(xpath = "//input[@placeholder='Select an Option']")
    private WebElement dropdown_phoneType;

    @FindBy(xpath = "//footer[@class='slds-modal__footer']//button[@type='submit']")
    private WebElement button_saveAccount;

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

    public boolean isConsumerPatientCaregiverFormDisplayed() throws Exception {
        boolean statusOperation = false;
        try {
            statusOperation = waitForElementVisibility(form_patientConsumerCaregiver, longWait());
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("isConsumerPatientCaregiverFormDisplayed")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "isConsumerPatientCaregiverFormDisplayed");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public HashMap<String, String> fillPatientConsumerCaregiverForm() throws Exception {
        Faker faker = new Faker();
        JsonFiles jsonFiles = new JsonFiles();
        jsonFiles.setFileName("zipCode");
        HashMap<String, String> patientDetails = new HashMap<String, String>();
        try {
            patientDetails.put("firstName", faker.name().firstName());
            patientDetails.put("lastName", faker.name().lastName() + "_Automation");
            patientDetails.put("address", faker.address().streetName());
            patientDetails.put("city", faker.address().cityName());
            patientDetails.put("phoneNumber", faker.phoneNumber().cellPhone().replace(".", "").replace("-", ""));
            patientDetails.put("date", getRandomDate());
            patientDetails.put("zipcode", jsonFiles.getRandomFieldArray("zip"));
            waitForElementClickable(dropdown_prefix, mediumWait());
            input_firstName.clear();
            clickAndMoveToElementClickable(input_firstName, mediumWait());
            sendKeysAndMoveToElementClickable(input_firstName, patientDetails.get("firstName"), mediumWait());
            sendKeysAndMoveToElementClickable(input_lastName, patientDetails.get("lastName"), mediumWait());
            String randomDate = patientDetails.get("date").replace("/", "");
            clickElementVisible(input_informalName, shortWait());
            sendKeysByActions(Keys.TAB.toString());
            sendKeysByActions(randomDate);
            scrollToWebElementJS(input_searchAccounts);
            sendKeysElementVisible(input_phoneNumber, patientDetails.get("phoneNumber"), mediumWait());
            scrollToWebElementJS(input_searchPlaces);
            sendKeysElementVisible(input_addressLine1, patientDetails.get("address"), mediumWait());
            sendKeysElementVisible(input_city, patientDetails.get("city"), mediumWait());
            scrollToWebElementJS(input_emailAddress);
            sendKeysAndMoveToElementVisible(input_emailAddress, patientDetails.get("firstName") + "@astrazeneca.com", mediumWait());
            selectAndMoveDropDownVisibleRandomOption(dropdown_emailType, mediumWait());
            sendKeysAndMoveToElementVisible(input_zipCode, patientDetails.get("zipcode"), mediumWait());
            if (!getWebElementAttribute(input_firstName, "value").equalsIgnoreCase(patientDetails.get("firstName"))) {
                input_firstName.clear();
                sendKeysAndMoveToElementClickable(input_firstName, patientDetails.get("firstName"), mediumWait());
            }
            if (!getWebElementAttribute(input_lastName, "value").equalsIgnoreCase(patientDetails.get("lastName"))) {
                input_lastName.clear();
                sendKeysAndMoveToElementClickable(input_lastName, patientDetails.get("lastName"), mediumWait());
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("fillPatientConsumerCaregiverForm")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "fillPatientConsumerCaregiverForm");
                        patientDetails = (HashMap<String, String>) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return patientDetails;
    }

    public HashMap<String, String> fillPatientConsumerCaregiverForm(HashMap<String, String> patientForm) throws Exception {
        Faker faker = new Faker();
        By phoneType = By.xpath("//div[contains(@class,'slds-dropdown_left slds-dropdown')]//*[@data-value='"+patientForm.get("phoneType")+"']");
        HashMap<String, String> patientDetails = new HashMap<String, String>();
        patientDetails.put("firstName", patientForm.get("name")+""+faker.name().firstName());
        patientDetails.put("lastName", faker.name().lastName());
        patientDetails.put("address", faker.address().streetName());
        patientDetails.put("city", faker.address().cityName());
        patientDetails.put("phoneNumber", patientForm.get("fax"));
        patientDetails.put("date", getRandomDate());
        patientDetails.put("zipcode", patientForm.get("zipcode"));
        waitForElementClickable(dropdown_prefix, 20);
        input_firstName.clear();
        clickAndMoveToElementClickable(input_firstName, 10);
        sendKeysAndMoveToElementClickable(input_firstName, patientDetails.get("firstName"), 10);
        sendKeysAndMoveToElementClickable(input_lastName, patientDetails.get("lastName"), 10);
        String randomDate = patientDetails.get("date").replace("/", "");
        clickElementVisible(input_informalName, 5);
        sendKeysByActions(Keys.TAB.toString());
        sendKeysByActions(randomDate);
        scrollToWebElementJS(input_searchAccounts);
        sendKeysElementVisible(input_phoneNumber, patientDetails.get("phoneNumber"), 10);
        clickElementClickable(dropdown_phoneType, 10);
        clickElementClickable(phoneType,10);
        scrollToWebElementJS(input_searchPlaces);
        sendKeysElementVisible(input_addressLine1, patientDetails.get("address"), 10);
        sendKeysElementVisible(input_city, patientDetails.get("city"), 10);
        scrollToWebElementJS(input_emailAddress);
        sendKeysAndMoveToElementVisible(input_emailAddress, patientDetails.get("firstName") + "@astrazeneca.com", 10);
        selectAndMoveDropDownVisibleRandomOption(dropdown_emailType, 10);
        sendKeysAndMoveToElementVisible(input_zipCode, patientDetails.get("zipcode"), 10);
        if (!getWebElementAttribute(input_firstName, "value").equalsIgnoreCase(patientDetails.get("firstName"))) {
            input_firstName.clear();
            sendKeysAndMoveToElementClickable(input_firstName, patientDetails.get("firstName"), 10);
        }
        if (!getWebElementAttribute(input_lastName, "value").equalsIgnoreCase(patientDetails.get("lastName"))) {
            input_lastName.clear();
            sendKeysAndMoveToElementClickable(input_lastName, patientDetails.get("lastName"), 10);
        }
        return patientDetails;
    }

    public boolean clickSaveButton() throws Exception {
        boolean statusOperation = false;
        try {
            waitForElementVisibility(button_saveAccount, mediumWait());
            scrollToWebElementJS(button_saveAccount);
            clickElementClickable(button_saveAccount, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickSaveButton")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickSaveButton");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }
}
