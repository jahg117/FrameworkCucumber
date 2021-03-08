package pageObject.pages.accessServices.Cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CasePage extends CommonFunctions {

    @FindBy(xpath = "(//img[@title='Case']/following::*[@name='Edit'])[last()]")
    private WebElement button_edit;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Case Requested By']/../..//span[contains(@class,'field-value')]")
    private WebElement input_caseRequestedBy;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Enrolled Patient']/../..//span[contains(@class,'field-value')]")
    private WebElement input_enrolledPatient;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Product Enrollment']/../..//span[contains(@class,'field-value')]")
    private WebElement input_productEnrollment;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Date of Birth']/../..//span[contains(@class,'field-value')]")
    private WebElement input_dateOfBirth;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Channel']/../..//span[contains(@class,'field-value')]")
    private WebElement input_channel;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Case Status']/../..//span[contains(@class,'field-value')]")
    private WebElement input_caseStatus;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Product']/../..//span[contains(@class,'field-value')]")
    private WebElement input_product;

    @FindBy(xpath = "//img[@title='Case']/following::*[./text()='Name']/../..//span[contains(@class,'field-value')]")
    private WebElement input_patientName;

    public boolean isCasePageDisplayed(){
        return waitForElementVisibility(button_edit, 20);
    }

    public String getCaseStatus(){
        return waitForElementVisibility(input_caseStatus, 2) ? getWebElementText(input_caseStatus).split("\n")[0] : "";
    }

    public String getCaseRequestedBy(){
        return waitForElementVisibility(input_caseRequestedBy, 2) ? getWebElementText(input_caseRequestedBy) : "";
    }

    public String getEnrolledPatient(){
        return waitForElementVisibility(input_enrolledPatient, 10) ? getWebElementText(input_enrolledPatient).split("\n")[0] : "";
    }

    public String getProductEnrollment(){
        return waitForElementVisibility(input_productEnrollment, 2) ? getWebElementText(input_productEnrollment) : "";
    }

    public String getDateOfBirth(){
        return waitForElementVisibility(input_dateOfBirth, 2) ? getWebElementText(input_dateOfBirth) : "";
    }

    public String getChannel(){
        return waitForElementVisibility(input_channel, 2) ? getWebElementText(input_channel).split("\n")[0] : "";
    }

    public String getProduct(){
        return waitForElementVisibility(input_product, 2) ? getWebElementText(input_product) : "";
    }

    public String getPatientName(){
        return waitForElementVisibility(input_patientName, 10) ? getWebElementText(input_patientName).split("\n")[0] : "";
    }
}
