package pageObject.pages.accessServices.Cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateCaseContactWizardPage extends CommonFunctions {
    private By label_caseContact = By.xpath("//span[contains(text(),'Case Contact')]/following::*[@class='dt-outer-container']");

    public void closeCaseContactWizardPage() throws Exception {
        if(waitForNumberOfElementsToBeMoreThanBy(label_caseContact, 0,10)){
            By subTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]");
            By lastSubTab = By.xpath("(//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]//button)[last()]");
            if (waitForNumberOfElementsToBeMoreThanBy(subTabs,2, 10)) {
                waitForPresenceOfAllElementsLocatedBy(subTabs, 10);
                clickAndMoveToElementClickable(getWebElement(lastSubTab), 10);
                if(waitForElementVisibilityOfElementLocatedBy(label_caseContact, 5)){
                    closeCaseContactWizardPage();
                }
            }else{
                closeLastSubTabSF(5);
            }
        }
    }
}
