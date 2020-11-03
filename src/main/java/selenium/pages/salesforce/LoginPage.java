package selenium.pages.salesforce;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage {
    @FindBy(how = How.ID, using = "username")
    private WebElement input_Username;

    @FindBy(how = How.CSS, using = "#password")
    private WebElement input_Password;

    @FindBy(how = How.CSS, using = "#Login")
    private WebElement button_Login;

    public void EnterUserPassword(String userName, String password)
    {
        waitForElementVisibility(input_Password, 15);
        input_Username.sendKeys(userName);
        input_Password.sendKeys(password);
    }

    public HomePage ClickLogin()
    {
        waitForElementClickable(button_Login, 10);
        button_Login.click();
        return GetInstance(HomePage.class);
    }
}
