package pageObject.pages.accessServices.attestation;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NewDSIFLSPAttestationPage extends CommonFunctions {
    @FindBy(xpath = "(//iframe[@title='accessibility title'])[last()]")
    private WebElement iframe_newAttestationPage;

    @FindBy(xpath = "//div[contains(@class,'apexDefaultPageBlock')]//table//td//input[@type='checkbox']")
    private List<WebElement> list_checkboxHCP;

    @FindBy(xpath = "//div[@class='pbBody']//table//input[@class='hcpAddresses']")
    private List<WebElement> list_checkboxHCPAddress;

    @FindBy(xpath = "//input[@value='Save']")
    private WebElement button_save;

    public void isNewDSIFLSAttestationPageDisplayed() throws Exception {
        waitForElementVisibility(iframe_newAttestationPage, mediumWait());
    }

    public void selectHCP() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newAttestationPage, mediumWait());
        waitForElementListVisible(list_checkboxHCP, mediumWait());
        clickElementClickable(list_checkboxHCP.get(0), mediumWait());
        switchToParentFrame();
    }

    public void clickSaveButton() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newAttestationPage, mediumWait());
        clickElementClickable(button_save, mediumWait());
        switchToParentFrame();
    }

    public void selectHCPAddress() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newAttestationPage, mediumWait());
        waitForElementListVisible(list_checkboxHCPAddress, mediumWait());
        clickElementClickable(list_checkboxHCPAddress.get(0), mediumWait());
        switchToParentFrame();
    }
}
