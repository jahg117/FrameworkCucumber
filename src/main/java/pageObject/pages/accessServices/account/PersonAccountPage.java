package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void clickViewAllProgramEnrollments() throws Exception {
        waitForPresenceOfAllElementsLocatedBy(link_viewAllProgramEnrollment, 30);
        clickElementJS(getWebElement(link_viewAllProgramEnrollment));
    }

    public void clickNewProductEnrollment() throws Exception {
        switchToDefaultContentFrame();
        waitForElementClickable(button_newProductEnrollment, mediumWait());
        scrollTop();
        clickElementClickable(button_newProductEnrollment, mediumWait());
    }

    public void clickNewCase() throws Exception {
        waitForElementVisibility(label_cases, 30);
        waitForElementVisibility(label_productEnrollmentsTabOption, 30);
        scrollToWebElementJS(label_productEnrollmentsTabOption);
        clickAndMoveToElementClickable(button_newCase, 10);
    }

    public void clickNewCasePersonalAccountPage() throws Exception {
        waitForElementVisibility(button_newCase, 30);
        waitForElementVisibility(button_newProductEnrollment, 10);
        scrollToWebElementJS(button_newProductEnrollment);
        clickAndMoveToElementClickable(button_newCase, 10);
    }

    public String getPEPId() {
        waitForElementVisibility(button_newCase, 30);
        waitForElementVisibility(label_pepId, 10);
        return getWebElementText(label_pepId);
    }

    public boolean isRedIconDisplayed(String product) throws Exception {
        boolean result = false;
        String newProduct = null;
        waitForElementListVisible(list_products, 20);
        for (int i = 0; i < list_products.size(); i++) {
            newProduct = list_products.get(i).getText();
            if (newProduct.equalsIgnoreCase(product)) {
                result = waitForElementVisibility(list_redPAFIcon.get(i), 10);
                break;
            }
        }
        return result;
    }

    public String getProductEnrollmentNumber(String product) throws Exception {
        switchToTab(0);
        String newProduct = "";
        String programEnrollment = "";
        waitForElementListVisible(list_products, 20);
        for (int i = 0; i < list_products.size(); i++) {
            newProduct = list_products.get(i).getText();
            if (newProduct.equalsIgnoreCase(product)) {
                programEnrollment = list_productEnrollmentNumber.get(i).getText();
                break;
            }
        }
        return programEnrollment;
    }

    public String clickProductEnrollmentAdded(String product) throws Exception {
        boolean flag = false;
        String newProduct = null;
        waitForElementListVisible(list_products, 20);
        for (int i = 0; i < list_products.size(); i++) {
            newProduct = list_products.get(i).getText();
            if (newProduct.equalsIgnoreCase(product)) {
                clickElementVisible(list_productEnrollmentNumber.get(i), 5);
                flag = true;
                break;
            }
        }
        if (flag = false) {
            return "";
        } else {
            return newProduct;
        }
    }

    /**
     * Method to click the button New Consent located at Person Page
     *
     * @throws Exception
     * @author J.Ruano
     */
    public void clickOnNewConsent() throws Exception {
        switchToDefaultContentFrame();
        clickAndMoveToElementVisible(tab_Consent, mediumWait());
        clickAndMoveToElementVisible(button_newConsent, mediumWait());
    }

    /**
     * Method to change from one tab to another according to an index
     *
     * @param idxTab it contains an integer with the number of the tab related to a list that contains all the corrently open tabs
     * @throws Exception
     * @author J.Ruano
     */
    public void switchToTab(int idxTab) throws Exception {
        switchSubTabByIndexSF(0, mediumWait());
    }

    /**
     * Method to click the program enrollment from Program Enrollments page
     *
     * @throws Exception
     * @author J.Ruano
     */
    public void clickOnProgramEnrollments() throws Exception {
        clickAndMoveToElementVisible(tab_productEnrollment, 10);
    }

    public void filterConsentWarningMessages(String consentTypeForm) throws Exception {
        clickAndMoveToElementVisible(tab_productEnrollment, mediumWait());
    }

    /**
     * Method to click the Payer Tab Person Account
     *
     * @throws Exception
     * @author J.Ruano
     */
    public void clickPayerTab() throws Exception {
        try {
            clickAndMoveToElementClickable(tabButton_payer, mediumWait());
            clickProductEnrollment();
            clickAndMoveToElementClickable(tabButton_payer, mediumWait());
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            waitUntilVisibleLoop(tabButton_payer, 2, mediumWait());
            clickAndMoveToElementClickable(tabButton_payer, mediumWait());
        }
    }

    /**
     * Method to click the Product Enrollment Tab
     *
     * @throws Exception
     * @author J.Ruano
     */
    public void clickProductEnrollment() throws Exception {
        clickAndMoveToElementClickable(tab_productEnrollment, mediumWait());
    }

    /**
     * Method to click the New button to create a Insurance
     *
     * @throws Exception
     * @author J.Ruano
     */
    public void clickNewPatientInsurances() throws Exception {
        clickAndMoveToElementClickable(button_newPatientInsurances, mediumWait());
    }
}