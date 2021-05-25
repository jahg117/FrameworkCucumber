package pageObject.pages.accessServices.accessServices;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.util.List;

public class AccessServicesHomePage extends CommonFunctions {
    @FindBy(css = ".slds-truncate[title='Access Services']")
    private WebElement labelAccessServicesTitle;

    @FindBy(css = ".appLauncher")
    private WebElement appLauncherButton;

    @FindBy(css = "input.slds-input[placeholder^='Search app']")
    private WebElement appLaucherSearchBar;

    @FindBy(css = "button[title='Show Navigation Menu']")
    private WebElement buttonNavigationMenu;

    @FindBy(css = "#navMenuList")
    private WebElement listNavigationMenu;

    @FindBy(css = "#navMenuList a.menuItem")
    private List<WebElement> listNavigationOptions;

    @FindBy(css = "a[title='New']")
    private WebElement buttonNewAccount;

    @FindBy(xpath = "//*[starts-with(@class,'selectedListItem')]/a")
    private WebElement labelNavigationName;

    protected FileReading fileReading = new FileReading();
    private final Logger logger = Logger.getLogger(CommonFunctions.class);
    public static int maxNumberOfTries = 0;

    Class<?> myClass;

    {
        try {
            fileReading.setLog4jFile();
            fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
            maxNumberOfTries = Integer.parseInt(fileReading.getField(Values.TXT_RETRYWHILE));
            myClass = Class.forName(Values.REFLECTION_COMMONFUNCTIONSCLASSPATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void clickNewAccount() throws Exception {
        clickAndMoveToElementClickable(buttonNewAccount, longWait());
    }


    public boolean isAccessServicesTitleVisible() throws Exception {
        return waitForElementVisibility(labelAccessServicesTitle, longWait());
    }

    public void selectMenuOption(String menuOption) throws Exception {
        waitForPageToLoad();
        if (waitForElementVisibility(labelNavigationName, mediumWait())) {
            if (!labelNavigationName.getAttribute("title").trim().equalsIgnoreCase(menuOption.trim())) {
                clickElementVisible(buttonNavigationMenu, mediumWait());
                if (!waitForElementVisibility(listNavigationMenu, mediumWait())) {
                    clickElementVisible(buttonNavigationMenu, mediumWait());
                    waitForElementVisibility(listNavigationMenu, longWait());
                }
                clickAndMoveToElementVisible(getWebElementByAttributeFromList(listNavigationOptions, "title", menuOption), mediumWait());
            }
        }
    }
}
