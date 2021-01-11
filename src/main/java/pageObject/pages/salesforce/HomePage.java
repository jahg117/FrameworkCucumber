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

    @FindBy(xpath = "//div[contains(@class,'lafPageHost')]")
    private WebElement label_panelTab;

    public void isSalesforcePageVisible() throws InterruptedException {
        waitForElementVisibility(input_SearchBar, 30);
        waitForPageToLoad();
        waitForElementAttributeContains(label_panelTab,"data-aura-class","lafPageHost", 30);
    }

    public void closeOpenTabs() throws Exception {
        if (waitForElementListVisible(button_closeTabs, 7) && button_closeTabs.size() > 0) {
            for (WebElement close : button_closeTabs) {
                    clickElementVisible(close, 10);
            }
        }
    }

}
