package pageObject.pages.salesforce;

import base.functions.CommonFunctions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AppLauncherPage extends CommonFunctions {

    @FindBy(xpath = "//*[@class='appLauncher slds-context-bar__icon-action']")
    private WebElement appLauncher_Button;

    //@FindBy(css = "input.slds-input[placeholder*='Search app']")
    @FindBy(xpath = "//*[@placeholder='Search apps and items...']")
    private WebElement appLaucher_SearchBar;

    @FindBy(xpath = "//*[starts-with(@class,'appName')]/span[@title]")
    private WebElement appNameTitle_PageTitle;


    public boolean searchAppName(String appName) throws Exception {
        boolean statusOperation = false;
        if (waitForElementVisibility(appLauncher_Button, 20)) {
            clickMethod(appLauncher_Button);
            waitForElementVisibility(appLaucher_SearchBar, 20);
            appLaucher_SearchBar.sendKeys(appName);
            appLaucher_SearchBar.sendKeys(Keys.ENTER);
            if (waitForElementVisibility(appNameTitle_PageTitle, 20)) {
                statusOperation = true;
            } else {
                System.out.println("The Page: " + appNameTitle_PageTitle.getAttribute("title") + " Does Not Match: "
                        + " appName");
                statusOperation = false;
            }
        }
        return statusOperation;
    }
}

