package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class NewConsentAttestationPage extends CommonFunctions {
    @FindBy(xpath = "//*[contains(@class,'dateInput')]//input")
    private WebElement input_consentDate;

    @FindBy(xpath = "//*[contains(@id,'consentSource')]")
    private WebElement dropdown_consentSource;

    @FindBy(xpath = "//*[contains(@id,'conProvBy:spp')]")
    private WebElement checkbox_optionSPP;

    @FindBy(xpath = "//input[@value='Next']")
    private WebElement button_next;

    @FindBy(xpath = "//iframe[@title='accessibility title']")
    private List<WebElement> iframe_consentForm;

    @FindBy(xpath = "//td[@class='dataCol first']")
    private WebElement label_patientName;

    @FindBy(xpath = "//*[contains(text(),'Primary Company')]/following::*[@class='dataCol'][1]")
    private WebElement label_primaryCompany;

    @FindBy(xpath = "//*[contains(text(),'Product Enrollment')]/following::*[@class='dataCol'][1]")
    private WebElement label_productEnrollment;

    @FindBy(xpath = "//select[contains(@name,'consentDate')]")
    private WebElement dropdown_consentStatus;

    public boolean isConsentFormDisplayed() {
        switchToParentFrame();
        if(waitForElementVisibility(iframe_consentForm.get(1), 20)){
            switchToFrameByWebElementIndexOrName(iframe_consentForm.get(1), 10);
            return true;
        }else{
            return false;
        }
    }

    public String getProductEnrollment() {
        return getWebElementText(label_productEnrollment);
    }

    public String getPatientName() {
        return getWebElementText(label_patientName);
    }

    public boolean isPrimaryCompanyDisplayed() {
        return waitForElementVisibility(label_primaryCompany, 10);
    }

    public boolean isConsentDateDisplayed() {
        return waitForElementVisibility(input_consentDate, 10);
    }

    public boolean isConsentStatusDisplayed() {
        return waitForElementVisibility(dropdown_consentStatus, 10);
    }

    public boolean isConsentSourceDisplayed() {
        return waitForElementVisibility(dropdown_consentSource, 10);
    }

    public void fillConsentMandatoryFields() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_consentForm, 20);
        sendKeysElementVisible(input_consentDate, getRandomDate(),10);
        selectDropDownRandomOptionNone(dropdown_consentSource, 10);
        clickAndMoveToElementClickable(checkbox_optionSPP, 10);
        clickAndMoveToElementClickable(button_next, 10);
        switchToParentFrame();
    }
}
