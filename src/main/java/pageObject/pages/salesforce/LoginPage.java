package pageObject.pages.salesforce;

import base.driverInitialize.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.functions.CommonFunctions;
import utils.FileReading;

import java.sql.Driver;

public class LoginPage extends CommonFunctions {
    @FindBy(id = "username")
    private WebElement input_Username;

    private By test = By.cssSelector("#username");

    @FindBy(css = "#password")
    private WebElement input_Password;

    @FindBy(css = "#Login")
    private WebElement button_Login;

    public void enterUserPassword(String salesforceUser) throws Exception {
        FileReading fileReading = new FileReading();
        fileReading.setFileName("SalesforceCredentials.properties");
        String username = fileReading.getField(salesforceUser);
        String password = fileReading.getField(salesforceUser+"Password");
        waitForPageToLoad();
        waitForElementVisibility(input_Username,20);
        sendKeysElementVisible(input_Username, username,10);
        sendKeysElementVisible(input_Password, password, 10);
        clickElementVisible(button_Login, 10);
    }

}
