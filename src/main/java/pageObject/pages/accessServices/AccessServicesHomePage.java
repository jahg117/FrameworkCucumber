package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccessServicesHomePage extends CommonFunctions {
    @FindBy(css = ".slds-truncate[title='Access Services']")
    private WebElement accessServices_Title;

    @FindBy(css = ".appLauncher")
    private WebElement appLauncher_Button;

    @FindBy(css = "input.slds-input[placeholder^='Search app']")
    private WebElement appLaucher_SearchBar;

    public void isAccessServicesTitleVisible()
    {
        waitForElementVisibility(accessServices_Title, 30);
    }
}
