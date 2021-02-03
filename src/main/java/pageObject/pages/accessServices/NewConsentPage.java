package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewConsentPage extends CommonFunctions {
    @FindBy(xpath = "//iframe[@title='accessibility title']")
    private List<WebElement> iframe_pageInformation;

    @FindBy(xpath = "//span[normalize-space(text())='New Consent']")
    private WebElement label_newConsent;

    @FindBy(xpath = "//*[@class='requiredInput']/following::select[@id]")
    private WebElement dropdown_consentType;

    @FindBy(xpath = "//*[@class='actionBtns']/input[@value='Next']")
    private WebElement button_next;

    /**
     * Method used to select the type of consent
     *
     * @param consentType it contains the type of consent to be select
     * @throws Exception
     * @author J.Ruano
     */
    public void selectConsentType(String consentType) throws Exception {
        waitForElementListVisible(iframe_pageInformation, 10);
        switchToFrameByWebElementIndexOrName(iframe_pageInformation.get(1), 30);
        waitForElementVisibility(label_newConsent, 20);
        selectAndMoveDropdownByText(dropdown_consentType, consentType, 20);
        clickAndMoveToElementVisible(button_next, 15);
        switchToParentFrame();
    }
}
