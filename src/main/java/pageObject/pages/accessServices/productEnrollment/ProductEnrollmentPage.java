package pageObject.pages.accessServices.productEnrollment;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.util.List;

public class ProductEnrollmentPage extends CommonFunctions {

    @FindBy(xpath = "//*[@title='New Care Team Member']")
    private WebElement buttonNewCareTeamMember;

    @FindBy(xpath = "//li[@title='Care Team']/a")
    private WebElement labelCareTeamTabOption;

    @FindBy(xpath = "(//*[contains(text(),'Cases')]/following::*//a[@title='New Case'])[last()]")
    private WebElement buttonNewCase;

    @FindBy(xpath = "//div[contains(@class,'truncate')]//slot[@name='primaryField']//lightning-formatted-text")
    private WebElement labelProductEnrollmentNumber;

    @FindBy(xpath = "//*[@data-component-id='ACS_ConsentWarning']//p")
    private WebElement messageMsgNoDSIConsent;

    @FindBy(xpath = "//li[@title='Attestations']/a")
    private WebElement labelAttestationTabOption;

    @FindBy(xpath = "//*[@title='New Consent']//button")
    private WebElement buttonNewConsent;

    @FindBy(xpath = "//input[contains(@placeholder,'Search this')]")
    private WebElement inputPmSearchList;

    @FindBy(xpath = "//*[contains(text(),'No items to display')]")
    private WebElement labelPmNoItemsMessage;

    @FindBy(xpath = "//slot[@slot='primaryField']")
    private WebElement labelPmProductEnrollment;

    @FindBy(xpath = "//*[@id='brandBand_1']//tbody/tr[1]")
    private WebElement tableRowPmFirstRow;

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

    public boolean isProductEnrollmentPageDisplayed() throws Exception {
        return waitForElementVisibility(buttonNewCareTeamMember, mediumWait());
    }


    public void clickNewCareTeamMember() throws Exception {
        switchSubTabByIndexSF(0, shortWait());
        switchSubTabByIndexSF(1, shortWait());
        int exitCounter = 2;
        for (int i = 0; i < exitCounter; i++) {
            if (!waitForElementClickable(labelAttestationTabOption, shortWait())) {
                logger.info("Waiting For Attestation To Be Available");
            } else {
                clickMethodsWebElement(labelAttestationTabOption);
                waitForElementClickable(labelCareTeamTabOption, shortWait());
                clickMethodsWebElement(labelCareTeamTabOption);
                waitForElementVisibility(buttonNewCareTeamMember, mediumWait());
                clickElementClickable(buttonNewCareTeamMember, mediumWait());
                break;
            }
        }
    }


    public String getProductEnrollmentNumber() throws Exception {
        String statusOperation = "";
        waitForPageToLoad();
        if (waitForElementVisibility(labelProductEnrollmentNumber, longWait())) {
            statusOperation = getWebElementText(labelProductEnrollmentNumber);
        }
        return statusOperation;
    }


    /**
     * Method to validate the message when No DSI Consent has been created
     * <p>
     * warningNoDSIConsentMessage it will contains the message that will be validating when NO DSI consent has been created
     *
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public boolean validatePEDSIMessage(String messagePE) throws Exception {
        boolean statusOperation = false;
        waitForPageToLoad();
        if (waitForElementVisibility(messageMsgNoDSIConsent, shortWait())) {
            if (messageMsgNoDSIConsent.getText().trim().equalsIgnoreCase(messagePE.trim())) {
                logger.info("The Message: " + messagePE + "Matched");
                statusOperation = true;
            } else {
                logger.info("The Message: " + messagePE + "Did Not Matched");
            }
        } else {
            logger.info("No Warning Message Was Displayed");
        }
        return statusOperation;
    }

    public void createNewAttestationConsent() throws Exception {
        clickElementClickable(buttonNewConsent, longWait());
    }

    public void clickAttestationTab() throws Exception {
        clickElementClickable(labelAttestationTabOption, longWait());
    }


    /**
     * Used to click the New Case button and Product Enrollment Page
     *
     * @return
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void clickOnNewCase() throws Exception {
        clickAndMoveToElementClickable(buttonNewCase, longWait());
    }


    /**
     * Used to select the PE that will contains the specified product (drug)
     *
     * @param id_PE is the PE id used to search the PE
     * @return it returns a boolean value
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public boolean searchAndClickPEFromResults(String id_PE) throws Exception {
        boolean statusOperation = false;
        By labelList_pmServicesProvidedList = By.xpath("//tr//a[@title='" + id_PE + "']");
        waitForElementTextPresent(tableRowPmFirstRow, id_PE, mediumWait());
        List<WebElement> productNamesFound = getWebElementList(labelList_pmServicesProvidedList);
        if (!productNamesFound.isEmpty()) {
            for (int i = 0; i < productNamesFound.size(); i++) {
                if (getWebElementAttribute(productNamesFound.get(i), "title").equalsIgnoreCase(id_PE)) {
                    clickMethodsWebElement(productNamesFound.get(i));
                    while (!waitForElementInvisibilityOfElementLocatedBy(labelList_pmServicesProvidedList, shortWait())) {
                        productNamesFound = getWebElementList(labelList_pmServicesProvidedList);
                        clickMethodsWebElement(productNamesFound.get(i));
                    }
                    logger.info("The Product Enrollment Element was found");
                    statusOperation = true;
                    break;
                }
            }
        } else {
            if (labelPmNoItemsMessage.isDisplayed()) {
                logger.error("No items to display");
            }
        }
        return statusOperation;
    }

    public List<String> getProductEnrollmentList(String productEnrollment) throws Exception {
        return splitRegex(productEnrollment, Values.REGEX_COMMA);
    }
}