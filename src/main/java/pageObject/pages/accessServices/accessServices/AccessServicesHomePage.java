package pageObject.pages.accessServices.accessServices;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageObject.application.Salesforce;
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

    @FindBy(xpath = "//input[@placeholder='Search Accounts and more...']")
    private WebElement labelSearchCases;

    @FindBy(xpath = "//div[contains(@class, 'lookup__menu')]//ul[contains(@class, 'visible')]")
    private WebElement labelResultCases;

    @FindBy(xpath = "//div[contains(@class, 'lookup__menu')]//ul[contains(@class, 'visible')]//div[contains(@class, 'mruDescription')]")
    private List<WebElement> listSearchResults;

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

    public void searchPatient(String patientId) throws Exception {
        sendKeysElementClickable(labelSearchCases, patientId, mediumWait());
        waitForElementClickable(labelResultCases, shortWait());
        waitForElementListVisible(listSearchResults, shortWait());
        for(WebElement el : listSearchResults) {
            if(getWebElementText(el).contains(patientId)) {
                clickElementVisible(el, shortWait());
                break;
            }
        }
    }

    public boolean isAccessServicesTitleVisible() throws Exception {
        return waitForElementVisibility(labelAccessServicesTitle, longWait());
    }

    public void selectMenuOption(String menuOption) throws Exception {
        waitForPageToLoad();
        try {
            if (waitForElementVisibility(labelNavigationName, mediumWait())) {
                if (!labelNavigationName.getAttribute("title").trim().equalsIgnoreCase(menuOption.trim())) {
                    clickElementVisible(buttonNavigationMenu, mediumWait());
                    if (!waitForElementVisibility(listNavigationMenu, mediumWait())) {
                        clickElementVisible(buttonNavigationMenu, mediumWait());
                        waitForElementVisibility(listNavigationMenu, longWait());
                    }
                    if (Values.ENVIRONMENT.equalsIgnoreCase(Values.TXT_UAT)) {
                        clickAndMoveToElementVisible(getWebElementByAttributeFromList(listNavigationOptions, Values.ATR_DATALABEL, menuOption), mediumWait());
                    } else {
                        clickAndMoveToElementVisible(getWebElementByAttributeFromList(listNavigationOptions, Values.ATR_DATALABEL, menuOption), mediumWait());
                    }
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
