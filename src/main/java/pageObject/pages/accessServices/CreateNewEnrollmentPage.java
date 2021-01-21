package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void enterProduct() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newProgramEnrollment, 20);
        sendKeysAndMoveToElementVisible(input_product, "fasenra",20);
        clickElementVisible(input_programEndDate, 10);
    }

    public void clickEnrollButton() throws Exception {
        scrollToWebElementJS(button_enroll);
        clickElementVisible(button_enroll, 10);
        switchToParentFrame();
    }
}
