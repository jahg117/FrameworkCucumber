package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewPatientInsuranceNoPI extends CommonFunctions {
    @FindBy(xpath = "//*[@name='SaveEdit']")
    private WebElement button_save;

    /**
     * It will create a No Patient Insurance only saving since it is no necessary to put any data
     * @throws Exception
     */
    public void clickOnSaveNoPI() throws Exception {
        clickAndMoveToElementClickable(button_save,mediumWait());
    }
}