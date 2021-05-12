package pageObject.pages.accessServices.consents;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;


public class NewConsentPage extends CommonFunctions {
    @FindBy(xpath = "(//iframe[@title='accessibility title'])[last()]")
    private WebElement iframe_pageInformation;

    @FindBy(xpath = "//span[normalize-space(text())='New Consent']")
    private WebElement label_newConsent;

    @FindBy(xpath = "//*[@class='requiredInput']/following::select[@id]")
    private WebElement dropdown_consentType;

    @FindBy(xpath = "//*[@class='actionBtns']/input[@value='Next']")
    private WebElement button_next;

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

    /**
     * Method used to select the type of consent
     *
     * @param consentTypeOption it contains the type of consent to be select
     * @return
     * @throws Exception
     * @author J.Ruano
     */
    public boolean selectConsentType(String consentTypeOption) throws Exception {
        boolean statusOperation = false;
        try {
            switchToFrameByWebElementIndexOrName(iframe_pageInformation, longWait());
            waitForElementVisibility(dropdown_consentType, longWait());
            if (consentTypeOption.trim().equalsIgnoreCase("RND")) {
                selectDropDownRandomOptionNone(dropdown_consentType, longWait());
            } else {
                selectAndMoveDropdownByText(dropdown_consentType, consentTypeOption, longWait());
            }
            clickAndMoveToElementVisible(button_next, mediumWait());
            switchToParentFrame();
            statusOperation = true;
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }
}
