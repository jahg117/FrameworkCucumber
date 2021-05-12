package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;
import java.util.List;

public class PersonAccountPage extends CommonFunctions {
    @FindBy(xpath = "//slot[@name='primaryField']")
    private WebElement labelAccountPersonName;

    @FindBy(xpath = "//*[contains(text(),'Product Enrollments')]/following::*[@title='New']")
    private WebElement buttonNewProductEnrollment;

    @FindBy(xpath = "(//*[contains(text(),'Cases')]/following::*//a[@title='New Case'])[last()]")
    private WebElement buttonNewCase;

    @FindBy(xpath = "(//li[@title='Product Enrollments'])[last()]")
    private WebElement labelProductEnrollmentsTabOption;

    @FindBy(xpath = "(//li[@title='Cases'])[last()]")
    private WebElement labelCases;

    @FindBy(xpath = "//th[@data-label='Product Enrollment Number']//a/span")
    private List<WebElement> listProductEnrollmentNumber;

    @FindBy(xpath = "//td[@data-label='Product']")
    private List<WebElement> listProducts;

    @FindBy(xpath = "//td[@data-label='PAF']//*[contains(@src,'green')]")
    private List<WebElement> listGreenPAFIcon;

    @FindBy(xpath = "//td[@data-label='PAF']//*[contains(@src,'red')]")
    private List<WebElement> listRedPAFIcon;

    @FindBy(xpath = "//a[@data-label='Consent']")
    private WebElement tabConsent;

    @FindBy(xpath = "//*[@title='New Consent']")
    private WebElement buttonNewConsent;

    @FindBy(xpath = "//a[@data-label='Product Enrollments']")
    private WebElement tabProductEnrollment;

    @FindBy(xpath = "//p[@title='PEP ID']/..//lightning-formatted-text")
    private WebElement labelPepId;

    @FindBy(xpath = "//*[@title='Payer']")
    private WebElement tabButtonPayer;

    @FindBy(xpath = "//span[@title='Patient Insurances']/following::*[@name='New'][1]")
    private WebElement buttonNewPatientInsurances;

    @FindBy(xpath = "//span[normalize-space(text())='Logged out']")
    private WebElement buttonLoggedOut;


    private By buttonCloseSubTabs = By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//div[starts-with(@class,'close')]");

    private By linkViewAllProgramEnrollment = By.xpath("//*[contains(@href,'Enrollment')]//span[@class='view-all-label']");

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

