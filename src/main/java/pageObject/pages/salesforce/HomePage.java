package pageObject.pages.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.functions.CommonFunctions;

import java.util.List;

public class HomePage extends CommonFunctions {
    @FindBy(css = ".uiAutocomplete")
    private WebElement input_SearchBar;

    @FindBy(xpath = "//div[contains(@class,'secondary')]//div[starts-with(@class,'close')]")
    private List<WebElement> button_closeTabs;
    private By button_closeTabsBy = By.xpath("//div[contains(@class,'secondary')]//div[starts-with(@class,'close')]");

    @FindBy(xpath = "//div[contains(@class,'lafPageHost')]")
    private WebElement label_panelTab;

    public boolean isSalesforcePageVisible() throws Exception {
        waitForPageToLoad();
        waitForElementVisibility(input_SearchBar, longWait());
        return waitForElementAttributeContains(label_panelTab,"data-aura-class","lafPageHost", longWait());
    }

    public void closeOpenTabs() throws Exception {
        waitForPageToLoad();
        if(waitForNumberOfElementsToBeMoreThanBy(button_closeTabsBy, 0, 5)){
            try {
                for (WebElement close : getWebElementList(button_closeTabsBy)) {
                    clickElementVisible(close, 5);
                }
                if(waitForNumberOfElementsToBeMoreThanBy(button_closeTabsBy, 0, 5)){
                    closeOpenTabs();
                }
            }
            catch (Exception e) {
                if (waitForNumberOfElementsToBeMoreThanBy(button_closeTabsBy, 0, 5)) {
                    closeOpenTabs();
                }
            }
        }
    }

    public void closePreviousTab() throws Exception {
        waitForPageToLoad();
        if (waitForElementListVisible(button_closeTabs, 10) && button_closeTabs.size() > 0) {
            clickElementVisible(button_closeTabs.get(button_closeTabs.size()-2),20);
        }
    }

}
