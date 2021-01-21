package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductEnrollmentPage extends CommonFunctions {
    @FindBy(xpath = "//*[@title='New Care Team Member']")
    private WebElement button_newCareTeamMember;

    public void isProductEnrollmentPageDisplayed() {
        waitForElementVisibility(button_newCareTeamMember, 30);
    }
}
