package pageObject.pages.accessServices.Cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateCaseContactWizardPage extends CommonFunctions {
    @FindBy(xpath = "//span[contains(text(),'Case Contact')]/following::*[@class='dt-outer-container']")
    private WebElement label_caseContact;

    public void closeCaseContactWizardPage() throws Exception {
        if(waitForElementVisibility(label_caseContact, 15)){
            By subTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]");
            By lastSubTab = By.xpath("(//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]//button)[last()]");
            if (waitForNumberOfElementsToBeMoreThanBy(subTabs,2, 10)) {
                waitForPresenceOfAllElementsLocatedBy(subTabs, 10);
                clickAndMoveToElementClickable(getWebElement(lastSubTab), 10);
            }
        }
    }
}
