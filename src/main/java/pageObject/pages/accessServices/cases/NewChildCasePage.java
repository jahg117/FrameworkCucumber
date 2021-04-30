package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewChildCasePage extends CommonFunctions {

    @FindBy(xpath = "//div[contains(@class,'modal-header')]//*[contains(text(),'Child Case')]")
    private WebElement label_childCaseTitle;

    @FindBy(xpath = "//input[@name='selectRecType']")
    private WebElement dropdown_recordType;

    @FindBy(xpath = "//div[contains(@class,'slds-dropdown_left slds-dropdown')]//span[@class='slds-truncate']")
    private List<WebElement> list_dropdownElements;

    @FindBy(xpath = "//button[@title='Continue' and contains(text(),'Continue')]")
    private WebElement button_continue;

    public boolean isChildCaseFormDisplayed() throws Exception {
        return waitForElementVisibility(label_childCaseTitle, mediumWait());
    }

    public void selectCaseOption(String caseOption) throws Exception {
        clickAndMoveToElementClickable(dropdown_recordType, mediumWait());
        waitForElementListVisible(list_dropdownElements, mediumWait());
        if (caseOption.equalsIgnoreCase("random")) {
            clickAndMoveToElementClickable(getRandomWebElementFromList(list_dropdownElements, shortWait()), shortWait());
        } else {
            for (WebElement el : list_dropdownElements) {
                if (getWebElementText(el).equalsIgnoreCase(caseOption)) {
                    clickAndMoveToElementClickable(el, mediumWait());
                    break;
                }
            }
        }
        clickAndMoveToElementClickable(button_continue, mediumWait());
    }
}
