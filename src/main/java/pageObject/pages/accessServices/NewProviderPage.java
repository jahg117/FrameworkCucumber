package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewProviderPage extends CommonFunctions {

    @FindBy(xpath = "//input[@class='patientaddr']")
    private List<WebElement> list_checkboxAddress;

    @FindBy(xpath = "//iframe[@title='accessibility title']")
    private List<WebElement> iframe_newProvider;

    @FindBy(xpath = "//input[@value='Save']")
    private WebElement button_save;

    @FindBy(xpath = " //table[contains(@class,'slds-vf-data-table')]//td")
    private List<WebElement> table_rows;

    public boolean isProviderPageDisplayed() {
        return waitForElementVisibility(iframe_newProvider.get(1), 20);
    }

    public boolean isTableElementDisplayed(String data){
        boolean isVisible = false;
        switchToFrameByWebElementIndexOrName(iframe_newProvider.get(1), 10);
        waitForElementListVisible(table_rows, 10);
        for(WebElement el : table_rows){
            if(getWebElementText(el).equalsIgnoreCase(data)){
                isVisible = true;
                break;
            }
        }
        switchToParentFrame();
        return isVisible;
    }

    public void selectAddressAndSaveConsent() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newProvider.get(1), 10);
        clickAndMoveToElementVisible(list_checkboxAddress.get(0), 10);
        clickAndMoveToElementVisible(button_save, 10);
        switchToParentFrame();
    }
}
