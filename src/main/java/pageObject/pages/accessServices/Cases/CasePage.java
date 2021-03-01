package pageObject.pages.accessServices.Cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CasePage extends CommonFunctions {

    @FindBy(xpath = "//article//a[@title='Open']")
    private WebElement label_openStatus;

    public boolean isCasePageDisplayed(){
        return waitForElementVisibility(label_openStatus, 20);
    }
}
