package pageObject.pages.accessServices.consents;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Locale;

public class ConsentPage extends CommonFunctions {
    @FindBy(xpath = "//img[@title='Consent']")
    private WebElement buttonNewContact;

    @FindBy(xpath = "//*[contains(text(),'Consent No.')]/following::*[@data-output-element-id='output-field']")
    private List<WebElement> listConsentDetails;

    @FindBy(xpath = "//span[contains(text(),'Consent No.') and contains(@class,'test-id')]/../..//*[@data-output-element-id]")
    private WebElement labelConsentID;

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

    public String getConsentID() throws Exception {
        waitForElementVisibility(labelConsentID, mediumWait());
        return getWebElementText(labelConsentID);
    }

    public boolean isConsentPageDisplayed() throws Exception {
       return waitForElementClickable(buttonNewContact, longWait());
    }


    public boolean isConsentDetailDisplayed(String consentDetail) throws Exception {
        boolean statusOperation = false;
        try {
            for (WebElement el : listConsentDetails) {
                String consent = getWebElementText(el);
                if (consent.toLowerCase().contains(consentDetail.toLowerCase())) {
                    statusOperation = true;
                    break;
                }
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }

    public String consentTypeFilter(String consentType) throws Exception {
        switch (consentType.trim().toUpperCase()) {
            case "DFM":
                consentType = Values.TXT_DFM;
                break;
            case "DCC":
                consentType = Values.TXT_DCC;
                break;
            case "APC":
                consentType = Values.TXT_APC;
                break;
            case "DPC":
                consentType = Values.TXT_DPC;
                break;
            case "AFP":
                consentType = Values.TXT_AFP;
                break;
            case "ANP":
                consentType = Values.TXT_ANP;
                break;
            case "DNC":
                consentType = Values.TXT_DNC;
                break;
            case "ACC":
                consentType = Values.TXT_ACC;
                break;
        }
        return consentType;
    }
}
