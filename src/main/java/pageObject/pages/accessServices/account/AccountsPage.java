package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountsPage extends CommonFunctions {
    @FindBy(xpath = "//input[@name='Account-search-input']")
    private WebElement input_searchAccount;

    @FindBy(xpath = "//div[@class='slds-spinner_container slds-grid']")
    private WebElement load_LoadSpinner;

    @FindBy(xpath = "//table[@data-aura-class='uiVirtualDataTable']//a[@data-refid='recordId']")
    private List<WebElement> list_patients;
    private By list_patient = By.xpath("//table[@data-aura-class='uiVirtualDataTable']//a[@data-refid='recordId']");

    public String isAccountCreated(String account) throws Exception {
        String searchAccount="";
        input_searchAccount.clear();
        waitForElementVisibility(input_searchAccount, 30);
        sendKeysElementVisibleJS(input_searchAccount, account, 5);
        input_searchAccount.sendKeys(Keys.ENTER);
        waitForElementListVisible(list_patients, 10);
        waitUntilAccountIsClickable(account);
        for(WebElement el : list_patients){
            if(el.getText().equalsIgnoreCase(account)){
                searchAccount = account;
                break;
            }
        }
        return searchAccount;
    }

    public void clickAccountCreated(String accountName) throws Exception {
        By tableLocator = By.xpath("//span//a[@title='" + accountName +"']");
        clickAndMoveToElementClickable(getWebElement(tableLocator),10);
    }

    public boolean waitUntilAccountIsClickable(String account) throws Exception {
        By clicklableAccountLink = By.xpath("//span//a[@title='"+ account + "']");
        return waitForElementToBeClickableBy(clicklableAccountLink, 10);
    }
}
