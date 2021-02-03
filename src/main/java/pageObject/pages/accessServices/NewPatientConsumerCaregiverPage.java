package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;

public class NewPatientConsumerCaregiverPage extends CommonFunctions {

    @FindBy(xpath = "//*[@data-component-id='ACS_PatientWizardParentComponent']")
    private WebElement form_patientConsumerCaregiver;

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

    public boolean isConsumerPatientCaregiverFormDisplayed(){
        return waitForElementVisibility(form_patientConsumerCaregiver, 30);
    }

    public HashMap<String, String> fillPatientConsumerCaregiverForm() throws Exception {
        Faker faker = new Faker();
        HashMap<String, String> patientDetails = new HashMap<String, String>();
        patientDetails.put("firstName", faker.name().firstName());
        patientDetails.put("lastName", faker.name().lastName());
        patientDetails.put("address", faker.address().streetName());
        patientDetails.put("city", faker.address().cityName());
        patientDetails.put("phoneNumber", faker.phoneNumber().cellPhone().replace(".","").replace("-",""));

        waitForElementVisibility(input_firstName, 20);
        sendKeysElementClickable(input_firstName, patientDetails.get("firstName"), 10);
        sendKeysElementClickable(input_lastName, patientDetails.get("lastName"), 10);
        //sendKeysElementVisibleWithCoordinates(input_dateOfBirth, getRandomDate(),5, 5 , 20);
        String randomDate = getRandomDate();
        clickElementVisible(input_informalName, 5);
        sendKeysByActions(Keys.TAB.toString());
        sendKeysByActions(randomDate.split("/")[0]);
        clickElementVisible(input_informalName, 5);
        sendKeysByActions(Keys.TAB.toString());
        sendKeysByActions(Keys.TAB.toString());
        sendKeysByActions(randomDate.split("/")[1]);
        clickElementVisible(input_informalName, 5);
        sendKeysByActions(Keys.TAB.toString());
        sendKeysByActions(Keys.TAB.toString());
        sendKeysByActions(Keys.TAB.toString());
        sendKeysByActions(randomDate.split("/")[2]);
        scrollToWebElementJS(input_searchAccounts);
        sendKeysElementVisible(input_phoneNumber, patientDetails.get("phoneNumber"), 10);
        scrollToWebElementJS(input_searchPlaces);
        sendKeysElementVisible(input_addressLine1, patientDetails.get("address"), 10);
        sendKeysElementVisible(input_city, patientDetails.get("city"), 10);
        scrollToWebElementJS(input_emailAddress);
        sendKeysAndMoveToElementVisible(input_emailAddress, patientDetails.get("firstName")+"@test.com", 10);
        selectAndMoveDropDownVisibleRandomOption(dropdown_emailType, 10);
        return patientDetails;
    }

    public void clickSaveButton() throws Exception {
        scrollToWebElementJS(button_saveAccount);
        clickElementVisible(button_saveAccount, 10);
    }
}
