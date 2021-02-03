package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.Keys;
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

    @FindBy(xpath = "//a[normalize-space(text())='EBcEenr']")//TBD
    private WebElement link_AZID;

    @FindBy(xpath = "//*[@id='searchButton']")
    private WebElement button_search;

    @FindBy(xpath = "//span[normalize-space(text())='Logged out']")
    private WebElement button_loggedOut;

    @FindBy(xpath = "//input[contains(@class,'input name')]")
    private WebElement input_facilityName;



    public void clickNewAccount() throws Exception {
        waitForPageToLoad();
        waitForElementListVisible(iframe_pageInformation, 10);
        switchToFrameByWebElementIndexOrName(iframe_pageInformation.get(0), 30);
        clickAndMoveToElementClickable(button_newAccount, 10);
        switchToParentFrame();
    }

    /**
     * Method used to search a CPC by fist name and last name at CustomerLookup search
     *
     * @param firstName it contains a string with the first name used to filled in the first name inpunt at CustomerLooup
     * @param lastName  it contains a string with the last name used to filled in the first name inpunt at CustomerLooup
     * @throws Exception
     * @author J.Ruano
     */
    public void searchCPCByName(String firstName, String lastName) throws Exception {
        String filterOption = "hcp";
        if (!checkbox_CheckedList.isEmpty()) {
            uncheckCheckbox(checkbox_CheckedList);
        }
        filterByCheckbox(filterOption);
        sendKeysElementClickable(input_firstName, firstName, 10);
        sendKeysElementClickable(input_lastName, lastName, 10);
    }

    /**
     * Method used to search a CPC by fist name and last name at CustomerLookup search
     *
     * @param cpcID it contains the ID of the CPC that will be used to search it at CustomerLookup
     * @throws Exception
     * @author J.Ruano
     */
    public void searchCPCByID(String cpcID) throws Exception {
        String filterOption = "cpc";
        waitForElementListVisible(iframe_pageInformation, 10);
        switchToFrameByWebElementIndexOrName(iframe_pageInformation.get(0), 30);
        if (!checkbox_CheckedList.isEmpty()) {
            uncheckCheckbox(checkbox_CheckedList);
        }
        filterByCheckbox(filterOption);
        //sendKeysElementClickable(input_ExternalID, cpcID, 10);
        //clickAndMoveToElementClickable(button_search, 15);
    }

    /**
     * Method used to uncheck all the checkboxes with status ckecked at CustomerLookup search
     *
     * @param checkbox_CheckedList it a list of all checkedboxes in CustomerLookup
     * @throws Exception
     * @author J.Ruano
     */
    public void uncheckCheckbox(List<WebElement> checkbox_CheckedList) throws Exception {
        int counterWE = 0;
        waitForElementVisibility(button_loggedOut, 10);
        waitForElementClickable(checkbox_CheckedList.get(0), 10);
        waitForElementVisibility(button_search, 15);
        do {
            clickElementClickable(checkbox_CheckedList.get(counterWE), 10);
            counterWE++;
        }
        while (counterWE <= (checkbox_CheckedList.size() - 1));
    }

    /**
     * Method used to check the type of filter used to search at CustomerLookup search
     *
     * @param filterOption it contains the filter option i.e. cpc that it is related to Consumer/Patient/Caregiver
     * @throws Exception
     * @author J.Ruano
     */
    public void filterByCheckbox(String filterOption) throws Exception {
        switch (filterOption.trim()) {
            case "hca":
                clickElementVisible(checkbox_hca, 10);
                break;

            case "hcp":
                clickElementVisible(checkbox_hcp, 10);
                break;

            case "cpc":
                clickElementVisible(checkbox_cpc, 10);
                break;

            case "emp":
                clickElementVisible(checkbox_emp, 10);
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

    public void doDummySearch() throws Exception {

    }
}
