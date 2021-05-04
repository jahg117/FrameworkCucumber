package pageObject.pages.accessServices.productEnrollment;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;
import java.util.List;

public class ProductEnrollmentPage extends CommonFunctions {

    @FindBy(xpath = "//*[@title='New Care Team Member']")
    private WebElement button_newCareTeamMember;

    @FindBy(xpath = "//li[@title='Care Team']/a")
    private WebElement label_careTeamTabOption;

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

    public boolean isProductEnrollmentPageDisplayed() throws Exception {
        boolean statusOperation = false;
        try {
            statusOperation = waitForElementVisibility(button_newCareTeamMember, mediumWait());
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("isProductEnrollmentPageDisplayed")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "isProductEnrollmentPageDisplayed");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public boolean clickNewCareTeamMember() throws Exception {
        boolean statusOperation = false;
        try {
            switchSubTabByIndexSF(0, shortWait());
            switchSubTabByIndexSF(1, shortWait());
            int exitCounter = 2;
            for (int i = 0; i < exitCounter; i++) {
                if (!waitForElementClickable(label_attestationTabOption, shortWait())) {
                    logger.info("Waiting For Attestation To Be Available");
                } else {
                    clickElementJS(label_attestationTabOption);
                    waitForElementClickable(label_careTeamTabOption, shortWait());
                    clickElementJS(label_careTeamTabOption);
                    waitForElementVisibility(button_newCareTeamMember, mediumWait());
                    clickElementClickable(button_newCareTeamMember, mediumWait());
                    statusOperation = true;
                    break;
                }
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickNewCareTeamMember")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickNewCareTeamMember");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public String getProductEnrollmentNumber() throws Exception {
        String statusOperation = "";
        waitForPageToLoad();
        try {
            if (waitForElementVisibility(label_productEnrollmentNumber, mediumWait())) {
                statusOperation = getWebElementText(label_productEnrollmentNumber);
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("getProductEnrollmentNumber")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "getProductEnrollmentNumber");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
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
        boolean statusOperation = false;
        waitForPageToLoad();
        try {
            if (waitForElementVisibility(message_msgNoDSIConsent, shortWait())) {
                if (message_msgNoDSIConsent.getText().trim().equalsIgnoreCase(messagePE.trim())) {
                    logger.info("The Message: " + messagePE + "Matched");
                    statusOperation = true;
                } else {
                    logger.info("The Message: " + messagePE + "Did Not Matched");
                }
            } else {
                logger.info("No Warning Message Was Displayed");
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("validatePEDSIMessage")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "validatePEDSIMessage");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), messagePE);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public void createNewAttestationConsent() throws Exception {
        clickElementClickable(button_newConsent, longWait());
    }


    /**
     * Used to click the New Case button and Product Enrollment Page
     *
     * @return
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
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("searchAndClickPEFromResults")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "searchAndClickPEFromResults");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), id_PE);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }
}