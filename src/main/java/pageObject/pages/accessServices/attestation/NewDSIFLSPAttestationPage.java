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

    public void isNewDSIFLSAttestationPageDisplayed(){
        waitForElementVisibility(iframe_newAttestationPage, 20);
    }

    public void selectHCP() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newAttestationPage, 10);
        waitForElementListVisible(list_checkboxHCP, 20);
        clickElementClickable(list_checkboxHCP.get(0), 10);
        switchToParentFrame();
    }

    public void clickSaveButton() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newAttestationPage, 10);
        clickElementClickable(button_save, 20);
        switchToParentFrame();
    }

    public void selectHCPAddress() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newAttestationPage, 20);
        waitForElementListVisible(list_checkboxHCPAddress, 10);
        clickElementClickable(list_checkboxHCPAddress.get(0), 10);
        switchToParentFrame();
    }
}
