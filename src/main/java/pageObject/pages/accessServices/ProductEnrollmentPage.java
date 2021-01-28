package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductEnrollmentPage extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "//*[@title='New Care Team Member']")
    private WebElement button_newCareTeamMember;

    @FindBy(xpath = "//div[contains(@class,'truncate')]//slot[@name='primaryField']//lightning-formatted-text")
    private WebElement label_productEnrollmentNumber;

    @FindBy(xpath = "//*[@data-component-id='ACS_ConsentWarning']//p")
    private WebElement message_msgNoDSIConsent;

    public boolean isProductEnrollmentPageDisplayed() {
        return waitForElementVisibility(button_newCareTeamMember, 30);
    }

    public String getProductEnrollmentNumber() {
        if(waitForElementVisibility(label_productEnrollmentNumber, 10)){
            return label_productEnrollmentNumber.getText();
        }else{
            return "";
        }
    }

    /**
     * Method to validate the message when No DSI Consent has been created
     * <p>
     * warningNoDSIConsentMessage it will contains the message that will be validating when NO DSI consent has been created
     *
     * @throws Exception
     * @author J.Ruano
     */
    public boolean validatePEDSIMessage(String messagePE) throws Exception {
        boolean result = false;
        waitForPageToLoad();
        if (message_msgNoDSIConsent.getText().trim().equalsIgnoreCase(messagePE.trim())) {
            logger.info("The Message: " + messagePE + "Matched");
            result = true;
        } else {
            logger.info("The Message: " + messagePE + "Did Not Matched");
        }
        return result;
    }
}