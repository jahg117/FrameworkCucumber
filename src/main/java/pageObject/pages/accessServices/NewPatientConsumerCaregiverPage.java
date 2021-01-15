package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.WebElement;
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
        //clickElementVisible(input_dateOfBirth, 5);
        //sendKeysAndMoveToElementVisible(input_dateOfBirth, getRandomDate(), 10);
        //scrollMethodTopBottom("bottom");
        scrollToWebElementVisibleByAction(input_emailAddress, 5);
        sendKeysAndMoveToElementVisible(input_emailAddress, firstName+"@test.com", 10);
        selectAndMoveDropDownVisibleRandomOption(dropdown_emailType, 10);
        clickAndMoveToElementVisible(button_saveAccount, 10);
        clickElementJS(button_saveAccount);
    }

}
