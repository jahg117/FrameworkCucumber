package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccessServicesHomePage extends CommonFunctions {
    @FindBy(css = ".slds-truncate[title='Access Services']")
    private WebElement label_accessServicesTitle;

    @FindBy(css = ".appLauncher")
    private WebElement appLauncher_Button;

    @FindBy(css = "input.slds-input[placeholder^='Search app']")
    private WebElement appLaucher_SearchBar;

    @FindBy(css = "button[title='Show Navigation Menu']")
    private WebElement button_navigationMenu;

    @FindBy(css = "#navMenuList")
    private WebElement list_navigationMenu;

    @FindBy(css = "#navMenuList a.menuItem")
    private List<WebElement> list_navigationOptions;

    @FindBy(css = "a[title='New']")
    private WebElement button_NewAccount;

    @FindBy(xpath = "//*[starts-with(@class,'selectedListItem')]/a")
    private WebElement label_navigationName;


    public void clickNewAccount() throws Exception {
        clickAndMoveToElementClickable(button_NewAccount, 30);
    }

    public void isAccessServicesTitleVisible() {
        waitForElementVisibility(label_accessServicesTitle, 30);
    }

    public void selectMenuOption(String menuOption) throws Exception {
        waitForPageToLoad();
        if(waitForElementVisibility(label_navigationName, 20)){
            if (!label_navigationName.getAttribute("title").trim().equalsIgnoreCase(menuOption.trim())) {
                clickElementVisible(button_navigationMenu, 10);
                if (!waitForElementVisibility(list_navigationMenu, 10)) {
                    clickElementVisible(button_navigationMenu, 15);
                    waitForElementVisibility(list_navigationMenu, 30);
                }
                clickAndMoveToElementVisible(getWebElementByAttributeFromList(list_navigationOptions, "title", menuOption), 15);
            }
        }
    }
}
