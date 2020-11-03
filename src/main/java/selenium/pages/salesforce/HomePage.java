package selenium.pages.salesforce;

import base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends BasePage{
    @FindBy(how = How.CSS, using = ".uiAutocomplete")
    private WebElement input_SearchBar;

    @FindBy(how = How.CSS, using = "li[role='presentation'] svg[data-key='close']")
    private WebElement button_CloseTabs;

    public void IsSearchBarVisible()
    {
        waitForElementVisibility(input_SearchBar, 30);
    }
}
