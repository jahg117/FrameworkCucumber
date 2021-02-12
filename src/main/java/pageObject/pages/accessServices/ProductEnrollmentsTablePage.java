package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ProductEnrollmentsTablePage extends CommonFunctions {
    @FindBy(xpath = "//a[@data-refid='recordId'][contains(@title,'PE-')]")
    private List<WebElement> list_productEnrollments;

    @FindBy(xpath = "//h1[@title='Product Enrollments']")
    private WebElement label_productEnrollments;

    public boolean isProductEnrollmentsPageDisplayed(){
        return waitForElementVisibility(label_productEnrollments, 30);
    }

    public ArrayList<String> getProductEnrollmentsList(){
        waitForElementListVisible(list_productEnrollments, 20);
        ArrayList<String> productEnrollments = new ArrayList<>();
        for(WebElement el : list_productEnrollments){
            productEnrollments.add(getWebElementText(el));
        }
        return productEnrollments;
    }
}
