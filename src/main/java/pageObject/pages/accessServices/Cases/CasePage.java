package pageObject.pages.accessServices.Cases;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CasePage extends CommonFunctions {

    @FindBy(xpath = "//article//a[@title='Open']")
    private WebElement label_caseStatus;

    @FindBy(xpath = "//*[contains(text(),'Case Contact')]/following::*[./text()='Case Requested By']/../..//*[contains(@class,'itemBody')]")
    private WebElement input_caseRequestedBy;

    @FindBy(xpath = "//*[contains(text(),'Case Contact')]/following::*[./text()='Enrolled Patient']/../..//*[contains(@class,'itemBody')]")
    private WebElement input_enrolledPatient;

    @FindBy(xpath = "//span[contains(text(),'Product Enrollment')][contains(@class,'test-id')]/../..//div[contains(@class,'itemBody')]//a")
    private WebElement input_productEnrollment;

    @FindBy(xpath = "//*[contains(text(),'Case Contact')]/following::*[./text()='Date of Birth']/../..//*[contains(@class,'itemBody')]")
    private WebElement input_dateOfBirth;

    @FindBy(xpath = "//*[contains(text(),'Case')]/following::*[./text()='Channel']/../..//*[contains(@class,'itemBody')]")
    private WebElement input_channel;

    @FindBy(xpath = "//*[contains(text(),'Case')]/following::*[./text()='Case Status']/../..//*[contains(@class,'itemBody')]")
    private WebElement input_caseStatus;

    @FindBy(xpath = "//*[contains(text(),'Case')]/following::*[./text()='Product']/../..//*[contains(@class,'itemBody')]")
    private WebElement input_product;

    public boolean isCasePageDisplayed(){
        return waitForElementVisibility(label_caseStatus, 20);
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
        return waitForElementVisibility(input_productEnrollment, 2) ? getWebElementAttribute(input_productEnrollment, "title") : "";
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
}
