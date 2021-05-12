package pageObject.pages.salesforce;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.lang.reflect.Method;

import utils.Values;

public class AppLauncherPage extends CommonFunctions {

    @FindBy(xpath = "//*[@class='appLauncher slds-context-bar__icon-action']")
    private WebElement button_AppLauncher;

    @FindBy(xpath = "//*[@placeholder='Search apps and items...']")
    private WebElement input_AppLauncher;

    @FindBy(xpath = "//*[starts-with(@class,'appName')]/span[@title]")
    private WebElement label_AppNameTitle;

    private final Logger logger = Logger.getLogger(CommonFunctions.class);

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

    public boolean searchAppName(String appName) throws Exception {
        boolean statusOperation = false;
        try {
            waitForElementFluentMinutes(button_AppLauncher, 2, 1);
            if (!label_AppNameTitle.getAttribute("title").trim().equalsIgnoreCase(appName)) {
                clickAndMoveToElementVisible(button_AppLauncher, mediumWait());
                if (!waitForElementVisibility(input_AppLauncher, mediumWait())) {
                    clickAndMoveToElementVisible(button_AppLauncher, mediumWait());
                }
                sendKeysElementVisible(input_AppLauncher, appName, mediumWait());
                sendKeysElementVisible(input_AppLauncher, Keys.ENTER.toString(), mediumWait());
                waitForElementNotVisible(input_AppLauncher, mediumWait());
                if (waitForElementVisibility(label_AppNameTitle, mediumWait())) {
                    statusOperation = true;
                } else {
                    statusOperation = false;
                }
            } else {
                statusOperation = true;
            }
        } catch (Exception e) {
        }
        return statusOperation;
    }
}