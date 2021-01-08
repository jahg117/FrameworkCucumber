package pageObject.pages.salesforce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.functions.CommonFunctions;

import java.util.List;

public class HomePage extends CommonFunctions {
    @FindBy(css = ".uiAutocomplete")
    private WebElement input_SearchBar;

    @FindBy(xpath = "//div[contains(@class,'secondary')]//div[starts-with(@class,'close')]")
    private List<WebElement> button_closeTabs;

    public void isSalesforcePageVisible()
    {
        waitForPageToLoad();
        waitForElementVisibility(input_SearchBar, 30);
    }

    public void closeOpenTabs() throws Exception {
        if(waitForElementListVisible(button_closeTabs, 30)&&button_closeTabs.size()>0){
            for(WebElement close : button_closeTabs){
                clickElementVisible(close, 10);
            }
        }
    }

}
