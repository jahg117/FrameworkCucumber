package pageObject.pages.accessServices.consents;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewConsentAttestationPage extends CommonFunctions {
    @FindBy(xpath = "//*[contains(@class,'dateInput')]//input")
    private WebElement inputConsentDate;

    @FindBy(xpath = "//*[contains(@id,'consentSource')]")
    private WebElement dropdownConsentSource;

    @FindBy(xpath = "//*[contains(@id,'conProvBy:spp')]")
    private WebElement checkboxOptionSPP;

    @FindBy(xpath = "//input[@value='Next']")
    private WebElement buttonNext;

    @FindBy(xpath = "(//iframe[@title='accessibility title'])[last()]")
    private WebElement iframeConsentForm;

    @FindBy(xpath = "//td[@class='dataCol first']")
    private WebElement labelPatientName;

    @FindBy(xpath = "//*[contains(text(),'Primary Company')]/following::*[@class='dataCol'][1]")
    private WebElement labelPrimaryCompany;

    @FindBy(xpath = "//*[contains(text(),'Product Enrollment')]/following::*[@class='dataCol'][1]")
    private WebElement labelProductEnrollment;

    @FindBy(xpath = "//select[contains(@name,'consentDate')]")
    private WebElement dropdownConsentStatus;

    public boolean isConsentFormDisplayed() throws Exception {
        switchToParentFrame();
        if (waitForElementVisibility(iframeConsentForm, mediumWait())) {
            switchToFrameByWebElementIndexOrName(iframeConsentForm, mediumWait());
            return true;
        } else {
            return false;
        }
    }

    public String getProductEnrollment() throws Exception {
        return getWebElementText(labelProductEnrollment);
    }

    public String getPatientName() throws Exception {
        return getWebElementText(labelPatientName);
    }

    public boolean isPrimaryCompanyDisplayed() throws Exception {
        return waitForElementVisibility(labelPrimaryCompany, mediumWait());
    }

    public boolean isConsentDateDisplayed() throws Exception {
        return waitForElementVisibility(inputConsentDate, mediumWait());
    }

    public boolean isConsentStatusDisplayed() throws Exception {
        return waitForElementVisibility(dropdownConsentStatus, mediumWait());
    }

    public boolean isConsentSourceDisplayed() throws Exception {
        return waitForElementVisibility(dropdownConsentSource, mediumWait());
    }

    public void fillConsentMandatoryFields() throws Exception {
        switchToParentFrame();
        switchToFrameByWebElementIndexOrName(iframeConsentForm, mediumWait());
        sendKeysElementVisible(inputConsentDate, getRandomDate(), mediumWait());
        selectDropDownRandomOptionNone(dropdownConsentSource, mediumWait());
        clickAndMoveToElementClickable(checkboxOptionSPP, mediumWait());
        clickAndMoveToElementClickable(buttonNext, mediumWait());
        switchToParentFrame();
    }

    public void fillDSIFLSPMandatoryFields() throws Exception {
        switchToParentFrame();
        switchToFrameByWebElementIndexOrName(iframeConsentForm, mediumWait());
        sendKeysElementVisible(inputConsentDate, getRandomDate(), mediumWait());
        selectDropDownRandomOptionNone(dropdownConsentSource, mediumWait());
        clickAndMoveToElementClickable(buttonNext, mediumWait());
        switchToParentFrame();
    }
}
