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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewPatientConsumerCaregiverPage extends CommonFunctions {

    @FindBy(xpath = "//*[@data-component-id='ACS_PatientWizardParentComponent']")
    private WebElement formPatientConsumerCaregiver;

    @FindBy(xpath = "//select[@data-name='salutation']")
    private WebElement dropdownPrefix;

    @FindBy(xpath = "//input[@data-name='first']")
    private WebElement inputFirstName;

    @FindBy(xpath = "//input[@data-name='last']")
    private WebElement inputLastName;

    @FindBy(xpath = "//input[@data-name='pname']")
    private WebElement inputInformalName;

    @FindBy(xpath = "//input[@data-name='dob']")
    private WebElement inputDateOfBirth;

    @FindBy(xpath = "//input[@data-name='email']")
    private WebElement inputEmailAddress;

    @FindBy(xpath = "//input[@data-name='Zip']")
    private WebElement inputZipCode;

    @FindBy(xpath = "//input[@data-name='number']")
    private WebElement inputPhoneNumber;

    @FindBy(xpath = "//*[text()='  Phone/Fax 2 ']/following::input[@data-name='number']")
    private WebElement inputSecondPhoneFaxNumber;

    @FindBy(xpath = "//input[@placeholder='Search Accounts...']")
    private WebElement inputSearchAccounts;

    @FindBy(xpath = "//input[@placeholder='Search Places']")
    private WebElement inputSearchPlaces;

    @FindBy(xpath = "//input[@data-name='street1']")
    private WebElement inputAddressLine1;

    @FindBy(xpath = "//input[@data-name='city']")
    private WebElement inputCity;

    @FindBy(xpath = "//div[@role='listbox']//li")
    private List<WebElement> labelSearchOptions;

    @FindBy(xpath = "//*[contains(text(),'Email Type')]/following::*[@name='optionSelect']")
    private WebElement dropdownEmailType;

    @FindBy(xpath = "//*[contains(text(),'Email Address')]/following::input[@name='ACS_Type__c']")
    private WebElement dropdownEmailTypeHCA;

    @FindBy(xpath = "//*[contains(text(),'Email Address')]/following::*[@role='combobox']/following::*[@data-value]")
    private List<WebElement> dropdownEmailTypeHCAList;

    @FindBy(xpath = "//input[@placeholder='Select an Option']")
    private WebElement dropdownPhoneType;

    @FindBy(xpath = "//*[text()='  Phone/Fax 2 ']/following::input[@placeholder='Select an Option']")
    private WebElement dropdownSecondPhoneFaxType;

    @FindBy(xpath = "//footer[@class='slds-modal__footer']//button[@type='submit']")
    private WebElement buttonSaveAccount;

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

    public boolean isConsumerPatientCaregiverFormDisplayed() throws Exception {
        return waitForElementVisibility(formPatientConsumerCaregiver, longWait());
    }
//JRR

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
        waitForElementClickable(dropdownPrefix, mediumWait());
        inputFirstName.clear();
        clickAndMoveToElementClickable(inputFirstName, mediumWait());
        sendKeysAndMoveToElementClickable(inputFirstName, patientDetails.get("firstName"), mediumWait());
        sendKeysAndMoveToElementClickable(inputLastName, patientDetails.get("lastName"), mediumWait());
        String randomDate = patientDetails.get("date").replace("/", "");
        sendKeysWebElement(inputDateOfBirth,randomDate);
        scrollToWebElementJS(inputSearchAccounts);
        sendKeysElementVisible(inputPhoneNumber, patientDetails.get("phoneNumber"), mediumWait());
        scrollToVisibleElement(inputSearchPlaces, shortWait());
        sendKeysElementVisible(inputAddressLine1, patientDetails.get("address"), mediumWait());
        sendKeysElementVisible(inputCity, patientDetails.get("city"), mediumWait());
        sendKeysAndMoveToElementVisible(inputEmailAddress, patientDetails.get("firstName") + "@astrazeneca.com", mediumWait());
        if(waitForElementVisibility(dropdownEmailType, shortWait())){
            scrollToWebElementJS(dropdownEmailType);
            selectRandomDropDownNotNone(dropdownEmailType);
        }
        sendKeysAndMoveToElementVisible(inputZipCode, patientDetails.get("zipcode"), mediumWait());
        if (!getWebElementAttribute(inputFirstName, "value").equalsIgnoreCase(patientDetails.get("firstName"))) {
            inputFirstName.clear();
            sendKeysAndMoveToElementClickable(inputFirstName, patientDetails.get("firstName"), mediumWait());
        }
        if (!getWebElementAttribute(inputLastName, "value").equalsIgnoreCase(patientDetails.get("lastName"))) {
            inputLastName.clear();
            sendKeysAndMoveToElementClickable(inputLastName, patientDetails.get("lastName"), mediumWait());
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
        waitForElementClickable(dropdownPrefix, mediumWait());
        inputFirstName.clear();
        clickAndMoveToElementClickable(inputFirstName, mediumWait());
        sendKeysAndMoveToElementClickable(inputFirstName, patientDetails.get("firstName"), mediumWait());
        sendKeysAndMoveToElementClickable(inputLastName, patientDetails.get("lastName"), mediumWait());
        String randomDate = patientDetails.get("date").replace("/", "");
        sendKeysWebElement(inputDateOfBirth,randomDate);
        sendKeysElementVisible(inputPhoneNumber, patientDetails.get("phoneNumber"), mediumWait());
        clickElementClickable(dropdownPhoneType, mediumWait());
        clickElementClickable(phoneType, mediumWait());
        scrollToWebElementJS(inputSearchPlaces);
        sendKeysElementVisible(inputAddressLine1, patientDetails.get("address"), mediumWait());
        sendKeysElementVisible(inputCity, patientDetails.get("city"), mediumWait());
        scrollToWebElementJS(inputEmailAddress);
        sendKeysAndMoveToElementVisible(inputEmailAddress, patientDetails.get("firstName") + "@astrazeneca.com", mediumWait());
        waitForElementVisibility(dropdownEmailType, mediumWait());
        if (waitForElementClickable(dropdownEmailType, shortWait())) {
            scrollToWebElementJS(dropdownEmailType);
            selectRandomDropDownNotNone(dropdownEmailType);
        }
        sendKeysAndMoveToElementVisible(inputZipCode, patientDetails.get("zipcode"), mediumWait());
        if (!getWebElementAttribute(inputFirstName, "value").equalsIgnoreCase(patientDetails.get("firstName"))) {
            inputFirstName.clear();
            sendKeysAndMoveToElementClickable(inputFirstName, patientDetails.get("firstName"), mediumWait());
        }
        if (!getWebElementAttribute(inputLastName, "value").equalsIgnoreCase(patientDetails.get("lastName"))) {
            inputLastName.clear();
            sendKeysAndMoveToElementClickable(inputLastName, patientDetails.get("lastName"), mediumWait());
        }
        return patientDetails;
    }


    public void clickSaveButton() throws Exception {
        waitForElementVisibility(buttonSaveAccount, mediumWait());
        scrollToWebElementJS(buttonSaveAccount);
        clickElementClickable(buttonSaveAccount, mediumWait());
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

        waitForElementClickable(dropdownPrefix, mediumWait());
        //====================================================================================================FIRST NAME
        inputFirstName.clear();
        if (!patientDetails.get("identifier").equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            patientDetails.put("fName", (patientDetails.get("identifier") + patientDetails.get("fName")).trim());
        }
        clickAndMoveToElementClickable(inputFirstName, mediumWait());
        sendKeysAndMoveToElementClickable(inputFirstName, patientDetails.get("fName"), mediumWait());
        //=====================================================================================================LAST NAME
        inputLastName.clear();
        sendKeysAndMoveToElementClickable(inputLastName, patientDetails.get("lName"), mediumWait());
        //============DATE OF BIRTH
        clickElementVisible(inputInformalName, shortWait());
        sendKeysByActions(Keys.TAB.toString());
        sendKeysByActions(patientDetails.get("date"));
        //========================================================================================FIRST PHONE/FAX NUMBER
        scrollToWebElementJS(inputSearchAccounts);
        sendKeysElementVisible(inputPhoneNumber, patientDetails.get("phoneNumber"), mediumWait());
        clickElementClickable(dropdownPhoneType, mediumWait());
        clickElementClickable(phoneType, mediumWait());
        //=======================================================================================SECOND PHONE/FAX NUMBER
        if (!patientDetails.get("faxNumber").equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysElementVisible(inputSecondPhoneFaxNumber, patientDetails.get("faxNumber"), mediumWait());
            clickElementClickable(dropdownSecondPhoneFaxType, mediumWait());
            clickElementClickable(faxType, mediumWait());
        }
        //=======================================================================================================ADDRESS
        scrollToWebElementJS(inputSearchPlaces);
        if (!patientDetails.get("address").equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysElementVisible(inputAddressLine1, patientDetails.get("address"), mediumWait());
            sendKeysElementVisible(inputCity, patientDetails.get("city"), mediumWait());
        }
        //========================================================================================EMAIL ADDRESS CREATION
        scrollToWebElementJS(inputEmailAddress);
        if (!patientDetails.get("emailDomain").equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputEmailAddress, patientDetails.get("fName") +
                    patientDetails.get("emailDomain"), mediumWait());
            waitForElementVisibility(dropdownEmailType, mediumWait());
            if (waitForElementClickable(dropdownEmailType, mediumWait())) {
                selectRandomDropDownNotNone(dropdownEmailType);
            }
        }
        //=======================================================================================================ZIPCODE
        sendKeysAndMoveToElementVisible(inputZipCode, patientDetails.get("zipCode"), mediumWait());
        if (!getWebElementAttribute(inputFirstName, "value").equalsIgnoreCase(patientDetails.get("fName"))) {
            inputFirstName.clear();
            sendKeysAndMoveToElementClickable(inputFirstName, patientDetails.get("fName"), mediumWait());
        }
        if (!getWebElementAttribute(inputLastName, "value").equalsIgnoreCase(patientDetails.get("lName"))) {
            inputLastName.clear();
            sendKeysAndMoveToElementClickable(inputLastName, patientDetails.get("lName"), mediumWait());
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