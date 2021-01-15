package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomerLookupPage extends CommonFunctions {

    @FindBy(xpath = "//iframe[@title='accessibility title']")
    private List<WebElement> iframe_pageInformation;

    @FindBy(xpath = "//button[contains(@onClick,'openAccountCreationTab')]")
    private WebElement button_newAccount;

    public void clickNewAccount() throws Exception {
        waitForPageToLoad();
        waitForElementListVisible(iframe_pageInformation, 10);
        switchToFrameByWebElementIndexOrName(iframe_pageInformation.get(0), 30);
        clickAndMoveToElementClickable(button_newAccount, 10);
        switchToParentFrame();
    }
}
