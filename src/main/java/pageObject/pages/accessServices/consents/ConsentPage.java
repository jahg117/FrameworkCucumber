package pageObject.pages.accessServices.consents;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "//span[contains(text(),'Consent Expiration Date') and contains(@class,'test-id')]/../..//*[@data-output-element-id]")
    private WebElement labelConsentExpDate;

    @FindBy(xpath = "//span[contains(text(),'Consent Date') and contains(@class,'test-id')]/../..//*[@data-output-element-id]")
    private WebElement labelConsentDate;

    @FindBy(xpath = "//*[@title='Related']")
    private WebElement relatedTab;

    @FindBy(xpath = "//*[normalize-space(text())='Select Files']")
    private WebElement selectFileLabel;

    @FindBy(xpath = "//div[@class='panelBody']/..//*[@type='search']")
    private WebElement searchFileForAttachment;

    @FindBy(xpath = "//span[@title='Files']/following::a[@title='Add Files']")
    private WebElement addFilesButton;

    @FindBy(xpath = "//*[normalize-space(text())='Select Files']/following::*[@class='filerow']")
    private List<WebElement> addFileList;

    @FindBy(xpath = "//button//span[starts-with(text(),'Add')][not(@role)]")
    private WebElement addFileButton;

    @FindBy(xpath = "//span[contains(text(),'State') and contains(@class,'test-id')]/../..//*[@data-output-element-id]")
    private WebElement labelConsentState;

    @FindBy(xpath = "//*[@data-key='clear']")
    private WebElement clearButton;

    @FindBy(xpath = "//div[@class='searchText']")
    private WebElement noResultFound;

    @FindBy(xpath = "//*[@data-refid='divId']")
    private WebElement fileAdded;

    protected FileReading fileReading = new FileReading();
    CommonFunctions commonFunctions = new CommonFunctions();
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

    public String consentTypeFilter(String consentType, String consent) throws Exception {
        if (consentType.trim().equalsIgnoreCase(Values.TXT_RANDOM)) {
            consentType = rndConsentType(consent);
        }
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

    public int getConsentExpDateYear() throws Exception {
        waitForElementVisibility(labelConsentExpDate, mediumWait());
        String ConsentExpDate = labelConsentExpDate.getText();
        String[] ConsentExpDateYear = ConsentExpDate.split("/");
        return Integer.parseInt(ConsentExpDateYear[2]);
    }

    public int getConsentDateYear() throws Exception {
        waitForElementVisibility(labelConsentDate, mediumWait());
        String ConsentDate = labelConsentDate.getText();
        String[] ConsentDateYear = ConsentDate.split("/");
        return Integer.parseInt(ConsentDateYear[2]);
    }

    public boolean getConsentDateValidation() throws Exception {
        waitForElementVisibility(labelConsentState, mediumWait());
        if (getWebElementText(labelConsentState).equalsIgnoreCase("MD") || getWebElementText(labelConsentState).equalsIgnoreCase("MN")) {
            if (getConsentExpDateYear() - getConsentDateYear() == 1) {
                return true;
            } else {
                return false;
            }
        } else {
            if (getConsentExpDateYear() - getConsentDateYear() == 2) {
                return true;
            } else {
                return false;
            }
        }
    }

    public String rndConsentType(String typeOfConsent) throws Exception {
        String consentType = "";
        switch (typeOfConsent.trim().toUpperCase()) {
            case "AZ":
                consentType = Values.ARRAY_AZTYPES_CODES[getRandomNumberByLimits(0, Values.ARRAY_AZTYPES_CODES.length)];
                break;
            case "DSI":
                consentType = Values.ARRAY_DSITYPES_CODES[getRandomNumberByLimits(0, Values.ARRAY_DSITYPES_CODES.length)];
                break;
        }
        return consentType;
    }

    public void attachfileToConsent(String typeOfAttachment) throws Exception {
        try {
            if (typeOfAttachment.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                logger.info(Values.TXT_NOINSURANCE);
            } else {
                if (waitForElementVisibility(relatedTab, mediumWait())) {
                    commonFunctions.clickAndMoveToElementClickable(relatedTab, shortWait());
                    if (waitForElementVisibility(relatedTab, mediumWait())) {
                        commonFunctions.clickAndMoveToElementClickable(addFilesButton, shortWait());
                    }
                    if (waitForElementVisibility(selectFileLabel, mediumWait())) {
                        switch (typeOfAttachment.trim().toUpperCase()) {
                            case "P1":
                            case "RND":
                                typeOfAttachment = Values.ARRAY_FILESATTACHMENTNAMES[getRandomNumberByLimits(2, Values.ARRAY_FILESATTACHMENTNAMES.length)];
                                typeOfAttachment = typeOfAttachment.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.TXT_EMPTY);
                                break;
                            case "P2":
                            case "P3":
                            case "P4":
                            case "P5":
                                typeOfAttachment = searchIntoArray(typeOfAttachment, Values.ARRAY_FILESATTACHMENTNAMES);
                                typeOfAttachment = typeOfAttachment.replaceAll(Values.REGEX_REPLACEINDEXLABEL, Values.REPLACETO_EMPTY);
                                break;
                            default:
                                logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                                break;
                        }
                        if (waitForElementClickable(searchFileForAttachment,shortWait())) {
                            sendKeysAndMoveToElement(searchFileForAttachment, typeOfAttachment);
                        }
                        if (waitForElementToBeClickableBy(By.xpath("//*[normalize-space(text())='Select Files']/following::span[@title='" + typeOfAttachment + "']"),shortWait())) {
                            clickElementClickable(getWebElement(By.xpath("//*[normalize-space(text())='Select Files']/following::span[@title='" + typeOfAttachment + "']")), shortWait());
                        } else {
                            logger.warn(Values.TXT_NOFILEWASFOUND);
                            if (waitForElementClickable(clearButton, shortWait())) {
                                clickElementClickable(clearButton, shortWait());
                                if (waitForElementClickable(getRandomWebElementFromList(addFileList, shortWait()),shortWait())) {
                                    clickAndMoveToElementClickable((getRandomWebElementFromList(addFileList, shortWait())), shortWait());
                                }
                            }
                        }
                    }
                }
                clickAndMoveToElementClickable(addFileButton, shortWait());
                if (waitForElementClickable(fileAdded, shortWait())) {
                    logger.warn(Values.TXT_FILEADDED);
                }else{
                    logger.warn(Values.TXT_FILENOTADDED);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
