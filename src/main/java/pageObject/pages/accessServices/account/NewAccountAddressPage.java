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

    private By firstOpcFromAddressSearchBox = By.xpath("//*[@class='slds-listbox__item']");

    @FindBy (xpath = "(//*[@name='progress'])[last()]")
    private WebElement addressTypeDropdown;

    @FindBy (xpath = "//*[@data-value='Home']")
    private WebElement addressTypeHome;

    @FindBy (xpath = "//*[@title='Save']")
    private WebElement saveButton;


    public void searchAddressWithStateMN() throws Exception {
        sendKeysElementClickable(googleAddressSearchBox,"TCF Bank Stadium", mediumWait());
        waitForNumberOfElementsToBeMoreThanBy(firstOpcFromAddressSearchBox, 1, mediumWait());
        clickAndMoveToElementClickable(getWebElementList(firstOpcFromAddressSearchBox).get(0), mediumWait());
        clickAndMoveToElementVisible(addressTypeDropdown, mediumWait());
        clickAndMoveToElementVisible(addressTypeHome, mediumWait());
        clickAndMoveToElementClickable(saveButton, mediumWait());
    }

    public void searchAddressWithStateMD() throws Exception {
        sendKeysElementClickable(googleAddressSearchBox,"Berwyn Heights Elementary School", mediumWait());
        waitForNumberOfElementsToBeMoreThanBy(firstOpcFromAddressSearchBox, 1, mediumWait());
        clickAndMoveToElementClickable(getWebElementList(firstOpcFromAddressSearchBox).get(0), mediumWait());
        clickAndMoveToElementVisible(addressTypeDropdown, mediumWait());
        clickAndMoveToElementVisible(addressTypeHome, mediumWait());
        clickAndMoveToElementClickable(saveButton, mediumWait());
    }

}
