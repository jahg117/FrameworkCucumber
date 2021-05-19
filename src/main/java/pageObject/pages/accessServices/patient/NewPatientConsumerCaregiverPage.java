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
import java.util.ArrayList;
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

    @FindBy(xpath = "//*[text()='  Phone/Fax 2 ']/following::input[@data-name='number']")
    private WebElement inputSecondPhoneFaxNumber;

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

    @FindBy(xpath = "//*[contains(text(),'Email Address')]/following::input[@name='ACS_Type__c']")
    private WebElement dropdown_emailTypeHCA;

    @FindBy(xpath = "//*[contains(text(),'Email Address')]/following::*[@role='combobox']/following::*[@data-value]")
    private List<WebElement> dropdownEmailTypeHCAList;

    @FindBy(xpath = "//input[@placeholder='Select an Option']")
    private WebElement dropdown_phoneType;

    @FindBy(xpath = "//*[text()='  Phone/Fax 2 ']/following::input[@placeholder='Select an Option']")
    private WebElement dropdownSecondPhoneFaxType;

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
        return waitForElementVisibility(form_patientConsumerCaregiver, longWait());
    }

    public HashMap<String, String> fillPatientConsumerCaregiverForm() throws Exception {
        Faker faker = new Faker();
        JsonFiles jsonFiles = new JsonFiles();
        jsonFiles.setFileName("zipCode");
        HashMap<String, String> patientDetails = new HashMap<String, String>();
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
        if(waitForElementVisibility(dropdown_emailType, shortWait())){
            scrollToWebElementJS(dropdown_emailType);
            selectRandomDropDownNotNone(dropdown_emailType);
        }
        sendKeysAndMoveToElementVisible(input_zipCode, patientDetails.get("zipcode"), mediumWait());
        if (!getWebElementAttribute(input_firstName, "value").equalsIgnoreCase(patientDetails.get("firstName"))) {
            input_firstName.clear();
            sendKeysAndMoveToElementClickable(input_firstName, patientDetails.get("firstName"), mediumWait());
        }
        if (!getWebElementAttribute(input_lastName, "value").equalsIgnoreCase(patientDetails.get("lastName"))) {
            input_lastName.clear();
            sendKeysAndMoveToElementClickable(input_lastName, patientDetails.get("lastName"), mediumWait());
        }
        return patientDetails;
    }


    public HashMap<String, String> fillPatientConsumerCaregiverForm(HashMap<String, String> patientForm) throws Exception {
        Faker faker = new Faker();
        By phoneType = By.xpath("//div[contains(@class,'slds-dropdown_left slds-dropdown')]//*[@data-value='" + patientForm.get("phoneType") + "']");
        HashMap<String, String> patientDetails = new HashMap<String, String>();
        patientDetails.put("firstName", patientForm.get("name") + "" + faker.name().firstName());
        patientDetails.put("lastName", faker.name().lastName());
        patientDetails.put("address", faker.address().streetName());
        patientDetails.put("city", faker.address().cityName());
        patientDetails.put("phoneNumber", patientForm.get("fax"));
        patientDetails.put("date", getRandomDate());
        patientDetails.put("zipcode", patientForm.get("zipcode"));
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
        clickElementClickable(dropdown_phoneType, mediumWait());
        clickElementClickable(phoneType, mediumWait());
        scrollToWebElementJS(input_searchPlaces);
        sendKeysElementVisible(input_addressLine1, patientDetails.get("address"), mediumWait());
        sendKeysElementVisible(input_city, patientDetails.get("city"), mediumWait());
        scrollToWebElementJS(input_emailAddress);
        sendKeysAndMoveToElementVisible(input_emailAddress, patientDetails.get("firstName") + "@astrazeneca.com", mediumWait());
        waitForElementVisibility(dropdown_emailType, mediumWait());
        if (waitForElementClickable(dropdown_emailType, mediumWait())) {
            selectRandomDropDownNotNone(dropdown_emailType);
        }
        sendKeysAndMoveToElementVisible(input_zipCode, patientDetails.get("zipcode"), mediumWait());
        if (!getWebElementAttribute(input_firstName, "value").equalsIgnoreCase(patientDetails.get("firstName"))) {
            input_firstName.clear();
            sendKeysAndMoveToElementClickable(input_firstName, patientDetails.get("firstName"), mediumWait());
        }
        if (!getWebElementAttribute(input_lastName, "value").equalsIgnoreCase(patientDetails.get("lastName"))) {
            input_lastName.clear();
            sendKeysAndMoveToElementClickable(input_lastName, patientDetails.get("lastName"), mediumWait());
        }
        return patientDetails;
    }

    public void clickSaveButton() throws Exception {
        waitForElementVisibility(button_saveAccount, mediumWait());
        scrollToWebElementJS(button_saveAccount);
        clickElementClickable(button_saveAccount, mediumWait());
    }


    public HashMap<String, String> fillPatientConsumerCaregiverFormPDC(String accData) throws Exception {
        Faker faker = new Faker();
        HashMap<String, String> patientDetails = new HashMap<String, String>();
        List<String> accDataList = splitRegex(accData = accData.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY), Values.REGEX_COMMA);
        List<String> patientData = generateAccRecord(accDataList);
        patientDetails.put("identifier", patientData.get(0));
        patientDetails.put("fName", patientData.get(1));
        patientDetails.put("lName", patientData.get(2));
        patientDetails.put("phoneNumber", patientData.get(3));
        patientDetails.put("phoneType", patientData.get(4));
        patientDetails.put("faxNumber", patientData.get(5));
        patientDetails.put("faxType", patientData.get(6));
        patientDetails.put("zipCode", patientData.get(7));
        patientDetails.put("emailDomain", patientData.get(8));
        patientDetails.put("address", patientData.get(9));
        patientDetails.put("date", patientData.get(10));
        patientDetails.put("city", patientData.get(11));

        By phoneType = By.xpath("//*[@data-value='" + patientDetails.get("phoneType") + "'][position()=1]");
        By faxType = By.xpath("//*[text()='  Phone/Fax 2 ']/following::input[@placeholder='Select an Option']/following::*[@data-value='" + patientDetails.get("faxType") + "']");

        waitForElementClickable(dropdown_prefix, mediumWait());
        //====================================================================================================FIRST NAME
        input_firstName.clear();
        if (!patientDetails.get("identifier").equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            patientDetails.put("fName", (patientDetails.get("identifier") + patientDetails.get("fName")).trim());
        }
        clickAndMoveToElementClickable(input_firstName, mediumWait());
        sendKeysAndMoveToElementClickable(input_firstName, patientDetails.get("fName"), mediumWait());
        //=====================================================================================================LAST NAME
        input_lastName.clear();
        sendKeysAndMoveToElementClickable(input_lastName, patientDetails.get("lName"), mediumWait());
        //============DATE OF BIRTH
        clickElementVisible(input_informalName, shortWait());
        sendKeysByActions(Keys.TAB.toString());
        sendKeysByActions(patientDetails.get("date"));
        //========================================================================================FIRST PHONE/FAX NUMBER
        scrollToWebElementJS(input_searchAccounts);
        sendKeysElementVisible(input_phoneNumber, patientDetails.get("phoneNumber"), mediumWait());
        clickElementClickable(dropdown_phoneType, mediumWait());
        clickElementClickable(phoneType, mediumWait());
        //=======================================================================================SECOND PHONE/FAX NUMBER
        if (!patientDetails.get("faxNumber").equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysElementVisible(inputSecondPhoneFaxNumber, patientDetails.get("faxNumber"), mediumWait());
            clickElementClickable(dropdownSecondPhoneFaxType, mediumWait());
            clickElementClickable(faxType, mediumWait());
        }
        //=======================================================================================================ADDRESS
        scrollToWebElementJS(input_searchPlaces);
        if (!patientDetails.get("address").equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysElementVisible(input_addressLine1, patientDetails.get("address"), mediumWait());
            sendKeysElementVisible(input_city, patientDetails.get("city"), mediumWait());
        }
        //========================================================================================EMAIL ADDRESS CREATION
        scrollToWebElementJS(input_emailAddress);
        if (!patientDetails.get("emailDomain").equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(input_emailAddress, patientDetails.get("fName") +
                    patientDetails.get("emailDomain"), mediumWait());
            waitForElementVisibility(dropdown_emailType, mediumWait());
            if (waitForElementClickable(dropdown_emailType, mediumWait())) {
                selectRandomDropDownNotNone(dropdown_emailType);
            }
        }
        //=======================================================================================================ZIPCODE
        sendKeysAndMoveToElementVisible(input_zipCode, patientDetails.get("zipCode"), mediumWait());
        if (!getWebElementAttribute(input_firstName, "value").equalsIgnoreCase(patientDetails.get("fName"))) {
            input_firstName.clear();
            sendKeysAndMoveToElementClickable(input_firstName, patientDetails.get("fName"), mediumWait());
        }
        if (!getWebElementAttribute(input_lastName, "value").equalsIgnoreCase(patientDetails.get("lName"))) {
            input_lastName.clear();
            sendKeysAndMoveToElementClickable(input_lastName, patientDetails.get("lName"), mediumWait());
        }
        return patientDetails;
    }

    public List<String> generateAccRecord(List<String> accDataList) throws Exception {
        int dataCounter = 0;
        List<String> patientData = new ArrayList<>();
        for (String data : accDataList) {
            List<String> patientDataBKP = new ArrayList<>();
            if (data.trim().equalsIgnoreCase(Values.TXT_RANDOM)) {
                patientDataBKP.add(createAccData(data, dataCounter));
            } else {
                patientDataBKP.add(data);
            }
            dataCounter++;
            patientData.addAll(patientDataBKP);
        }
        return patientData;
    }

    public String createAccData(String accData, int dataPosition) throws Exception {
        Faker faker = new Faker();
        try {
            switch (dataPosition) {
                case 0:
                    //============Identifier Name Randomly
                    accData = Values.TXT_ACCDATAIDENTIFIER;
                    break;
                case 1:
                    //============First Name Randomly
                    accData = faker.name().firstName();
                    break;
                case 2:
                    //============Last Name Randomly
                    accData = faker.name().lastName();
                    break;
                case 3:
                    //============Phone Number
                    accData = String.valueOf(faker.number().randomNumber(11, true));
                    break;
                case 4:
                    //============Phone Type
                    accData = Values.ARRAY_PHONEFAXVALUES[getRandomNumberByLimits(0, (Values.ARRAY_PHONEFAXVALUES.length - 1))];
                    break;
                case 5:
                    //============Fax Number
                    accData = String.valueOf(faker.number().randomNumber(11, true));
                    break;
                case 6:
                    //============Fax Type
                    accData = Values.ARRAY_PHONEFAXVALUES[getRandomNumberByLimits(0, (Values.ARRAY_PHONEFAXVALUES.length - 1))];
                    break;
                case 7:
                    //============ZipCode Randomly
                    accData = Values.ARRAY_ZIPCODEVALUES[getRandomNumberByLimits(0, (Values.ARRAY_ZIPCODEVALUES.length - 1))];
                    break;
                case 8:
                    //============emailDomain Randomly
                    accData = Values.CHAR_AT + Values.ARRAY_EMAILDOMAINVALUES[getRandomNumberByLimits
                            (0, (Values.ARRAY_EMAILDOMAINVALUES.length - 1))].trim() + Values.TXT_COM;
                    break;
                case 9:
                    //============Address Randomly
                    accData = faker.address().streetName();
                    break;
                case 10:
                    //============DATE OF BIRTH Randomly
                    accData = (accData = getRandomDate()).replace(Values.TXT_SLASH, Values.REPLACETO_EMPTY);
                    break;
                case 11:
                    //============DATE OF BIRTH Randomly
                    accData = faker.address().cityName();
                    break;
                default:
                    logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE + accData);
                    break;
            }
        } catch (Exception e) {
        }
        return accData;
    }
}