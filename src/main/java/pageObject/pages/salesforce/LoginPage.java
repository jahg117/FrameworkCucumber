package pageObject.pages.salesforce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.functions.CommonFunctions;

public class LoginPage extends CommonFunctions {
    @FindBy(id = "username")
    private WebElement input_Username;

    @FindBy(css = "#password")
    private WebElement input_Password;

    @FindBy(css = "#Login")
    private WebElement button_Login;

    public void EnterUserPassword(String userName, String password)
    {
        waitForElementVisibility(input_Username,20);
        input_Username.sendKeys(userName);
        input_Password.sendKeys(password);
    }

    public void ClickLogin()
    {
        waitForElementVisibility(button_Login, 20);
        button_Login.click();
    }
}
