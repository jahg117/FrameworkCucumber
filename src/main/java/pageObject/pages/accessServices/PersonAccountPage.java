package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonAccountPage extends CommonFunctions {
    @FindBy(css = "slot[name='primaryField']")
    private WebElement label_accountPersonName;

    public boolean isPersonAccountPageDisplayed(){
        return waitForElementVisibility(label_accountPersonName, 30);
    }
}
