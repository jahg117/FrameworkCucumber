package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
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

    public void clickNewProductEnrollment() throws Exception {
        waitForElementVisibility(label_accountPersonName, 30);
        clickElementClickable(button_newProductEnrollment, 15);
    }

    public boolean isRedIconDisplayed(String product) throws Exception {
        boolean result=false;
        String newProduct = null;
        waitForElementListVisible(list_products, 20);
        for(int i=0; i<list_products.size(); i++) {
            newProduct=list_products.get(i).getText();
            if(newProduct.equalsIgnoreCase(product)) {
                result = waitForElementVisibility(list_redPAFIcon.get(i), 10);
                break;
            }
        }
        return result;
    }

    public String getProductEnrollmentNumber(String product) {
        String newProduct = "";
        String programEnrollment = "";
        waitForElementListVisible(list_products, 20);
        for(int i=0; i<list_products.size(); i++) {
            newProduct=list_products.get(i).getText();
            if(newProduct.equalsIgnoreCase(product)) {
                programEnrollment = list_productEnrollmentNumber.get(i).getText();
                 break;
            }
        }
        return programEnrollment;
    }

    public String clickProductEnrollmentAdded(String product) throws Exception {
        boolean flag=false;
        String newProduct = null;
        waitForElementListVisible(list_products, 20);
        for(int i=0; i<list_products.size(); i++) {
            newProduct=list_products.get(i).getText();
            if(newProduct.equalsIgnoreCase(product)) {
                clickElementVisible(list_productEnrollmentNumber.get(i), 5);
                flag=true;
                break;
            }
        }
        if(flag=false){
            return "";
        }else{
            return newProduct;
        }
    }
}
