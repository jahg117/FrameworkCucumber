package pageObject.pages.salesforce;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Values;

public class AppLauncherPage extends CommonFunctions {

    @FindBy(xpath = "//*[@class='appLauncher slds-context-bar__icon-action']")
    private WebElement buttonAppLauncher;

    @FindBy(xpath = "//*[@placeholder='Search apps and items...']")
    private WebElement inputAppLauncher;

    @FindBy(xpath = "//*[starts-with(@class,'appName')]/span[@title]")
    private WebElement labelAppNameTitle;

    private final Logger logger = Logger.getLogger(CommonFunctions.class);

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

    public boolean searchAppName(String appName) throws Exception {
        boolean statusOperation = false;
        waitForElementFluentMinutes(buttonAppLauncher, 2, 1);
        if (!labelAppNameTitle.getAttribute("title").trim().equalsIgnoreCase(appName)) {
            clickAndMoveToElementVisible(buttonAppLauncher, mediumWait());
            if (!waitForElementVisibility(inputAppLauncher, mediumWait())) {
                clickAndMoveToElementVisible(buttonAppLauncher, mediumWait());
            }
            sendKeysElementVisible(inputAppLauncher, appName, mediumWait());
            sendKeysElementVisible(inputAppLauncher, Keys.ENTER.toString(), mediumWait());
            waitForElementNotVisible(inputAppLauncher, mediumWait());
            if (waitForElementVisibility(labelAppNameTitle, mediumWait())) {
                statusOperation = true;
            } else {
                statusOperation = false;
            }
        } else {
            statusOperation = true;
        }
        return statusOperation;
    }
}