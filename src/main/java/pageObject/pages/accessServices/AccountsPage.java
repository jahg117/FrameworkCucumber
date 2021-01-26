package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountsPage extends CommonFunctions {
    @FindBy(xpath = "//input[@name='Account-search-input']")
    private WebElement input_searchAccount;

    @FindBy(xpath = "//table[@data-aura-class='uiVirtualDataTable']//a[@data-refid='recordId']")
    private List<WebElement> list_patients;
    private By list_patient = By.xpath("//table[@data-aura-class='uiVirtualDataTable']//a[@data-refid='recordId']");

    public String isAccountCreated(String account) throws Exception {
        String searchAccount="";
        waitForElementVisibility(input_searchAccount, 30);
        sendKeysElementVisible(input_searchAccount, account, 5);
        sendKeysElementVisible(input_searchAccount, Keys.ENTER.toString(), 5);
        waitForNumberOfElementsToBe(list_patient, 1, 10);
        waitForElementListVisible(list_patients, 10);
        for(WebElement el : list_patients){
            if(el.getText().equalsIgnoreCase(account)){
                searchAccount = account;
                break;
            }
        }
        return searchAccount;
    }
}
