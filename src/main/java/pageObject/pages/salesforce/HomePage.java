package pageObject.pages.salesforce;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.functions.CommonFunctions;
import java.util.List;

public class HomePage extends CommonFunctions {
    @FindBy(css = ".uiAutocomplete")
    private WebElement inputSearchBar;

    @FindBy(xpath = "//div[contains(@class,'secondary')]//div[starts-with(@class,'close')]")
    private List<WebElement> buttonCloseTabs;
    private By buttonCloseTabsBy = By.xpath("//div[contains(@class,'secondary')]//div[starts-with(@class,'close')]");

    @FindBy(xpath = "//div[contains(@class,'lafPageHost')]")
    private WebElement labelPanelTab;

    public boolean isSalesforcePageVisible() throws Exception {
        waitForPageToLoad();
        waitForElementVisibility(inputSearchBar, longWait());
        return waitForElementAttributeContains(labelPanelTab, "data-aura-class", "lafPageHost", longWait());
    }

    public void closeOpenTabs() throws Exception {
        waitForPageToLoad();
        if (waitForNumberOfElementsToBeMoreThanBy(buttonCloseTabsBy, 0, shortWait())) {
            try {
                for (WebElement close : getWebElementList(buttonCloseTabsBy)) {
                    clickElementVisible(close, shortWait());
                }
                if (waitForNumberOfElementsToBeMoreThanBy(buttonCloseTabsBy, 0, shortWait())) {
                    closeOpenTabs();
                }
            } catch (Exception e) {
                if (waitForNumberOfElementsToBeMoreThanBy(buttonCloseTabsBy, 0, shortWait())) {
                    closeOpenTabs();
                }
            }
        }
    }

    public void closePreviousTab() throws Exception {
        waitForPageToLoad();
        if (waitForElementListVisible(buttonCloseTabs, mediumWait()) && buttonCloseTabs.size() > 0) {
            clickElementVisible(buttonCloseTabs.get(buttonCloseTabs.size() - 2), mediumWait());
        }
    }
}
