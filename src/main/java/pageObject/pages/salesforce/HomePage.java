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

    public boolean isSalesforcePageVisible() throws InterruptedException {
        waitForPageToLoad();
        waitForElementVisibility(input_SearchBar, 30);
        return waitForElementAttributeContains(label_panelTab,"data-aura-class","lafPageHost", 30);
    }

    public void closeOpenTabs() throws Exception {
        waitForPageToLoad();
        waitForElementVisibilityOfElementLocatedBy(button_closeTabsBy, 10);
        waitForNumberOfElementsToBeMoreThanBy(button_closeTabsBy, 3, 15);
        try {
            if(!waitForNumberOfElementsToBe(button_closeTabsBy, 0, 5)) {
                for (WebElement close : button_closeTabs) {
                    clickElementVisible(close, 10);
                }
            }
        }
        catch (Exception e) {
            if (waitForNumberOfElementsToBeMoreThanBy(button_closeTabsBy, 0, 5)) {
                closeOpenTabs();
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
