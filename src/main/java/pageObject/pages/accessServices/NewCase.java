package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewCase extends CommonFunctions {
    @FindBy(xpath = "//div[@class='forceChangeRecordType']")
    private WebElement form_newCase;

    @FindBy(xpath = "//label[contains(@class,'radio topdown')]//span[contains(@class,'label')]")
    private List<WebElement> list_labelCases;

    @FindBy(xpath = "//label[contains(@class,'radio topdown')]//span[contains(@class,'radio--faux')]")
    private List<WebElement> list_radioCases;

    public boolean isNewCaseFormDisplayed(){
        return waitForElementVisibility(form_newCase, 20);
    }

    public void selectCaseOption(String caseOption) throws Exception {
        int i = 0;
        for(WebElement el : list_labelCases){
            if(getWebElementText(el).equalsIgnoreCase(caseOption)){
                clickAndMoveToElementClickable(list_radioCases.get(i), 10);
            }
            i++;
        }
    }
}
