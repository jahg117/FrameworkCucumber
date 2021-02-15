package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewCase extends CommonFunctions {
    @FindBy(xpath = "//div[@class='forceChangeRecordType']")
    private WebElement form_newCase;
}
