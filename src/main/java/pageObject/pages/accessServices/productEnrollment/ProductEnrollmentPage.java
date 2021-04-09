package pageObject.pages.accessServices.productEnrollment;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductEnrollmentPage extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "//*[@title='New Care Team Member']")
    private WebElement button_newCareTeamMember;

    @FindBy(xpath = "(//*[contains(text(),'Cases')]/following::*//a[@title='New Case'])[last()]")
    private WebElement button_newCase;

    @FindBy(xpath = "//div[contains(@class,'truncate')]//slot[@name='primaryField']//lightning-formatted-text")
    private WebElement label_productEnrollmentNumber;

    @FindBy(xpath = "//*[@data-component-id='ACS_ConsentWarning']//p")
    private WebElement message_msgNoDSIConsent;

    @FindBy(xpath = "//li[@title='Attestations']/a")
    private WebElement label_attestationTabOption;

    @FindBy(xpath = "//*[@title='New Consent']//button")
    private WebElement button_newConsent;

    @FindBy(xpath = "//input[contains(@placeholder,'Search this')]")
    private WebElement input_pmSearchList;

    @FindBy(xpath = "//*[contains(text(),'No items to display')]")
    private WebElement label_pmNoItemsMessage;

    @FindBy(xpath = "//slot[@slot='primaryField']")
    private WebElement label_pmProductEnrollment;

    @FindBy(xpath = "//*[@id='brandBand_1']//tbody/tr[1]")
    private WebElement tableRow_pmFirstRow;


    public boolean isProductEnrollmentPageDisplayed() {
        return waitForElementVisibility(button_newCareTeamMember, 30);
    }

    public void clickNewCareTeamMember() throws Exception {
        waitForElementVisibility(button_newCareTeamMember, 20);
        try {
            clickElementClickable(button_newCareTeamMember, 20);
        } catch (Exception e) {
            clickElementClickable(button_newCareTeamMember, 20);
        }
    }

    public String getProductEnrollmentNumber() {
        if (waitForElementVisibility(label_productEnrollmentNumber, 10)) {
            return getWebElementText(label_productEnrollmentNumber);
        } else {
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
        if (waitForElementVisibility(message_msgNoDSIConsent,shortWait())) {
            if (message_msgNoDSIConsent.getText().trim().equalsIgnoreCase(messagePE.trim())) {
                logger.info("The Message: " + messagePE + "Matched");
                result = true;
            } else {
                logger.info("The Message: " + messagePE + "Did Not Matched");
            }
        }else{
            logger.info("No Warning Message Was Displayed");
        }
        return result;
    }

    public void createNewAttestationConsent() throws Exception {
        clickElementClickable(button_newConsent, 30);
    }

    /**
     * Used to click the New Case button and Product Enrollment Page
     *
     * @throws Exception
     * @author J.Ruano
     */
    public void clickOnNewCase() throws Exception {
        clickAndMoveToElementClickable(button_newCase, longWait());
    }

    /**
     * Used to select the PE that will contains the specified product (drug)
     *
     * @param id_PE is the PE id used to search the PE
     * @return it returns a boolean value
     * @throws Exception
     * @author J.Ruano
     */


    public boolean searchAndClickPEFromResults(String id_PE) throws Exception {
        boolean statusOperation = false;
        By labelList_pmServicesProvidedList = By.xpath("//tr//a[@title='" + id_PE + "']");
       try {
            waitForElementTextPresent(tableRow_pmFirstRow, id_PE, mediumWait());
            List<WebElement> productNamesFound = getWebElementList(labelList_pmServicesProvidedList);
            if (!productNamesFound.isEmpty()) {
                for (WebElement product : productNamesFound) {
                        waitForElementVisibility(product, shortWait());
                        clickAndMoveToElementClickable(product, shortWait());
                        logger.info("The Product Enrollment Element was found");
                        statusOperation = true;
                        break;
                }
            } else {
                if (label_pmNoItemsMessage.isDisplayed()) {
                    logger.error("No items to display");
                }
            }
        } catch (Exception e) {
            logger.error("The Product Enrollment WebElement was not found");
            new NoSuchElementException("The WebElement was not found");
        }
        return statusOperation;
    }
}