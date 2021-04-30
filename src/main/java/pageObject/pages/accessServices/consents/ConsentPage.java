package pageObject.pages.accessServices.consents;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;
import java.util.List;

public class ConsentPage extends CommonFunctions {
    @FindBy(xpath = "//button[@name='Global.NewContact']")
    private WebElement button_newContact;

    @FindBy(xpath = "//*[contains(text(),'Consent No.')]/following::*[@data-output-element-id='output-field']")
    private List<WebElement> list_consentDetails;

    @FindBy(xpath = "//span[contains(text(),'Consent No.') and contains(@class,'test-id')]/../..//*[@data-output-element-id]")
    private WebElement label_consentID;

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

    public String getConsentID() throws Exception {
        String statusOperation = "";
        try {
            waitForElementVisibility(label_consentID, mediumWait());
            statusOperation = getWebElementText(label_consentID);
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("getConsentID")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "getConsentID");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public boolean isConsentPageDisplayed() throws Exception {
        boolean statusOperation = false;
        try {
            statusOperation = waitForElementClickable(button_newContact, longWait());
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("isConsentPageDisplayed")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "isConsentPageDisplayed");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public boolean isConsentDetailDisplayed(String consentDetail) throws Exception {
        boolean statusOperation = false;
        try {
            for (WebElement el : list_consentDetails) {
                String consent = getWebElementText(el);
                if (consent.contains(consentDetail)) {
                    statusOperation = true;
                    break;
                }
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("isConsentDetailDisplayed")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "isConsentDetailDisplayed");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), consentDetail);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }
}
