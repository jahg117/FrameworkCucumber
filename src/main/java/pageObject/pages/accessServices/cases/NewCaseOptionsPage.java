package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewCaseOptionsPage extends CommonFunctions {

    @FindBy(xpath = "//div[@class='forceChangeRecordType']")
    private WebElement form_caseOptionsPage;

    @FindBy(xpath = "//div[@class='forceChangeRecordType']//span[contains(@class,'topdown-radio--label')]")
    private List<WebElement> list_caseOptions;

    @FindBy(xpath = "//*[contains(text(),'New Case')]/following::*[./text()='Next']")
    private WebElement button_next;

    public boolean isFormCaseOptionsPageDisplayed(){
        return waitForElementVisibility(form_caseOptionsPage, 20);
    }

    public void selectCaseOption(String caseOption) throws Exception {
        waitForElementListVisible(list_caseOptions, 20);
        if(!caseOption.equalsIgnoreCase("random")){
            for (WebElement el : list_caseOptions) {
                if(getWebElementText(el).equalsIgnoreCase(caseOption)){
                    clickAndMoveToElementClickable(el, 10);
                    break;
                }
            }
        }else{
            clickAndMoveToElementClickable(getRandomWebElementFromList(list_caseOptions, 10), 10);
        }
        clickAndMoveToElementClickable(button_next, 10);
    }
}
