package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PersonAccountPage extends CommonFunctions {
    @FindBy(xpath = "//slot[@name='primaryField']")
    private WebElement label_accountPersonName;

    @FindBy(xpath = "//*[contains(text(),'Product Enrollments')]/following::*[@title='New']")
    private WebElement button_newProductEnrollment;

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

    private By button_closeSubTabs = By.xpath("//ul[@class='tabBarItems slds-tabs--default__nav']//div[starts-with(@class,'close')]");

    public void clickNewProductEnrollment() throws Exception {
        waitForElementVisibility(label_accountPersonName, 30);
        clickElementClickable(button_newProductEnrollment, 15);
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
        clickAndMoveToElementVisible(tab_Consent, 10);
        clickAndMoveToElementVisible(button_newConsent, 10);
    }

    /**
     * Method to change from one tab to another according to an index
     *
     * @param idxTab it contains an integer with the number of the tab related to a list that contains all the corrently open tabs
     * @throws Exception
     * @author J.Ruano
     */
    public void switchToTab(int idxTab) throws Exception {
        switchSubTabByIndexSF(0, 10);
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
}