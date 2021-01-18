package pageObject.pages.accessServices;

import base.driverInitialize.DriverFactory;
import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class NewPatientConsumerCaregiverPage extends CommonFunctions {

    @FindBy(xpath = "//input[@data-name='first']")
    private WebElement input_firstName;

    @FindBy(xpath = "//input[@data-name='last']")
    private WebElement input_lastName;
    
    @FindBy(xpath = "//input[@data-name='dob']")
    private WebElement input_dateOfBirth;

    @FindBy(xpath = "//input[@data-name='email']")
    private WebElement input_emailAddress;

    @FindBy(xpath = "//*[contains(text(),'Email Type')]/following::*[@name='optionSelect']")
    private WebElement dropdown_emailType;

    @FindBy(xpath = "//footer[@class='slds-modal__footer']//button[@type='submit']")
    private WebElement button_saveAccount;

    public void fillPatientConsumerCaregiverForm() throws Exception {
        Faker faker = new Faker();
        waitForElementVisibility(input_firstName, 20);
        String firstName = faker.name().firstName();
        sendKeysAndMoveToElementVisible(input_firstName, firstName, 10);
        sendKeysAndMoveToElementVisible(input_lastName, faker.name().lastName(), 10);
        sendKeysElementVisibleWithCoordinates(input_dateOfBirth, 5, 5 , 20);
        scrollToWebElementJS(input_emailAddress);
        sendKeysAndMoveToElementVisible(input_emailAddress, firstName+"@test.com", 10);
        selectAndMoveDropDownVisibleRandomOption(dropdown_emailType, 10);
        scrollToWebElementJS(button_saveAccount);
        clickElementVisible(button_saveAccount, 10);
    }

}
