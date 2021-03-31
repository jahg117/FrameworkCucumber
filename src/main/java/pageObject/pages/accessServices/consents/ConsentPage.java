package pageObject.pages.accessServices.consents;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ConsentPage extends CommonFunctions {
    @FindBy(xpath = "//button[@name='Global.NewContact']")
    private WebElement button_newContact;

    @FindBy(xpath = "//*[contains(text(),'Consent No.')]/following::*[@data-output-element-id='output-field']")
    private List<WebElement> list_consentDetails;

    @FindBy(xpath = "//span[contains(text(),'Consent No.') and contains(@class,'test-id')]/../..//*[@data-output-element-id]")
    private WebElement label_consentID;

    public String getConsentID(){
        waitForElementVisibility(label_consentID, 20);
        return getWebElementText(label_consentID);
    }

    public boolean isConsentPageDisplayed(){
        return waitForElementClickable(button_newContact, 30);
    }

    public boolean isConsentDetailDisplayed(String consentDetail){
        boolean isDisplayed = false;
        for(WebElement el : list_consentDetails){
            String consent = getWebElementText(el);
            if(consent.contains(consentDetail)){
                isDisplayed = true;
                break;
            }
        }
        return isDisplayed;
    }
}
