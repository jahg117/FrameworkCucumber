package pageObject.pages.salesforce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.functions.CommonFunctions;
import utils.FileReading;


public class LoginPage extends CommonFunctions {
    @FindBy(id = "username")
    private WebElement input_username;

    @FindBy(css = "#password")
    private WebElement input_password;

    @FindBy(css = "#Login")
    private WebElement button_login;

    public void enterUserPassword(String salesforceUser) throws Exception {
        FileReading fileReading = new FileReading();
        fileReading.setFileName("SalesforceCredentials.properties");
        waitForPageToLoad();
        sendKeysElementVisible(input_username, fileReading.getField(salesforceUser),20);
        sendKeysElementVisible(input_password, fileReading.getField(salesforceUser+"Password"), 10);
        clickElementVisible(button_login, 10);
    }

}
