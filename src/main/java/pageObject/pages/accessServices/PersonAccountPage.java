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

    @FindBy(xpath = "//th[@data-label='Product Enrollment Number']")
    private List<WebElement> list_productEnrollmentNumber;

    @FindBy(xpath = "//td[@data-label='Product']")
    private List<WebElement> list_products;

    public void clickNewProductEnrollment() throws Exception {
        waitForElementVisibility(label_accountPersonName, 30);
        clickElementVisible(button_newProductEnrollment, 15);
    }

    public void clickProductEnrollmentAdded(String product) throws Exception {
        waitForElementListVisible(list_products, 20);
        for(int i=0; i<list_products.size(); i++) {
            if(list_products.get(i).getText().equalsIgnoreCase(product)) {
                clickElementVisible(list_productEnrollmentNumber.get(i), 5);
            }
        }
    }
}
