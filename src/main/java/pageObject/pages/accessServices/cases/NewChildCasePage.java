package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Values;

import java.util.List;

public class NewChildCasePage extends CommonFunctions {

    @FindBy(xpath = "//div[contains(@class,'modal-header')]//*[contains(text(),'Child Case')]")
    private WebElement labelChildCaseTitle;

    @FindBy(xpath = "//input[@name='selectRecType']")
    private WebElement dropdownRecordType;

    @FindBy(xpath = "//div[contains(@class,'slds-dropdown_left slds-dropdown')]//span[@class='slds-truncate']")
    private List<WebElement> listDropdownElements;

    @FindBy(xpath = "//button[@title='Continue' and contains(text(),'Continue')]")
    private WebElement buttonContinue;

    public boolean isChildCaseFormDisplayed() throws Exception {
        return waitForElementVisibility(labelChildCaseTitle, mediumWait());
    }

    public void selectCaseOption(String caseOption) throws Exception {
        clickAndMoveToElementClickable(dropdownRecordType, mediumWait());
        waitForElementListVisible(listDropdownElements, mediumWait());
        if (caseOption.equalsIgnoreCase(Values.TXT_RANDOM)) {
            clickAndMoveToElementClickable(getRandomWebElementFromList(listDropdownElements, shortWait()), shortWait());
        } else {
            for (WebElement el : listDropdownElements) {
                if (getWebElementText(el).equalsIgnoreCase(caseOption)) {
                    clickAndMoveToElementClickable(el, mediumWait());
                    break;
                }
            }
        }
        clickAndMoveToElementClickable(buttonContinue, mediumWait());
    }
}
