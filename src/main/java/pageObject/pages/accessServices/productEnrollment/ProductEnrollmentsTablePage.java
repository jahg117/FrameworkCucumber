package pageObject.pages.accessServices.productEnrollment;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProductEnrollmentsTablePage extends CommonFunctions {
    @FindBy(xpath = "//a[@data-refid='recordId'][contains(@title,'PE-')]")
    private List<WebElement> listProductEnrollments;

    @FindBy(xpath = "//h1[@title='Product Enrollments']")
    private WebElement labelProductEnrollments;

    public boolean isProductEnrollmentsPageDisplayed() throws Exception {
        return waitForElementVisibility(labelProductEnrollments, longWait());
    }

    public ArrayList<String> getProductEnrollmentsList() throws Exception {
        waitForElementListVisible(listProductEnrollments, mediumWait());
        ArrayList<String> productEnrollments = new ArrayList<>();
        for (WebElement el : listProductEnrollments) {
            productEnrollments.add(getWebElementText(el));
        }
        return productEnrollments;
    }
}
