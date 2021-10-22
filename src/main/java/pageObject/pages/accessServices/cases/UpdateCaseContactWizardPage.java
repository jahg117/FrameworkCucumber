package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;

public class UpdateCaseContactWizardPage extends CommonFunctions {
    private By label_caseContact = By.xpath("//span[contains(text(),'Case Contact')]/following::*//p[normalize-space(text())='Select Primary Contact for the Case']");

    public void closeCaseContactWizardPage() throws Exception {
        if(waitForNumberOfElementsToBeMoreThanBy(label_caseContact, 0,mediumWait())){
            By subTabs = By.xpath("//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]");
            By lastSubTab = By.xpath("(//*[starts-with(@aria-label,'Subtabs')]//li[starts-with(@class,'oneConsoleTabItem')]//*[starts-with(@class,'close')]//button)[last()]");
            if (waitForNumberOfElementsToBeMoreThanBy(subTabs,2, mediumWait())) {
                waitForPresenceOfAllElementsLocatedBy(subTabs, mediumWait());
                clickAndMoveToElementClickable(getWebElement(lastSubTab), mediumWait());
                if(waitForElementVisibilityOfElementLocatedBy(label_caseContact, shortWait())){
                    closeCaseContactWizardPage();
                }
            }else{
                closeLastSubTabSF(shortWait());
            }
        }
    }
}
