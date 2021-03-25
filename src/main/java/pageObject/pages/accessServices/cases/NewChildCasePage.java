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

    public boolean isChildCaseFormDisplayed() {
        return waitForElementVisibility(label_childCaseTitle, 20);
    }

    public void selectCaseOption(String caseOption) throws Exception {
        clickAndMoveToElementClickable(dropdown_recordType, 10);
        waitForElementListVisible(list_dropdownElements, 10);
        if(caseOption.equalsIgnoreCase("random")) {
            clickAndMoveToElementClickable(getRandomWebElementFromList(list_dropdownElements,5),5);
        }else{
            for (WebElement el : list_dropdownElements) {
                if(getWebElementText(el).equalsIgnoreCase(caseOption)){
                    clickAndMoveToElementClickable(el, 10);
                    break;
                }
            }
        }
        clickAndMoveToElementClickable(button_continue, 10);
    }
}
