package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

public class CreateNewEnrollmentPage extends CommonFunctions {

    @FindBy(xpath = "//iframe[@title='New Program Enrollment']")
    private WebElement iframe_newProgramEnrollment;

    @FindBy(xpath = "//span[@class='lookupInput']")
    private WebElement input_product;

    @FindBy(xpath = "//img[@class='lookupIcon']")
    private WebElement icon_lookUp;

    @FindBy(xpath = "//span[@class='dateInput dateOnlyInput']")
    private WebElement input_programEndDate;

    @FindBy(xpath = "//td[@class='pbButtonb ']//input[@value='Enroll']")
    private WebElement button_enroll;

    public boolean isProductEnrollmentPageDisplayed() {
        return waitForElementVisibility(iframe_newProgramEnrollment, 30);
    }

    public String fillProductEnrollmentForm(String productType) throws Exception {
        String product = "";
        if(productType.equalsIgnoreCase("AZ")||productType.equalsIgnoreCase("DSI")){
            JsonFiles file = new JsonFiles();
            file.setFileName("1372_EnrollmentProducts");
            product = file.getRandomFieldArray(productType);
        } else{
            product = productType;
        }
        switchToFrameByWebElementIndexOrName(iframe_newProgramEnrollment, 20);
        sendKeysAndMoveToElementVisible(input_product, product,20);
        clickElementVisible(input_programEndDate, 10);
        switchToParentFrame();
        return product;
    }

    public void clickEnrollButton() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newProgramEnrollment, 20);
        scrollToWebElementJS(button_enroll);
        waitForElementVisibility(button_enroll, 10);
        doubleClickAndMoveToElementClickable(button_enroll, 10);
        switchToParentFrame();
    }
}
