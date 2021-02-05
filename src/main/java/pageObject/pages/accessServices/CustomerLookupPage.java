package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.util.HashMap;
import java.util.List;

public class CustomerLookupPage extends CommonFunctions {

    @FindBy(xpath = "(//iframe[@title='accessibility title'])[last()]")
    private WebElement iframe_pageInformation;

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

    @FindBy(xpath = "//table[@id='cust-table']//td[@data-label='Address']")
    private List<WebElement> row_address;
    private By row_addressElements = By.xpath("//table[@id='cust-table']//td[@data-label='First Name']");

    @FindBy(xpath = "//label[contains(text(),'Relationship')]/../..//select")
    private WebElement dropdown_relationship;

    @FindBy(xpath = "//input[@value='Create CareTeam Member']")
    private WebElement button_createCareTeamMember;

    public void searchRandomFirstName() throws Exception {
        JsonFiles jsonFiles = new JsonFiles();
        jsonFiles.setFileName("ExistingHCP");
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        sendKeysElementVisible(input_firstName, jsonFiles.getRandomFieldArray("HCP"),10);
        clickAndMoveToElementClickable(button_search, 10);
        switchToParentFrame();
    }

    public HashMap<String,String> getAndSelectCareTeamMemberDetails() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        HashMap<String, String> hcpDetails = new HashMap<>();
        waitForNumberOfElementsToBeMoreThanBy(row_addressElements, 0, 20);
        int index = 1;
        for (WebElement el : row_address){
            if(getWebElementText(el).equalsIgnoreCase("")){
                index++;
            }else{
                break;
            }
        }
        By rows = By.xpath("//table[@id='cust-table']//tbody/tr["+index+"]//td");
        if(waitForPresenceOfAllElementsLocatedBy(rows, 10)){
            List<WebElement> list_rows = getWebElementList(rows);
            for(WebElement el : list_rows){
                hcpDetails.put(getWebElementAttribute(el, "data-label"), getWebElementText(el));
            }
            clickElementClickable(list_rows.get(0), 10);
        }
        switchToParentFrame();
        return hcpDetails;
    }

    public void selectRelationShipOption(){
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        waitForElementVisibility(dropdown_relationship, 10);
        scrollToWebElementJS(dropdown_relationship);
        selectRandomDropDownNotNone(dropdown_relationship);
        switchToParentFrame();
    }

    public void clickCreateCareTeamMember() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        clickAndMoveToElementClickable(button_createCareTeamMember, 10);
        switchToParentFrame();
    }

    public void clickNewAccount() throws Exception {
        waitForPageToLoad();
        waitForElementVisibility(iframe_pageInformation, 10);
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 30);
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
        waitForElementVisibility(iframe_pageInformation, 10);
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 30);
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
}
