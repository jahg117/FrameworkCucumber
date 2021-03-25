package pageObject.pages.accessServices.provider;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewProviderPage extends CommonFunctions {

    @FindBy(xpath = "//input[@class='patientaddr']")
    private List<WebElement> list_checkboxAddress;

    @FindBy(xpath = "(//iframe[@title='accessibility title'])[last()]")
    private WebElement iframe_newProvider;

    @FindBy(xpath = "//input[@value='Save']")
    private WebElement button_save;

    @FindBy(xpath = " //table[contains(@class,'slds-vf-data-table')]//td")
    private List<WebElement> table_rows;

    @FindBy(xpath = "//input[contains(@id,'sppName')]")
    private WebElement input_sppName;

    @FindBy(xpath = "//input[contains(@id,'sppContactName')]")
    private WebElement input_sppContactName;

    public boolean isSppContactNameDisplayed(){
        return waitForElementVisibility(input_sppContactName, 20);
    }

    public boolean isSppNameDisplayed(){
        return waitForElementVisibility(input_sppName, 20);
    }

    public boolean isProviderPageDisplayed() throws Exception {
        switchToParentFrame();
        if(waitForElementVisibility(iframe_newProvider, 20)){
            switchToFrameByWebElementIndexOrName(iframe_newProvider, 10);
            return true;
        }else{
            return false;
        }
    }

    public boolean isTableElementDisplayed(String data){
        boolean isVisible = false;
        waitForElementListVisible(table_rows, 10);
        for(WebElement el : table_rows){
            if(getWebElementText(el).equalsIgnoreCase(data)){
                isVisible = true;
                break;
            }
        }
        return isVisible;
    }

    public void selectAddressAndSaveConsent() throws Exception {
        isProviderPageDisplayed();
        clickAndMoveToElementVisible(list_checkboxAddress.get(0), 10);
        clickAndMoveToElementClickable(button_save, 10);
        switchToParentFrame();
    }
}
