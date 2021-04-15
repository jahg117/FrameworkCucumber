package pageObject.pages.accessServices.customerLookup;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.util.HashMap;
import java.util.List;

public class CustomerLookupPage extends CommonFunctions {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "(//iframe[@title='accessibility title'])[last()]")
    private WebElement iframe_pageInformation;

    private By icon_loadPage = By.xpath("//span[contains(@id,'actionstatus.stop') and @style='display: none;']");

    @FindBy(xpath = "//button[contains(@onClick,'openAccountCreationTab')]")
    private WebElement button_newAccount;

    @FindBy(xpath = "//*[contains(@name,'fname')]")
    private WebElement input_searchFirstName;

    @FindBy(xpath = "//*[contains(@name,'mname')]")
    private WebElement input_searchMiddleName;

    @FindBy(xpath = "//*[contains(@name,'lname')]")
    private WebElement input_searchLastName;

    @FindBy(xpath = "//*[contains(@name,'dob')]")
    private WebElement input_searchDOB;

    @FindBy(xpath = "//*[contains(@name,'email')]")
    private WebElement input_email;

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

    @FindBy(xpath = "//*[@class='slds-text-title_caps']")
    private WebElement table_resultsTableHeaders;

    @FindBy(xpath = "//table[@id='cust-table']//td[@data-label='Address']")
    private List<WebElement> row_address;
    private By row_addressElements = By.xpath("//table[@id='cust-table']//td[@data-label='First Name']");

    @FindBy(xpath = "//label[contains(text(),'Relationship')]/../..//select")
    private WebElement dropdown_relationship;

    @FindBy(xpath = "//input[@value='Create CareTeam Member']")
    private WebElement button_createCareTeamMember;

    @FindBy(xpath = "//input[contains(@class,'input name')]")
    private WebElement input_searchFacilityName;

    @FindBy(xpath = "//*[contains(@id,'ext-id')]")
    private WebElement input_searchExternalID;

    @FindBy(xpath = "//*[contains(@id,'npi')]")
    private WebElement input_searchNPI;

    @FindBy(xpath = "//*[contains(@id,'email')][not(@id='emailFieldNext')]")
    private WebElement input_searchEmail;

    @FindBy(xpath = "//*[contains(@id,'phone')]")
    private WebElement input_searchPhoneOrFax;

    @FindBy(xpath = "//*[contains(@id,'addr')]")
    private WebElement input_searchAddressLine1;

    @FindBy(xpath = "//*[contains(@id,'state')][1]")
    private WebElement input_searchState;

    @FindBy(xpath = "//*[contains(@id,'city')]")
    private WebElement input_searchCity;

    @FindBy(xpath = "//*[contains(@id,'zip')]")
    private WebElement input_searchZipCode;

    @FindBy(xpath = "//*[contains(@id,'country')]")
    private WebElement input_searchCountry;

    @FindBy(xpath = "//div[contains(text(),'No results found')]|//*[text()='No results found']")
    private WebElement message_searchNoResults;

    @FindBy(xpath = "//div[@class='messageText']")
    private WebElement message_errorMessageAtSearch;

    @FindBy(xpath = "//input[@class='caseContact']")
    private WebElement checkbox_caseContact;

    @FindBy(xpath = "//input[@class='affiliateAccount']")
    private WebElement checkbox_usernameAddress;

    @FindBy(xpath = "//input[@class='casecontactFaxNumber']")
    private List<WebElement> checkbox_caseContactCaseNumber;

    @FindBy(xpath = "//input[@class='casecontactPhoneNumber']")
    private List<WebElement> checkbox_casePhoneNumber;


    /*
    @FindBy(xpath = "//*[@class='pbSubsection']/..//*[@checked='checked']")
    private WebElement checkbox_relationshipCheckbox;
*/
    @FindBy(xpath = "//*[@class='pbSubsection']/..//*[@type='checkbox']")
    private List<WebElement> checkbox_relationshipCheckbox;
    //private WebElement checkbox_relationshipCheckbox;


    public void searchRandomFirstName() throws Exception {
        JsonFiles jsonFiles = new JsonFiles();
        jsonFiles.setFileName("ExistingHCP");
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        sendKeysElementVisible(input_searchFirstName, jsonFiles.getRandomFieldArray("HCP"), 10);
        clickAndMoveToElementClickable(button_search, 10);
        switchToParentFrame();
    }

    public HashMap<String, String> getAndSelectCareTeamMemberDetails() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        if (waitForPresenceOfAllElementsLocatedBy(icon_loadPage, 3)) {
            waitForNumberOfElementsToBe(icon_loadPage, 0, 10);
        }
        HashMap<String, String> hcpDetails = new HashMap<>();
        waitForNumberOfElementsToBeMoreThanBy(row_addressElements, 0, 30);
        int index = 1;
        for (WebElement el : row_address) {
            if (getWebElementText(el).equalsIgnoreCase("")) {
                index++;
            } else {
                break;
            }
        }
        By rows = By.xpath("//table[@id='cust-table']//tbody/tr[" + index + "]//td");
        if (waitForPresenceOfAllElementsLocatedBy(rows, 10)) {
            List<WebElement> list_rows = getWebElementList(rows);
            waitForElementListVisible(list_rows, 10);
            for (WebElement el : list_rows) {
                hcpDetails.put(getWebElementAttribute(el, "data-label"), getWebElementText(el));
            }
            waitForElementClickable(list_rows.get(0), 10);
            clickAndMoveToElementClickable(list_rows.get(0), 10);
        }
        switchToParentFrame();
        return hcpDetails;
    }

    public void selectCareTeamMemberAddressDetails() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        if (waitForPresenceOfAllElementsLocatedBy(icon_loadPage, 3)) {
            waitForNumberOfElementsToBe(icon_loadPage, 0, 10);
        }
        waitForNumberOfElementsToBeMoreThanBy(row_addressElements, 0, 30);
        waitForElementVisibility(checkbox_usernameAddress, 10);
        scrollToWebElementJS(checkbox_usernameAddress);
        clickAndMoveToElementClickable(checkbox_usernameAddress, 10);
        switchToParentFrame();
    }

    public void selectCaseContactOption() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, longWait());
        if (waitForPresenceOfAllElementsLocatedBy(icon_loadPage, shortWait())) {
            waitForNumberOfElementsToBe(icon_loadPage, 0, mediumWait());
        }
        /*
        if(waitForElementVisibility(checkbox_caseContact, 5)){
            waitForElementClickable(checkbox_caseContact, 5);
            scrollToWebElementJS(checkbox_caseContact);
            clickAndMoveToElementClickable(checkbox_caseContact, 10);
            waitForElementClickable(checkbox_caseContactCaseNumber, 20);
            if(!isClickableElementSelected(checkbox_caseContactCaseNumber, 3)){
                clickAndMoveToElementClickable(checkbox_caseContactCaseNumber, 10);
            }
        }*/
        By relationshipCheckboxes = By.xpath("//*[@class='pbSubsection']/..//*[@type='checkbox']");
        for (WebElement checkbox : getWebElementList(relationshipCheckboxes)) {
            String checkboxFlag = checkbox.getAttribute("checked");
            if (checkboxFlag == null || checkboxFlag.isEmpty() || !checkboxFlag.equalsIgnoreCase("true")) {
                scrollToWebElementJS(checkbox);
                waitForElementClickable(checkbox, shortWait());
                clickElementJS(checkbox);
                waitForPageToLoad();
            }
        }
        waitForPageToLoad();
        if (!checkbox_caseContactCaseNumber.isEmpty()) {
            waitForElementVisibility(checkbox_caseContactCaseNumber.get(0), shortWait());
                if (!isClickableElementSelected(checkbox_caseContactCaseNumber.get(0), shortWait())) {
                    clickElementJS(checkbox_caseContactCaseNumber.get(0));
                    waitForPageToLoad();
                    waitForElementVisibility(checkbox_casePhoneNumber.get(0), shortWait());
                    clickElementJS(checkbox_casePhoneNumber.get(0));
                    waitForPageToLoad();

                }
            }
        switchToParentFrame();
    }

    public void selectRelationShipOption() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        waitForElementVisibility(dropdown_relationship, 10);
        scrollToWebElementJS(dropdown_relationship);
        selectRandomDropDownNotNone(dropdown_relationship);
        waitForPageToLoad();
        switchToParentFrame();
    }

    public void selectRelationshipOption(String option) throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        waitForElementVisibility(dropdown_relationship, 10);
        scrollToWebElementJS(dropdown_relationship);
        selectDropDownClickableByText(dropdown_relationship, option, 10);
        waitForPageToLoad();
        if (waitForPresenceOfAllElementsLocatedBy(icon_loadPage, 3)) {
            waitForNumberOfElementsToBe(icon_loadPage, 0, 10);
        }
        switchToParentFrame();
    }

    public void clickCreateCareTeamMember() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, longWait());
        if (waitForPresenceOfAllElementsLocatedBy(icon_loadPage, 3)) {
            waitForNumberOfElementsToBe(icon_loadPage, 0, 10);
        }
        scrollToWebElementJS(button_createCareTeamMember);
        clickAndMoveToElementClickable(button_createCareTeamMember, mediumWait());
        waitForPageToLoad();
        if (waitForPresenceOfAllElementsLocatedBy(icon_loadPage, 3)) {
            waitForNumberOfElementsToBe(icon_loadPage, 0, 10);
        }
        switchToParentFrame();
    }

    public void clickNewAccount() throws Exception {
        waitForPageToLoad();
        waitForElementVisibility(iframe_pageInformation, mediumWait());
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, longWait());
        clickAndMoveToElementClickable(button_newAccount, mediumWait());
        switchToParentFrame();
    }

    /**
     * Method used to search a CPC by fist name and last name at CustomerLookup search
     *
     * @param firstName it contains a string with the first name used to filled in the first name input at CustomerLooup
     * @param lastName  it contains a string with the last name used to filled in the first name input at CustomerLooup
     * @throws Exception
     * @author J.Ruano
     */
    public void searchCPCByName(String firstName, String lastName) throws Exception {
        String filterOption = "hcp";
        if (!checkbox_CheckedList.isEmpty()) {
            uncheckCheckbox(checkbox_CheckedList);
        }
        filterByCheckbox(filterOption);
        sendKeysElementClickable(input_searchFirstName, firstName, mediumWait());
        sendKeysElementClickable(input_searchLastName, lastName, mediumWait());
    }

    /**
     * Method used to search a CPC by fist name and last name at CustomerLookup search
     *
     * @param cpcID it contains the ID of the CPC that will be used to search it at CustomerLookup
     * @throws Exception
     * @author J.Ruano
     */
    public void searchCPCByID(String cpcID) throws Exception {//TBD FROM WHERE WE ARE GOING TO GET THE EXTERNAL ID
        String filterOption = "cpc";
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, longWait());
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
        do {
            clickElementClickable(checkbox_CheckedList.get(counterWE), mediumWait());
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
        switch (filterOption.trim().toLowerCase()) {
            case "hca":
                clickElementVisible(checkbox_hca, mediumWait());
                break;

            case "hcp":
                clickElementVisible(checkbox_hcp, mediumWait());
                break;

            case "cpc":
                clickElementVisible(checkbox_cpc, mediumWait());
                break;

            case "emp":
                clickElementVisible(checkbox_emp, mediumWait());
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
        clickAndMoveToElementClickable(link_AZID, mediumWait());
    }

    public void searchFirstName(String firstName) throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        sendKeysElementVisible(input_searchFirstName, firstName, 10);
        clickAndMoveToElementClickable(button_search, 10);
        switchToParentFrame();
    }

    public void searchFirstNameByHCA(String firstName) throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 20);
        sendKeysElementVisible(input_searchFirstName, firstName, 10);
        clickAndMoveToElementClickable(checkbox_hca, 10);
        if (waitForPresenceOfAllElementsLocatedBy(icon_loadPage, 3)) {
            waitForNumberOfElementsToBe(icon_loadPage, 0, 10);
        }
        sendKeysElementVisible(input_searchFirstName, firstName, 10);
        clickAndMoveToElementClickable(button_search, 10);
        switchToParentFrame();
    }

    /**
     * Used to do a dummy search to be able to create a New Account
     *
     * @param searchValue it contains the string value to search
     * @param accountType this is to select which type of account it will be used for search i.e. HCA, CPC, HCP, EMP
     * @throws Exception
     * @author J.Ruano
     */
    public void doDummySearch(String searchValue, String accountType) throws Exception {
        waitForPageToLoad();
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, 15);
        waitForElementVisibility(input_searchFirstName, 15);
        uncheckCheckbox(checkbox_CheckedList);
        filterByCheckbox(accountType);
        switch (accountType.trim().toLowerCase()) {
            case "hca":
                waitForElementClickable(input_searchFacilityName, mediumWait());
                clickAndMoveToElementVisible(input_searchFacilityName, mediumWait());
                if (waitForPresenceOfAllElementsLocatedBy(icon_loadPage, 3)) {
                    waitForNumberOfElementsToBe(icon_loadPage, 0, 10);
                }
                sendKeysAndMoveToElementClickable(input_searchFacilityName, searchValue, mediumWait());
                break;

            case "hcp":
                //clickAndMoveToElementVisible(input_searchFirstName, mediumWait());//JR
                //sendKeysAndMoveToElementClickable(input_searchFirstName, searchValue, mediumWait());


                clickAndMoveToElementVisible(input_email, mediumWait());//JR
                sendKeysAndMoveToElementClickable(input_email, "testinghcp@testaz.com", mediumWait());
                break;

            case "cpc":
                clickAndMoveToElementVisible(input_searchFirstName, mediumWait());
                sendKeysAndMoveToElementClickable(input_searchFirstName, searchValue, mediumWait());
                break;

            case "emp":
                clickAndMoveToElementVisible(input_searchFirstName, mediumWait());
                sendKeysAndMoveToElementClickable(input_searchFirstName, searchValue, mediumWait());
                break;
        }
        clickElementVisible(button_search, mediumWait());
        switchToDefaultContentFrame();
    }

    /**
     * This method is used to uncheck and check the filter for search i.e. facility, consumer, hcp, employee
     *
     * @param accountType this is to select which type of account it will be used for search i.e. HCA, CPC, HCP, EMP
     * @throws Exception
     * @author J.Ruano
     */
    public void selectSearchFilter(String accountType) throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_pageInformation, longWait());
        uncheckCheckbox(checkbox_CheckedList);
        filterByCheckbox(accountType);
    }

    //============FACILITY SEARCH OPERATION

    /**
     * This method creates the data required to create a Facility search operation
     *
     * @param externalID        it contains the external ID of the account this is unique
     * @param npi               it contains the National Provider Identifier
     * @param nameHCA           it contains the name for HCA account
     * @param email             it contains the email for HCA account
     * @param phoneOrFax        it contains the phone or fax for HCA account
     * @param addressLine1      it contains the address for HCA account
     * @param state             it contains the state for HCA account
     * @param city              it contains the city for HCA account
     * @param zipCode           it contains the zipcode for HCA account
     * @param country           it contains the country for HCA account
     * @param searchAllFromFile value used to know if the data for search will be obtained from account json file or from table. If it is "Y" it will gather all the data from HCARecord.json file
     * @return HashMap<String, String> with all the Data
     * @throws Exception
     */
    public HashMap<String, String> createHCADataUseToSearch(String externalID, String npi, String nameHCA, String email, String phoneOrFax, String addressLine1, String state,
                                                            String city, String zipCode, String country, String searchAllFromFile) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("HCARecord");
        HashMap<String, String> hcaDetails = new HashMap<String, String>();
        if (searchAllFromFile.trim().equalsIgnoreCase("Y")) {
            hcaDetails.put("externalID", jsonFile.getField("externalID"));
            hcaDetails.put("npi", jsonFile.getField("npi"));
            hcaDetails.put("nameHCA", jsonFile.getField("nameHCA"));
            hcaDetails.put("email", jsonFile.getField("email"));
            hcaDetails.put("phoneOrFax", jsonFile.getField("phoneOrFax"));
            hcaDetails.put("addressLine1", jsonFile.getField("addressLine1"));
            hcaDetails.put("stateCode", jsonFile.getField("stateCode"));
            hcaDetails.put("city", jsonFile.getField("city"));
            hcaDetails.put("zipCode", jsonFile.getField("zipCode"));
            hcaDetails.put("country", jsonFile.getField("country"));
        } else {
            hcaDetails.put("externalID", selectHCADataForSearch(externalID, "externalID"));
            hcaDetails.put("npi", selectHCADataForSearch(npi, "npi"));
            hcaDetails.put("nameHCA", selectHCADataForSearch(nameHCA, "nameHCA"));
            hcaDetails.put("email", selectHCADataForSearch(email, "email"));
            hcaDetails.put("phoneOrFax", selectHCADataForSearch(phoneOrFax, "phoneOrFax"));
            hcaDetails.put("addressLine1", selectHCADataForSearch(addressLine1, "addressLine1"));
            hcaDetails.put("stateCode", selectHCADataForSearch(state, "stateCode"));
            hcaDetails.put("city", selectHCADataForSearch(city, "city"));
            hcaDetails.put("zipCode", selectHCADataForSearch(zipCode, "zipCode"));
            hcaDetails.put("country", selectHCADataForSearch(country, "country"));
        }
        return hcaDetails;
    }

    /**
     * This method is to check and create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param hcaValue    it will contains the data for each field to be populated
     * @param nameOfField it is used to know which field will be evaluated
     * @return it will return the values used to create the Facility Search Operation
     * @throws Exception
     * @author J.Ruano
     */
    public String selectHCADataForSearch(String hcaValue, String nameOfField) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("HCARecord");
        String notApply = "N_A";
        String searchFromFile = "SFF".trim();
        String returnedValue = "";

        switch (nameOfField) {
            case "externalID":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("externalID");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "npi":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("npi");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "nameHCA":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("nameHCA");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "email":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("email");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "phoneOrFax":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("phoneOrFax");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "addressLine1":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("addressLine1");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "stateCode":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("stateCode");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "city":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("city");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "zipCode":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("zipCode");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "country":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("country");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;
        }
        return returnedValue;
    }

    /**
     * Used to fill all the data on each field that will be used to create a Facility (HCA) search
     *
     * @param hcaDetails it contains all the data related to Facility (HCA)
     * @throws Exception
     * @author J.Ruano
     */
    public void doAFacilitySearch(HashMap<String, String> hcaDetails) throws Exception {
        String notApply = "N_A";
        if (!hcaDetails.get("externalID").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchExternalID, hcaDetails.get("externalID"), mediumWait());
        }

        if (!hcaDetails.get("npi").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchNPI, hcaDetails.get("npi"), mediumWait());
        }

        if (!hcaDetails.get("nameHCA").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchFacilityName, hcaDetails.get("nameHCA"), mediumWait());
        }

        if (!hcaDetails.get("email").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchEmail, hcaDetails.get("email"), mediumWait());
        }

        if (!hcaDetails.get("phoneOrFax").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchPhoneOrFax, hcaDetails.get("phoneOrFax"), mediumWait());
        }

        if (!hcaDetails.get("addressLine1").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchAddressLine1, hcaDetails.get("addressLine1"), mediumWait());
        }

        if (!hcaDetails.get("stateCode").trim().equalsIgnoreCase(notApply)) {
            selectAndMoveDropdownByOptionAttributeValue(input_searchState, hcaDetails.get("stateCode"), mediumWait());
        }

        if (!hcaDetails.get("city").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchCity, hcaDetails.get("city"), mediumWait());
        }

        if (!hcaDetails.get("zipCode").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchZipCode, hcaDetails.get("zipCode"), mediumWait());
        }

        if (!hcaDetails.get("country").trim().equalsIgnoreCase(notApply)) {
            selectAndMoveDropdownByOptionAttributeValue(input_searchCountry, hcaDetails.get("country"), mediumWait());
        }
    }

    public void clickOnSearch() throws Exception {
        clickAndMoveToElementClickable(button_search, mediumWait());
    }

    /**
     * Used to validate if there were results or not according to the search performed
     *
     * @throws Exception
     * @author J.Ruano
     */
    public void validateSearchResults() throws Exception {
        if (waitForElementVisibility(message_searchNoResults, mediumWait())) {
            logger.info("No Results Were Found");
        } else {
            waitForElementVisibility(table_resultsTableHeaders, longWait());
            logger.info("What To Validate Here");
        }
    }

    //============HCP SEARCH OPERATION

    /**
     * This method creates the data required to create a Facility search operation
     *
     * @param externalID        it contains the external ID of the account this is unique
     * @param npi               it contains the National Provider Identifier
     * @param firstName         it contains the first name for HCP account
     * @param middleName        it contains the middle name for HCP account
     * @param lastName          it contains the last name for HCP account
     * @param dateOfBirth       it contains the DOB for HCP account
     * @param email             it contains the email for HCP account
     * @param phoneOrFax        it contains the phone or fax for HCP account
     * @param addressLine1      it contains the address for HCP account
     * @param stateCode         it contains the state for HCP account
     * @param city              it contains the city for HCP account
     * @param zipCode           it contains the zipcode for HCP account
     * @param country           it contains the country for HCP account
     * @param searchAllFromFile value used to know if the data for search will be obtained from account json file or from table. If it is "Y" it will gather all the data from HCPRecord.json file
     * @return HashMap<String, String> with all the Data
     * @throws Exception
     */
    public HashMap<String, String> createHCPDataUseToSearch(String externalID, String npi, String firstName, String middleName, String lastName, String dateOfBirth, String email,
                                                            String phoneOrFax, String addressLine1, String stateCode, String city, String zipCode, String country,
                                                            String searchAllFromFile) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("HCPRecord");
        HashMap<String, String> hcpDetails = new HashMap<String, String>();
        if (searchAllFromFile.trim().equalsIgnoreCase("Y")) {
            hcpDetails.put("externalID", jsonFile.getField("externalID"));
            hcpDetails.put("npi", jsonFile.getField("npi"));
            hcpDetails.put("firstName", jsonFile.getField("firstName"));
            hcpDetails.put("middleName", jsonFile.getField("middleName"));
            hcpDetails.put("lastName", jsonFile.getField("lastName"));
            hcpDetails.put("dateOfBirth", jsonFile.getField("dateOfBirth"));
            hcpDetails.put("email", jsonFile.getField("email"));
            hcpDetails.put("phoneOrFax", jsonFile.getField("phoneOrFax"));
            hcpDetails.put("addressLine1", jsonFile.getField("addressLine1"));
            hcpDetails.put("stateCode", jsonFile.getField("stateCode"));
            hcpDetails.put("city", jsonFile.getField("city"));
            hcpDetails.put("zipCode", jsonFile.getField("zipCode"));
            hcpDetails.put("country", jsonFile.getField("country"));
        } else {
            hcpDetails.put("externalID", selectHCPDataForSearch(externalID, "externalID"));
            hcpDetails.put("npi", selectHCPDataForSearch(npi, "npi"));
            hcpDetails.put("firstName", selectHCPDataForSearch(firstName, "firstName"));
            hcpDetails.put("middleName", selectHCPDataForSearch(middleName, "middleName"));
            hcpDetails.put("lastName", selectHCPDataForSearch(lastName, "lastName"));
            hcpDetails.put("dateOfBirth", selectHCPDataForSearch(dateOfBirth, "dateOfBirth"));
            hcpDetails.put("email", selectHCPDataForSearch(email, "email"));
            hcpDetails.put("phoneOrFax", selectHCPDataForSearch(phoneOrFax, "phoneOrFax"));
            hcpDetails.put("addressLine1", selectHCPDataForSearch(addressLine1, "addressLine1"));
            hcpDetails.put("stateCode", selectHCPDataForSearch(stateCode, "stateCode"));
            hcpDetails.put("city", selectHCPDataForSearch(city, "city"));
            hcpDetails.put("zipCode", selectHCPDataForSearch(zipCode, "zipCode"));
            hcpDetails.put("country", selectHCPDataForSearch(country, "country"));
        }
        return hcpDetails;
    }

    /**
     * This method is to check and create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param hcpValue    it will contains the data for each field to be populated
     * @param nameOfField it is used to know which field will be evaluated
     * @return it will return the values used to create the HCP Search Operation
     * @throws Exception
     * @author J.Ruano
     */
    public String selectHCPDataForSearch(String hcpValue, String nameOfField) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("HCPRecord");
        String notApply = "N_A";
        String searchFromFile = "SFF".trim();
        String returnedValue = "";

        switch (nameOfField) {
            case "externalID":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("externalID");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "npi":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("npi");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "firstName":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("firstName");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "middleName":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("middleName");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "lastName":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("lastName");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "dateOfBirth":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("dateOfBirth").replace("/", "");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "email":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("email");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "phoneOrFax":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("phoneOrFax");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "addressLine1":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("addressLine1");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "stateCode":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("stateCode");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "city":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("city");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "zipCode":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("zipCode");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "country":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("country");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;
        }
        return returnedValue;
    }

    /**
     * Used to fill all the data on each field that will be used to create a HCP search
     *
     * @param hcpDetails it contains all the data related to HCP
     * @throws Exception
     * @author J.Ruano
     */
    public void doAHCPSearch(HashMap<String, String> hcpDetails) throws Exception {
        String notApply = "N_A";
        if (!hcpDetails.get("externalID").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchExternalID, hcpDetails.get("externalID"), mediumWait());
        }

        if (!hcpDetails.get("npi").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchNPI, hcpDetails.get("npi"), mediumWait());
        }

        if (!hcpDetails.get("firstName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchFirstName, hcpDetails.get("firstName"), mediumWait());
        }

        if (!hcpDetails.get("middleName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchMiddleName, hcpDetails.get("middleName"), mediumWait());
        }

        if (!hcpDetails.get("lastName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchLastName, hcpDetails.get("lastName"), mediumWait());
        }

        if (!hcpDetails.get("dateOfBirth").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchDOB, hcpDetails.get("dateOfBirth").replace("/", ""), mediumWait());
        }

        if (!hcpDetails.get("email").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchEmail, hcpDetails.get("email"), mediumWait());
        }

        if (!hcpDetails.get("phoneOrFax").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchPhoneOrFax, hcpDetails.get("phoneOrFax"), mediumWait());
        }

        if (!hcpDetails.get("addressLine1").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchAddressLine1, hcpDetails.get("addressLine1"), mediumWait());
        }

        if (!hcpDetails.get("stateCode").trim().equalsIgnoreCase(notApply)) {
            selectAndMoveDropdownByOptionAttributeValue(input_searchState, hcpDetails.get("stateCode"), mediumWait());
        }

        if (!hcpDetails.get("city").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchCity, hcpDetails.get("city"), mediumWait());
        }

        if (!hcpDetails.get("zipCode").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchZipCode, hcpDetails.get("zipCode"), mediumWait());
        }

        if (!hcpDetails.get("country").trim().equalsIgnoreCase(notApply)) {
            selectAndMoveDropdownByOptionAttributeValue(input_searchCountry, hcpDetails.get("country"), mediumWait());
        }
    }


    //============CPC SEARCH OPERATION

    /**
     * This method creates the data required to create a Facility search operation
     *
     * @param externalID        it contains the external ID of the account this is unique
     * @param firstName         it contains the first name for CPC account
     * @param middleName        it contains the middle name for CPC account
     * @param lastName          it contains the last name for CPC account
     * @param dateOfBirth       it contains the DOB for CPC account
     * @param careGiver         this is used in case there is a caregiver into the new CPC account so it can be selected
     * @param email             it contains the email for CPC account
     * @param phoneOrFax        it contains the phone or fax for CPC account
     * @param addressLine1      it contains the address for CPC account
     * @param stateCode         it contains the state for CPC account
     * @param city              it contains the city for CPC account
     * @param zipCode           it contains the zipcode for CPC account
     * @param country           it contains the country for CPC account
     * @param searchAllFromFile value used to know if the data for search will be obtained from account json file or from table. If it is "Y" it will gather all the data from CPCRecord.json file
     * @return HashMap<String, String> with all the Data
     * @throws Exception
     */
    public HashMap<String, String> createCPCDataUseToSearch(String externalID, String firstName, String middleName, String lastName, String dateOfBirth, String careGiver,
                                                            String email, String phoneOrFax, String addressLine1, String stateCode, String city, String zipCode, String country,
                                                            String searchAllFromFile) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("CPCRecord");
        HashMap<String, String> cpcDetails = new HashMap<String, String>();
        if (searchAllFromFile.trim().equalsIgnoreCase("Y")) {
            cpcDetails.put("externalID", jsonFile.getField("externalID"));
            cpcDetails.put("firstName", jsonFile.getField("firstName"));
            cpcDetails.put("middleName", jsonFile.getField("middleName"));
            cpcDetails.put("lastName", jsonFile.getField("lastName"));
            cpcDetails.put("dateOfBirth", jsonFile.getField("dateOfBirth"));
            cpcDetails.put("careGiver", jsonFile.getField("careGiver"));
            cpcDetails.put("email", jsonFile.getField("email"));
            cpcDetails.put("phoneOrFax", jsonFile.getField("phoneOrFax"));
            cpcDetails.put("addressLine1", jsonFile.getField("addressLine1"));
            cpcDetails.put("stateCode", jsonFile.getField("stateCode"));
            cpcDetails.put("city", jsonFile.getField("city"));
            cpcDetails.put("zipCode", jsonFile.getField("zipCode"));
            cpcDetails.put("country", jsonFile.getField("country"));
        } else {
            cpcDetails.put("externalID", selectCPCDataForSearch(externalID, "externalID"));
            cpcDetails.put("firstName", selectCPCDataForSearch(firstName, "firstName"));
            cpcDetails.put("middleName", selectCPCDataForSearch(middleName, "middleName"));
            cpcDetails.put("lastName", selectCPCDataForSearch(lastName, "lastName"));
            cpcDetails.put("dateOfBirth", selectCPCDataForSearch(dateOfBirth, "dateOfBirth"));
            cpcDetails.put("careGiver", selectCPCDataForSearch(careGiver, "careGiver"));
            cpcDetails.put("email", selectCPCDataForSearch(email, "email"));
            cpcDetails.put("phoneOrFax", selectCPCDataForSearch(phoneOrFax, "phoneOrFax"));
            cpcDetails.put("addressLine1", selectCPCDataForSearch(addressLine1, "addressLine1"));
            cpcDetails.put("stateCode", selectCPCDataForSearch(stateCode, "stateCode"));
            cpcDetails.put("city", selectCPCDataForSearch(city, "city"));
            cpcDetails.put("zipCode", selectCPCDataForSearch(zipCode, "zipCode"));
            cpcDetails.put("country", selectCPCDataForSearch(country, "country"));
        }
        return cpcDetails;
    }

    /**
     * This method is to check and create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param cpcValue    it will contains the data for each field to be populated
     * @param nameOfField it is used to know which field will be evaluated
     * @return it will return the values used to create the CPC Search Operation
     * @throws Exception
     * @author J.Ruano
     */
    public String selectCPCDataForSearch(String cpcValue, String nameOfField) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("CPCRecord");
        String notApply = "N_A";
        String searchFromFile = "SFF".trim();
        String returnedValue = "";

        switch (nameOfField) {
            case "externalID":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("externalID");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "firstName":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("firstName");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "middleName":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("middleName");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "lastName":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("lastName");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "dateOfBirth":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("dateOfBirth");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "careGiver":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("careGiver");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "email":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("email");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "phoneOrFax":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("phoneOrFax");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "addressLine1":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("addressLine1");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "stateCode":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("stateCode");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "city":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("city");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "zipCode":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("zipCode");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "country":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("country");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;
        }
        return returnedValue;
    }

    /**
     * Used to fill all the data on each field that will be used to create a CPC search
     *
     * @param cpcDetails it contains all the data related to CPC
     * @throws Exception
     * @author J.Ruano
     */
    public void doACPCSearch(HashMap<String, String> cpcDetails) throws Exception {
        String notApply = "N_A";
        if (!cpcDetails.get("externalID").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchExternalID, cpcDetails.get("externalID"), mediumWait());
        }

        if (!cpcDetails.get("firstName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchFirstName, cpcDetails.get("firstName"), mediumWait());
        }

        if (!cpcDetails.get("middleName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchMiddleName, cpcDetails.get("middleName"), mediumWait());
        }

        if (!cpcDetails.get("lastName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchLastName, cpcDetails.get("lastName"), mediumWait());
        }

        if (!cpcDetails.get("dateOfBirth").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchDOB, cpcDetails.get("dateOfBirth").replace("/", ""), mediumWait());
        }
        if (!cpcDetails.get("email").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchEmail, cpcDetails.get("email"), mediumWait());
        }

        if (!cpcDetails.get("phoneOrFax").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchPhoneOrFax, cpcDetails.get("phoneOrFax"), mediumWait());
        }

        if (!cpcDetails.get("addressLine1").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchAddressLine1, cpcDetails.get("addressLine1"), mediumWait());
        }

        if (!cpcDetails.get("stateCode").trim().equalsIgnoreCase(notApply)) {
            selectAndMoveDropdownByOptionAttributeValue(input_searchState, cpcDetails.get("stateCode"), mediumWait());
        }

        if (!cpcDetails.get("city").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchCity, cpcDetails.get("city"), mediumWait());
        }

        if (!cpcDetails.get("zipCode").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchZipCode, cpcDetails.get("zipCode"), mediumWait());
        }

        if (!cpcDetails.get("country").trim().equalsIgnoreCase(notApply)) {
            selectAndMoveDropdownByOptionAttributeValue(input_searchCountry, cpcDetails.get("country"), mediumWait());
        }
    }

    //============EMPLOYEE SEARCH OPERATION

    /**
     * This method creates the data required to create a Facility search operation
     *
     * @param firstName         it contains the first name for Employee account
     * @param middleName        it contains the middle name for Employee account
     * @param lastName          it contains the last name for Employee account
     * @param searchAllFromFile value used to know if the data for search will be obtained from account json file or from table. If it is "Y" it will gather all the data from EmployeeRecord.json file
     * @return HashMap<String, String> with all the Data
     * @throws Exception
     */
    public HashMap<String, String> createEmployeeDataUseToSearch(String externalID, String firstName, String
            middleName, String lastName, String searchAllFromFile) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("EmployeeRecord");
        HashMap<String, String> employeeDetails = new HashMap<String, String>();
        if (searchAllFromFile.trim().equalsIgnoreCase("Y")) {
            employeeDetails.put("externalID", jsonFile.getField("externalID"));
            employeeDetails.put("firstName", jsonFile.getField("firstName"));
            employeeDetails.put("middleName", jsonFile.getField("middleName"));
            employeeDetails.put("lastName", jsonFile.getField("lastName"));
        } else {
            employeeDetails.put("externalID", selectEmployeeDataForSearch(externalID, "externalID"));
            employeeDetails.put("firstName", selectEmployeeDataForSearch(firstName, "firstName"));
            employeeDetails.put("middleName", selectEmployeeDataForSearch(middleName, "middleName"));
            employeeDetails.put("lastName", selectEmployeeDataForSearch(lastName, "lastName"));
        }
        return employeeDetails;
    }

    /**
     * This method is to check and create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param employeeValue it will contains the data for each field to be populated
     * @param nameOfField   it is used to know which field will be evaluated
     * @return it will return the values used to create the Employee Search Operation
     * @throws Exception
     * @author J.Ruano
     */
    public String selectEmployeeDataForSearch(String employeeValue, String nameOfField) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("EmployeeRecord");
        String notApply = "N_A";
        String searchFromFile = "SFF".trim();
        String returnedValue = "";

        switch (nameOfField) {
            case "externalID":
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("externalID");
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            case "firstName":
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("firstName");
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            case "middleName":
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("middleName");
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            case "lastName":
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(searchFromFile)) {
                    returnedValue = jsonFile.getField("lastName");
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;
        }
        return returnedValue;
    }

    /**
     * Used to fill all the data on each field that will be used to create a Employee search
     *
     * @param employeeDetails it contains all the data related to Employee
     * @throws Exception
     * @author J.Ruano
     */
    public void doAEmployeeSearch(HashMap<String, String> employeeDetails) throws Exception {
        String notApply = "N_A";
        if (!employeeDetails.get("externalID").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchExternalID, employeeDetails.get("externalID"), mediumWait());
        }

        if (!employeeDetails.get("firstName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchFirstName, employeeDetails.get("firstName"), mediumWait());
        }

        if (!employeeDetails.get("middleName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchMiddleName, employeeDetails.get("middleName"), mediumWait());
        }

        if (!employeeDetails.get("lastName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_searchLastName, employeeDetails.get("lastName"), mediumWait());
        }
    }
}