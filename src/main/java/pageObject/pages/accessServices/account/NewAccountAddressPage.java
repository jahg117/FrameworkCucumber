package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;
import java.util.List;

public class NewAccountAddressPage extends CommonFunctions {
    @FindBy (xpath = "//*[@placeholder='Search Places']")
    private WebElement googleAddressSearchBox;

    @FindBy (xpath = "//*[@class='slds-listbox__item']")
    private WebElement firstOpcFromAddressSearchBox;

    public void searchAddressWithStateMN() throws Exception {
//        googleAddressSearchBox.sendKeys("TCF Bank Stadium");
        sendKeysElementClickable(googleAddressSearchBox,"TCF Bank Stadium", longWait());
        clickAndMoveToElementVisible(firstOpcFromAddressSearchBox, longWait());
    }

}
