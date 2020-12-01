package pageObject.pages.salesforce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.functions.CommonFunctions;

public class HomePage extends CommonFunctions {
    @FindBy(css = ".uiAutocomplete")
    private WebElement input_SearchBar;

    @FindBy(css = "li[role='presentation'] svg[data-key='close']")
    private WebElement button_CloseTabs;

    public void IsSearchBarVisible()
    {
        waitForElementVisibility(input_SearchBar, 30);
    }
}
