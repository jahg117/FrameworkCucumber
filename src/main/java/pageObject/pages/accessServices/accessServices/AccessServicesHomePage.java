package pageObject.pages.accessServices.accessServices;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;
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

    protected FileReading fileReading = new FileReading();
    private final Logger logger = Logger.getLogger(CommonFunctions.class);
    public static int maxNumberOfTries = 0;

    Class<?> myClass;

    {
        try {
            fileReading.setLog4jFile();
            fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
            maxNumberOfTries = Integer.parseInt(fileReading.getField(Values.TXT_RETRYWHILE));
            myClass = Class.forName("base.functions" + "." + "CommonFunctions");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean clickNewAccount() throws Exception {
        boolean statusOperation = false;
        try {
            statusOperation = clickAndMoveToElementClickable(button_NewAccount, longWait());
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickNewAccount")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickNewAccount");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public boolean isAccessServicesTitleVisible() throws Exception {
        boolean statusOperation = false;
        try {
            statusOperation = waitForElementVisibility(label_accessServicesTitle, longWait());
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("isAccessServicesTitleVisible")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "isAccessServicesTitleVisible");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public boolean selectMenuOption(String menuOption) throws Exception {
        boolean statusOperation = false;
        try {
            waitForPageToLoad();
            if (waitForElementVisibility(label_navigationName, mediumWait())) {
                if (!label_navigationName.getAttribute("title").trim().equalsIgnoreCase(menuOption.trim())) {
                    clickElementVisible(button_navigationMenu, mediumWait());
                    if (!waitForElementVisibility(list_navigationMenu, mediumWait())) {
                        clickElementVisible(button_navigationMenu, mediumWait());
                        waitForElementVisibility(list_navigationMenu, longWait());
                    }
                    clickAndMoveToElementVisible(getWebElementByAttributeFromList(list_navigationOptions, "title", menuOption), mediumWait());
                }
                statusOperation = true;
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("selectMenuOption")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "selectMenuOption");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), menuOption);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }
}
