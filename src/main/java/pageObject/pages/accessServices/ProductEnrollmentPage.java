package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductEnrollmentPage extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    int shortTimeOutInSeconds = 10;

    @FindBy(xpath = "//*[@title='New Care Team Member']")
    private WebElement button_newCareTeamMember;

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

    @FindBy(xpath = "//a[@title='New Case']")
    private WebElement button_pmNewCase;

    @FindBy(xpath = "//*[@id='brandBand_1']//tbody/tr[1]")
    private WebElement tableRow_pmFirstRow;


    public boolean isProductEnrollmentPageDisplayed() {
        return waitForElementVisibility(button_newCareTeamMember, 30);
    }

    public void clickNewCareTeamMember() throws Exception {
        clickElementClickable(button_newCareTeamMember, 20);
    }

    public String getProductEnrollmentNumber() {
        if (waitForElementVisibility(label_productEnrollmentNumber, 10)) {
            return label_productEnrollmentNumber.getText();
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
        if (message_msgNoDSIConsent.getText().trim().equalsIgnoreCase(messagePE.trim())) {
            logger.info("The Message: " + messagePE + "Matched");
            result = true;
        } else {
            logger.info("The Message: " + messagePE + "Did Not Matched");
        }
        return result;
    }

    public void createNewAttestationConsent() throws Exception {
        clickElementClickable(label_attestationTabOption, 10);
        clickElementClickable(button_newConsent, 10);
    }

    /**
     * Used to click the New Case button and Product Enrollment Page
     *
     * @throws Exception
     * @author J.Ruano
     */
    public void clickOnNewCase() throws Exception {
        waitForElementVisibility(label_pmProductEnrollment, shortTimeOutInSeconds);
        clickAndMoveToElementClickable(button_pmNewCase, shortTimeOutInSeconds);
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
            waitForElementTextPresent(tableRow_pmFirstRow, id_PE, 10);
            List<WebElement> productNamesFound = getWebElementList(labelList_pmServicesProvidedList);
            if (!productNamesFound.isEmpty()) {
                for (WebElement product : productNamesFound) {
                        waitForElementVisibility(product, shortTimeOutInSeconds);
                        clickAndMoveToElementClickable(product, shortTimeOutInSeconds);
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