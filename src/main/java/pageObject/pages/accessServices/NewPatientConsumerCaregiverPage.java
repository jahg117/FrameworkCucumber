package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewPatientConsumerCaregiverPage extends CommonFunctions {

    @FindBy(xpath = "//input[@data-name='first']")
    private WebElement input_firstName;

    @FindBy(xpath = "//input[@data-name='last']")
    private WebElement input_lastName;
    
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

    @FindBy(xpath = "//div[@role='listbox']//li")
    private List<WebElement> label_searchOptions;

    @FindBy(xpath = "//*[contains(text(),'Email Type')]/following::*[@name='optionSelect']")
    private WebElement dropdown_emailType;

    @FindBy(xpath = "//footer[@class='slds-modal__footer']//button[@type='submit']")
    private WebElement button_saveAccount;

    public void fillPatientConsumerCaregiverForm() throws Exception {
        Faker faker = new Faker();
        waitForElementVisibility(input_firstName, 20);
        String firstName = faker.name().firstName();
        sendKeysElementVisible(input_firstName, firstName, 10);
        sendKeysElementVisible(input_lastName, faker.name().lastName(), 10);
        sendKeysElementVisibleWithCoordinates(input_dateOfBirth, 5, 5 , 20);
        scrollToWebElementJS(input_searchAccounts);
        sendKeysElementVisible(input_phoneNumber, faker.phoneNumber().cellPhone().replace(".","").replace("-",""), 10);
        scrollToWebElementJS(input_searchPlaces);
        sendKeysElementVisible(input_searchPlaces, faker.address().cityName(), 10);
        waitForElementListVisible(label_searchOptions, 5);
        clickElementVisible(label_searchOptions.get(0), 10);
        scrollToWebElementJS(input_emailAddress);
        sendKeysAndMoveToElementVisible(input_emailAddress, firstName+"@test.com", 10);
        selectAndMoveDropDownVisibleRandomOption(dropdown_emailType, 10);
        scrollToWebElementJS(button_saveAccount);
        clickElementVisible(button_saveAccount, 10);
    }
}
