package pageObject.pages.accessServices.consents;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class NewConsentPage extends CommonFunctions {
    @FindBy(xpath = "(//iframe[@title='accessibility title'])[last()]")
    private WebElement iframe_pageInformation;

    @FindBy(xpath = "//span[normalize-space(text())='New Consent']")
    private WebElement label_newConsent;

    @FindBy(xpath = "//*[@class='requiredInput']/following::select[@id]")
    private WebElement dropdown_consentType;

    @FindBy(xpath = "//*[@class='actionBtns']/input[@value='Next']")
    private WebElement button_next;

    /**
     * Method used to select the type of consent
     *
     * @param consentTypeOption it contains the type of consent to be select
     * @throws Exception
     * @author J.Ruano
     */
    public void selectConsentType(String consentTypeOption) throws Exception {
        waitForElementVisibility(iframe_pageInformation, longWait());
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, longWait());
        waitForElementVisibility(label_newConsent, longWait());
        if (consentTypeOption.trim().equalsIgnoreCase("RND")) {
            selectDropDownRandomOptionNone(dropdown_consentType,longWait());
        }else{
            selectAndMoveDropdownByText(dropdown_consentType, consentTypeOption, longWait());
        }
        clickAndMoveToElementVisible(button_next, mediumWait());
        switchToParentFrame();
    }
}
