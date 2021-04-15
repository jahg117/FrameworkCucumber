package pageObject.pages.accessServices.patient;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

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

    @FindBy(xpath = "//footer[@class='slds-modal__footer']//button[@type='submit']")
    private WebElement button_saveAccount;

    public boolean isConsumerPatientCaregiverFormDisplayed() {
        return waitForElementVisibility(form_patientConsumerCaregiver, 30);
    }

    public HashMap<String, String> fillPatientConsumerCaregiverForm() throws Exception {
        Faker faker = new Faker();
        JsonFiles jsonFiles = new JsonFiles();
        jsonFiles.setFileName("zipCode");
        HashMap<String, String> patientDetails = new HashMap<String, String>();
        patientDetails.put("firstName", "AutoSPP_" + faker.name().firstName());//JR
        patientDetails.put("lastName", faker.name().lastName() + "_Automation");
        patientDetails.put("address", faker.address().streetName());
        patientDetails.put("city", faker.address().cityName());
        //patientDetails.put("phoneNumber", faker.phoneNumber().cellPhone().replace(".", "").replace("-", ""));//JR
        patientDetails.put("phoneNumber", "2403061405");
        patientDetails.put("date", getRandomDate());
        //patientDetails.put("zipcode", jsonFiles.getRandomFieldArray("zip"));//JR
        patientDetails.put("zipcode", "06019");//JR
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
        scrollToWebElementJS(input_searchPlaces);
        sendKeysElementVisible(input_addressLine1, patientDetails.get("address"), 10);
        sendKeysElementVisible(input_city, patientDetails.get("city"), 10);
        scrollToWebElementJS(input_emailAddress);
        //sendKeysAndMoveToElementVisible(input_emailAddress, patientDetails.get("firstName") + "@test.com", 10);//JR
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

    public void clickSaveButton() throws Exception {
        waitForElementVisibility(button_saveAccount, 10);
        scrollToWebElementJS(button_saveAccount);
        clickElementClickable(button_saveAccount, 10);
    }
}
