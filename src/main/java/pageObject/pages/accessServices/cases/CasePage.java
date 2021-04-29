package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CasePage extends CommonFunctions {

    @FindBy(xpath = "(//img[@title='Case']/following::*[@name='Edit'])[last()]")
    private WebElement button_edit;

    @FindBy(xpath = "//img[@title='Case']/following::*//button[contains(@name,'Child')]")
    private WebElement button_childCase;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Case Requested By']/../..//span[contains(@class,'field-value')]")
    private WebElement input_caseRequestedBy;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Enrolled Patient']/../..//span[contains(@class,'field-value')]")
    private WebElement input_enrolledPatient;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Product Enrollment']/../..//span[contains(@class,'field-value')]")
    private WebElement input_productEnrollment;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Date of Birth']/../..//span[contains(@class,'field-value')]")
    private WebElement input_dateOfBirth;

    @FindBy(xpath = "(//img[@title='Case']/following::*[./text()='Channel']/../..//span[contains(@class,'field-value')])[last()]")
    private WebElement input_channel;

    @FindBy(xpath = "(//img[@title='Case']/following::*[./text()='Case Status']/../..//span[contains(@class,'field-value')])[last()]")
    private WebElement input_caseStatus;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Status']/../..//span[contains(@class,'field-value')]")
    private WebElement input_status;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Product']/../..//span[contains(@class,'field-value')]")
    private WebElement input_product;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Name']/../..//span[contains(@class,'field-value')]")
    private WebElement input_patientName;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Case Number']/../..//span[contains(@class,'field-value')]")
    private WebElement input_caseNumber;

    public boolean isCasePageDisplayed() throws Exception {
        return waitForElementVisibility(button_edit, mediumWait());
    }

    public void clickChildCaseButton() throws Exception {
        clickAndMoveToElementClickable(button_childCase, mediumWait());
    }

    public String getCaseNumber() throws Exception {
        return waitForElementVisibility(input_caseNumber, shortWait()) ? getWebElementText(input_caseNumber).split("\n")[0] : "";
    }

    public String getCaseStatus() throws Exception {
        if (waitForElementVisibility(input_caseStatus, shortWait())) {
            return getWebElementText(input_caseStatus).split("\n")[0];
        } else {
            if (waitForElementVisibility(input_status, shortWait())) {
                return getWebElementText(input_status).split("\n")[0];
            } else {
                return "";
            }
        }
    }

    public String getStatus() throws Exception {
        return waitForElementVisibility(input_status, shortWait()) ? getWebElementText(input_status).split("\n")[0] : "";
    }

    public String getCaseRequestedBy() throws Exception {
        return waitForElementVisibility(input_caseRequestedBy, shortWait()) ? getWebElementText(input_caseRequestedBy) : "";
    }

    public String getEnrolledPatient() throws Exception {
        return waitForElementVisibility(input_enrolledPatient, mediumWait()) ? getWebElementText(input_enrolledPatient).split("\n")[0] : "";
    }

    public String getProductEnrollment() throws Exception {
        return waitForElementVisibility(input_productEnrollment, shortWait()) ? getWebElementText(input_productEnrollment) : "";
    }

    public String getDateOfBirth() throws Exception {
        return waitForElementVisibility(input_dateOfBirth, shortWait()) ? getWebElementText(input_dateOfBirth) : "";
    }

    public String getChannel() throws Exception {
        return waitForElementVisibility(input_channel, shortWait()) ? getWebElementText(input_channel).split("\n")[0] : "";
    }

    public String getProduct() throws Exception {
        return waitForElementVisibility(input_product, shortWait()) ? getWebElementText(input_product) : "";
    }

    public String getPatientName() throws Exception {
        return waitForElementVisibility(input_patientName, mediumWait()) ? getWebElementText(input_patientName).split("\n")[0] : "";
    }
}
