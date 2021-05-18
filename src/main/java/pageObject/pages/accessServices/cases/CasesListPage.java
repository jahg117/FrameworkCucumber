package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CasesListPage extends CommonFunctions {
    @FindBy(xpath = "//a[@title='New']")
    private WebElement button_new;

    public boolean isCasesListPageDisplayed() throws Exception {
        return waitForElementVisibility(button_new, mediumWait());
    }
    public void clickNewCase() throws Exception {
        clickAndMoveToElementClickable(button_new, mediumWait());
    }
}
