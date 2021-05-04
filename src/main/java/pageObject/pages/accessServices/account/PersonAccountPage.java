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
    private WebElement label_accountPersonName;

    @FindBy(xpath = "//*[contains(text(),'Product Enrollments')]/following::*[@title='New']")
    private WebElement button_newProductEnrollment;

    @FindBy(xpath = "(//*[contains(text(),'Cases')]/following::*//a[@title='New Case'])[last()]")
    private WebElement button_newCase;

    @FindBy(xpath = "(//li[@title='Product Enrollments'])[last()]")
    private WebElement label_productEnrollmentsTabOption;

    @FindBy(xpath = "(//li[@title='Cases'])[last()]")
    private WebElement label_cases;

    @FindBy(xpath = "//th[@data-label='Product Enrollment Number']//a/span")
    private List<WebElement> list_productEnrollmentNumber;

    @FindBy(xpath = "//td[@data-label='Product']")
    private List<WebElement> list_products;

    @FindBy(xpath = "//td[@data-label='PAF']//*[contains(@src,'green')]")
    private List<WebElement> list_greenPAFIcon;

    @FindBy(xpath = "//td[@data-label='PAF']//*[contains(@src,'red')]")
    private List<WebElement> list_redPAFIcon;

    @FindBy(xpath = "//a[@data-label='Consent']")
    private WebElement tab_Consent;

    @FindBy(xpath = "//*[@title='New Consent']")
    private WebElement button_newConsent;

    @FindBy(xpath = "//a[@data-label='Product Enrollments']")
    private WebElement tab_productEnrollment;

    @FindBy(xpath = "//p[@title='PEP ID']/..//lightning-formatted-text")
    private WebElement label_pepId;

    @FindBy(xpath = "//*[@title='Payer']")
    private WebElement tabButton_payer;

    @FindBy(xpath = "//span[@title='Patient Insurances']/following::*[@name='New'][1]")
    private WebElement button_newPatientInsurances;

    @FindBy(xpath = "//span[normalize-space(text())='Logged out']")
    private WebElement button_loggedOut;


    private By button_closeSubTabs = By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//div[starts-with(@class,'close')]");

    private By link_viewAllProgramEnrollment = By.xpath("//*[contains(@href,'Enrollment')]//span[@class='view-all-label']");

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
            waitForPresenceOfAllElementsLocatedBy(link_viewAllProgramEnrollment, longWait());
            clickElementJS(getWebElement(link_viewAllProgramEnrollment));
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
            waitUntilVisibleLoop(button_newProductEnrollment, 2, mediumWait());
            clickAndMoveToElementClickable(button_newProductEnrollment, mediumWait());
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
            waitForElementVisibility(button_newCase, longWait());
            scrollToWebElementJS(button_newCase);
            clickAndMoveToElementClickable(button_newCase, mediumWait());
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
            waitForElementVisibility(button_newCase, longWait());
            clickAndMoveToElementClickable(button_newCase, mediumWait());
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
            waitForElementVisibility(button_newCase, longWait());
            waitForElementVisibility(label_pepId, mediumWait());
            statusOperation = getWebElementText(label_pepId);
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
            waitForElementListVisible(list_products, mediumWait());
            for (int i = 0; i < list_products.size(); i++) {
                newProduct = list_products.get(i).getText();
                if (newProduct.equalsIgnoreCase(product)) {
                    statusOperation = waitForElementVisibility(list_redPAFIcon.get(i), mediumWait());
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
            waitForElementListVisible(list_products, mediumWait());
            for (int i = 0; i < list_products.size(); i++) {
                newProduct = list_products.get(i).getText();
                if (newProduct.equalsIgnoreCase(product)) {
                    statusOperation = list_productEnrollmentNumber.get(i).getText();
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
            waitForElementListVisible(list_products, mediumWait());
            for (int i = 0; i < list_products.size(); i++) {
                statusOperation = list_products.get(i).getText();
                if (statusOperation.equalsIgnoreCase(product)) {
                    clickElementVisible(list_productEnrollmentNumber.get(i), shortWait());
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
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickProductEnrollmentAdded")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickProductEnrollmentAdded");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), product);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    /**
     * Method to click the button New Consent located at Person Page
     *
     * @return
     * @throws Exception
     * @author J.Ruano
     */
    public boolean clickOnNewConsent() throws Exception {
        boolean statusOperation = false;
        try {
            switchToDefaultContentFrame();
            clickAndMoveToElementVisible(tab_Consent, mediumWait());
            clickAndMoveToElementVisible(button_newConsent, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickOnNewConsent")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickOnNewConsent");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
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
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("switchToTab")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "switchToTab");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), idxTab);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    /**
     * Method to click the program enrollment from Program Enrollments page
     *
     * @return
     * @throws Exception
     * @author J.Ruano
     */
    public boolean clickOnProgramEnrollments() throws Exception {
        boolean statusOperation = false;
        try {
            clickAndMoveToElementVisible(tab_productEnrollment, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickOnProgramEnrollments")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickOnProgramEnrollments");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    public boolean filterConsentWarningMessages(String consentTypeForm) throws Exception {
        boolean statusOperation = false;
        try {
            clickAndMoveToElementVisible(tab_productEnrollment, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("filterConsentWarningMessages")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "filterConsentWarningMessages");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), consentTypeForm);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    /**
     * Method to click the Payer Tab Person Account
     *
     * @return
     * @throws Exception
     * @author J.Ruano
     */
    public boolean clickPayerTab() throws Exception {
        boolean statusOperation = false;
        try {
            clickAndMoveToElementClickable(tabButton_payer, mediumWait());
            clickProductEnrollment();
            clickAndMoveToElementClickable(tabButton_payer, mediumWait());
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("clickPayerTab")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "clickPayerTab");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance());
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }


    /**
     * Method to click the Product Enrollment Tab
     *
     * @return
     * @throws Exception
     * @author J.Ruano
     */
    public void clickProductEnrollment() throws Exception {
        clickAndMoveToElementClickable(tab_productEnrollment, mediumWait());
    }


    /**
     * Method to click the New button to create a Insurance
     *
     * @return
     * @throws Exception
     * @author J.Ruano
     */
    public void clickNewPatientInsurances() throws Exception {
        clickAndMoveToElementClickable(button_newPatientInsurances, mediumWait());
    }

}