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
        JsonFiles file = new JsonFiles();
        file.setFileName("1372_EnrollmentProducts");
        String product = file.getRandomFieldArray(productType);
        switchToFrameByWebElementIndexOrName(iframe_newProgramEnrollment, 20);
        sendKeysAndMoveToElementVisible(input_product, product,20);
        clickElementVisible(input_programEndDate, 10);
        return product;
    }

    public void clickEnrollButton() throws Exception {
        scrollToWebElementJS(button_enroll);
        clickElementVisible(button_enroll, 10);
        switchToParentFrame();
    }
}
