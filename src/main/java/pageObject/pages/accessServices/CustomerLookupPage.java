package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CustomerLookupPage extends CommonFunctions {

    @FindBy(xpath = "//iframe[@title='accessibility title']")
    private List<WebElement> iframe_pageInformation;

    @FindBy(xpath = "//button[contains(@onClick,'openAccountCreationTab')]")
    private WebElement button_newAccount;

    @FindBy(xpath = "//*[contains(@name,'fname')]")
    private WebElement input_firstName;

    @FindBy(xpath = "//*[contains(@name,'mname')]")
    private WebElement input_middleName;

    @FindBy(xpath = "//*[contains(@name,'lname')]")
    private WebElement input_lastName;

    @FindBy(xpath = "//legend/following::input[@type='checkbox']")
    private List<WebElement> checkbox_FilterList;

    @FindBy(xpath = "//legend/following::input[@type='checkbox'][contains(@name,'hca')]")
    private WebElement checkbox_hca;

    @FindBy(xpath = "//legend/following::input[@type='checkbox'][contains(@name,'hcp')]")
    private WebElement checkbox_hcp;

    @FindBy(xpath = "//legend/following::input[@type='checkbox'][contains(@name,'cpc')]")
    private WebElement checkbox_cpc;

    @FindBy(xpath = "//legend/following::input[@type='checkbox'][contains(@name,'emp')]")
    private WebElement checkbox_emp;

    @FindBy(xpath = "//legend/following::input[contains(@name,'ext-id')]")
    private WebElement input_ExternalID;

    @FindBy(xpath = "//*[@checked='checked']")
    private List<WebElement> checkbox_CheckedList;

    @FindBy(xpath = "//a[normalize-space(text())='18pqbDGY']")
    private WebElement link_AZID;


    public void clickNewAccount() throws Exception {
        waitForPageToLoad();
        waitForElementListVisible(iframe_pageInformation, 10);
        switchToFrameByWebElementIndexOrName(iframe_pageInformation.get(0), 30);
        clickAndMoveToElementClickable(button_newAccount, 10);
        switchToParentFrame();
    }

    public void searchCPCByName() throws Exception {
        String checkbox_ConsumerPatientCaregiver = "cpc";
        //TBD from where the name will be pulled out
        String firstName = "Juan";
        String lastName = "Bug";
        if (!checkbox_CheckedList.isEmpty()) {
            uncheckCheckbox(checkbox_CheckedList);
        }
        filterByCheckbox(checkbox_ConsumerPatientCaregiver);
        sendKeysElementClickable(input_firstName, firstName, 10);
        sendKeysElementClickable(input_lastName, lastName, 10);
    }

    public void searchCPCByID() throws Exception {
        String checkbox_ConsumerPatientCaregiver = "cpc";
        //TBD from where the ID will be pulled out
        String cpcID = "1OE8yiph";
        if (!checkbox_CheckedList.isEmpty()) {
            uncheckCheckbox(checkbox_CheckedList);
        }
        filterByCheckbox(checkbox_ConsumerPatientCaregiver);
        sendKeysElementClickable(input_ExternalID, cpcID, 10);
    }

    public void uncheckCheckbox(List<WebElement> checkbox_CheckedList) throws Exception {
        for (WebElement checkBoxElement : checkbox_CheckedList) {
            clickMethod(checkBoxElement);
        }
    }

    public void filterByCheckbox(String checkbox) throws Exception {
        WebElement checkbox_filterBy = null;
        switch (checkbox.trim()) {
            case "hca":
                clickMethod(checkbox_hca);
                break;

            case "hcp":
                clickMethod(checkbox_hcp);
                break;

            case "cpc":
                clickMethod(checkbox_cpc);
                break;

            case "emp":
                clickMethod(checkbox_emp);
                break;
        }
    }
    /**
     * Method to click the External ID (AZ ID) found by the search function
     *
     * @throws Exception
     * @author J.Ruano
     */
    public void selectAndClickAZID() throws Exception {
        clickAndMoveToElementClickable(link_AZID, 10);
    }
}
