package pageObject.pages.salesforce;

import base.functions.CommonFunctions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppLauncherPage extends CommonFunctions {

    @FindBy(xpath = "//*[@class='appLauncher slds-context-bar__icon-action']")
    private WebElement button_AppLauncher;

    @FindBy(xpath = "//*[@placeholder='Search apps and items...']")
    private WebElement input_AppLauncher;

    @FindBy(xpath = "//*[starts-with(@class,'appName')]/span[@title]")
    private WebElement label_AppNameTitle;


    public boolean searchAppName(String appName) throws Exception {
        boolean statusOperation = false;
        if (waitForElementVisibility(button_AppLauncher, 20)) {
            clickAndMoveToElementVisible(button_AppLauncher, 20);
            waitForElementVisibility(input_AppLauncher, 20);
            sendKeysElementVisible(input_AppLauncher, appName, 10);
            sendKeysElementVisible(input_AppLauncher, Keys.ENTER.toString(), 10);
            if (waitForElementVisibility(label_AppNameTitle, 20)) {
                statusOperation = true;
            } else {
                statusOperation = false;
            }
        } else{
            statusOperation = false;
        }
        return statusOperation;
    }
}

