package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountsPage extends CommonFunctions {
    @FindBy(xpath = "//input[@name='Account-search-input']")
    private WebElement inputSearchAccount;

    @FindBy(xpath = "//div[@class='slds-spinner_container slds-grid']")
    private WebElement loadLoadSpinner;

    @FindBy(xpath = "//table[@data-aura-class='uiVirtualDataTable']//a[@data-refid='recordId']")
    private List<WebElement> listPatients;

    public String isAccountCreated(String account) throws Exception {
        String searchAccount="";
        inputSearchAccount.clear();
        waitForElementVisibility(inputSearchAccount, longWait());
        sendKeysElementVisibleJS(inputSearchAccount, account, shortWait());
        inputSearchAccount.sendKeys(Keys.ENTER);
        waitForElementListVisible(listPatients, mediumWait());
        waitUntilAccountIsClickable(account);
        for(WebElement el : listPatients){
            if(el.getText().equalsIgnoreCase(account)){
                searchAccount = account;
                break;
            }
        }
        return searchAccount;
    }

    public void clickAccountCreated(String accountName) throws Exception {
        By tableLocator = By.xpath("//span//a[@title='" + accountName +"']");
        clickAndMoveToElementClickable(getWebElement(tableLocator),mediumWait());
    }

    public boolean waitUntilAccountIsClickable(String account) throws Exception {
        By clicklableAccountLink = By.xpath("//span//a[@title='"+ account + "']");
        return waitForElementToBeClickableBy(clicklableAccountLink, mediumWait());
    }
}
