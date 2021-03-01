package pageObject.pages.accessServices.Cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateCaseContactWizardPage extends CommonFunctions {
    @FindBy(xpath = "//span[contains(text(),'Case Contact')]/following::*[@class='dt-outer-container']")
    private WebElement label_caseContact;

    public boolean isCaseContactWizardPageDisplayed(){
        return waitForElementVisibility(label_caseContact, 15);
    }
}