    public boolean clickViewAllProgramEnrollments() throws Exception {
        boolean statusOperation = false;
        try {
            waitForPresenceOfAllElementsLocatedBy(linkViewAllProgramEnrollment, longWait());
            clickElementJS(getWebElement(linkViewAllProgramEnrollment));
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickViewAllProgramEnrollments")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickViewAllProgramEnrollments");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public boolean clickNewProductEnrollment() throws Exception {
        boolean statusOperation = false;
        try {
            switchToDefaultContentFrame();
            waitUntilVisibleLoop(buttonNewProductEnrollment, 2, mediumWait());
            clickAndMoveToElementClickable(buttonNewProductEnrollment, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickNewProductEnrollment")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickNewProductEnrollment");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public boolean clickNewCase() throws Exception {
        boolean statusOperation = false;
        try {
            waitForElementVisibility(buttonNewCase, longWait());
            scrollToWebElementJS(buttonNewCase);
            clickAndMoveToElementClickable(buttonNewCase, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickNewCase")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickNewCase");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public boolean clickNewCasePersonalAccountPage() throws Exception {
        boolean statusOperation = false;
        try {
            waitForElementVisibility(buttonNewCase, longWait());
            clickAndMoveToElementClickable(buttonNewCase, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickNewCasePersonalAccountPage")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickNewCasePersonalAccountPage");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }

        Values.globalCounter = 0;
        return statusOperation;
    }


    public String getPEPId() throws Exception {
        String statusOperation = "";
        try {
            waitForElementVisibility(buttonNewCase, longWait());
            waitForElementVisibility(labelPepId, mediumWait());
            statusOperation = getWebElementText(labelPepId);
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("getPEPId")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "getPEPId");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public boolean isRedIconDisplayed(String product) throws Exception {
        String newProduct = null;
        boolean statusOperation = false;
        try {
            waitForElementListVisible(listProducts, mediumWait());
            for (int i = 0; i < listProducts.size(); i++) {
                newProduct = listProducts.get(i).getText();
                if (newProduct.equalsIgnoreCase(product)) {
                    statusOperation = waitForElementVisibility(listRedPAFIcon.get(i), mediumWait());
                    break;
                }
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("isRedIconDisplayed")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "isRedIconDisplayed");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), product);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public String getProductEnrollmentNumber(String product) throws Exception {
        switchToTab(0);
        String newProduct = "";
        String statusOperation = "";
        try {
            waitForElementListVisible(listProducts, mediumWait());
            for (int i = 0; i < listProducts.size(); i++) {
                newProduct = listProducts.get(i).getText();
                if (newProduct.equalsIgnoreCase(product)) {
                    statusOperation = listProductEnrollmentNumber.get(i).getText();
                    break;
                }
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("getProductEnrollmentNumber")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "getProductEnrollmentNumber");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), product);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public String clickProductEnrollmentAdded(String product) throws Exception {
        boolean flag = false;
        String statusOperation = null;
        try {
            waitForElementListVisible(listProducts, mediumWait());
            for (int i = 0; i < listProducts.size(); i++) {
                statusOperation = listProducts.get(i).getText();
                if (statusOperation.equalsIgnoreCase(product)) {
                    clickElementVisible(listProductEnrollmentNumber.get(i), shortWait());
                    flag = true;
                    break;
                }
            }
            if (flag = false) {
                return "";
            } else {
                return statusOperation;
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method to click the button New Consent located at Person Page
     *
     * @return it returns a boolean value
     * @throws Exception
     * @author J.Ruano
     */
    public boolean clickOnNewConsent() throws Exception {
        boolean statusOperation = false;
        try {
            switchToDefaultContentFrame();
            clickAndMoveToElementVisible(tabConsent, mediumWait());
            clickAndMoveToElementVisible(buttonNewConsent, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method to change from one tab to another according to an index
     *
     * @param idxTab it contains an integer with the number of the tab related to a list that contains all the corrently open tabs
     * @return
     * @throws Exception
     * @author J.Ruano
     */
    public boolean switchToTab(int idxTab) throws Exception {
        boolean statusOperation = false;
        try {
            switchSubTabByIndexSF(0, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method to click the program enrollment from Program Enrollments page
     *
     * @return it returns a boolean value
     * @throws Exception
     * @author J.Ruano
     */
    public boolean clickOnProgramEnrollments() throws Exception {
        boolean statusOperation = false;
        try {
            clickAndMoveToElementVisible(tabProductEnrollment, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    public boolean filterConsentWarningMessages(String consentTypeForm) throws Exception {
        boolean statusOperation = false;
        try {
            clickAndMoveToElementVisible(tabProductEnrollment, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method to click the Payer Tab Person Account
     *
     * @return it returns a boolean value
     * @throws Exception
     * @author J.Ruano
     */
    public boolean clickPayerTab() throws Exception {
        boolean statusOperation = false;
        try {
            clickAndMoveToElementClickable(tabButtonPayer, mediumWait());
            clickProductEnrollment();
            clickAndMoveToElementClickable(tabButtonPayer, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }


    /**
     * Method to click the Product Enrollment Tab
     *
     * @return it returns a boolean value
     * @throws Exception
     * @author J.Ruano
     */
    public void clickProductEnrollment() throws Exception {
        clickAndMoveToElementClickable(tabProductEnrollment, mediumWait());
    }


    /**
     * Method to click the New button to create a Insurance
     *
     * @return it returns a boolean value
     * @throws Exception
     * @author J.Ruano
     */
    public void clickNewPatientInsurances() throws Exception {
        clickAndMoveToElementClickable(buttonNewPatientInsurances, mediumWait());
    }
}