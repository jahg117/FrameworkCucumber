package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductEnrollmentPage extends CommonFunctions {
    @FindBy(xpath = "//*[@title='New Care Team Member']")
    private WebElement button_newCareTeamMember;

    @FindBy(xpath = "//div[contains(@class,'truncate')]//slot[@name='primaryField']//lightning-formatted-text")
    private WebElement label_productEnrollmentNumber;

    public boolean isProductEnrollmentPageDisplayed() {
        return waitForElementVisibility(button_newCareTeamMember, 30);
    }

    public String getProductEnrollmentNumber() {
        if(waitForElementVisibility(label_productEnrollmentNumber, 10)){
            return label_productEnrollmentNumber.getText();
        }else{
            return "";
        }
    }
}
