package pageObject.pages.accessServices.customerLookup;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;
import utils.Values;

import java.util.HashMap;
import java.util.List;

public class CustomerLookupPage extends CommonFunctions {
    private final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "(//iframe[@title='accessibility title'])[last()]")
    private WebElement iframePageInformation;

    private By iconLoadPage = By.xpath("//span[contains(@id,'actionstatus.stop') and @style='display: none;']");

    @FindBy(xpath = "//button[contains(@onClick,'openAccountCreationTab')]")
    private WebElement buttonNewAccount;

    @FindBy(xpath = "//*[contains(@name,'fname')]")
    private WebElement inputSearchFirstName;

    @FindBy(xpath = "//*[contains(@name,'mname')]")
    private WebElement inputSearchMiddleName;

    @FindBy(xpath = "//*[contains(@name,'lname')]")
    private WebElement inputSearchLastName;

    @FindBy(xpath = "//*[contains(@name,'dob')]")
    private WebElement inputSearchDOB;

    @FindBy(xpath = "//*[contains(@name,'email')]")
    private WebElement inputEmail;

    @FindBy(xpath = "//legend/following::input[@type='checkbox']")
    private List<WebElement> checkboxFilterList;

    @FindBy(xpath = "//legend/following::input[@type='checkbox'][contains(@name,'hca')]")
    private WebElement checkboxHCA;

    @FindBy(xpath = "//legend/following::input[@type='checkbox'][contains(@name,'hcp')]")
    private WebElement checkboxHCP;

    @FindBy(xpath = "//legend/following::input[@type='checkbox'][contains(@name,'cpc')]")
    private WebElement checkboxCPC;

    @FindBy(xpath = "//legend/following::input[@type='checkbox'][contains(@name,'emp')]")
    private WebElement checkboxEMP;

    @FindBy(xpath = "//legend/following::input[contains(@name,'ext-id')]")
    private WebElement inputExternalID;

    @FindBy(xpath = "//*[@checked='checked']")
    private List<WebElement> checkboxCheckedList;

    @FindBy(xpath = "//a[normalize-space(text())='EBcEenr']")//TBD
    private WebElement linkAZID;

    @FindBy(xpath = "//*[@id='searchButton']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//span[normalize-space(text())='Logged out']")
    private WebElement buttonLoggedOut;

    @FindBy(xpath = "//*[@class='slds-text-title_caps']")
    private WebElement tableResultsTableHeaders;

    @FindBy(xpath = "//table[@id='cust-table']//td[@data-label='Address']")
    private List<WebElement> rowAddress;
    private By rowAddressElements = By.xpath("//table[@id='cust-table']//td[@data-label='First Name']");

    @FindBy(xpath = "//label[contains(text(),'Relationship')]/following::select")
    private WebElement dropdownRelationship;

    @FindBy(xpath = "//input[@value='Create CareTeam Member']")
    private WebElement buttonCreateCareTeamMember;

    @FindBy(xpath = "//input[contains(@class,'input name')]")
    private WebElement inputSearchFacilityName;

    @FindBy(xpath = "//*[contains(@id,'email')][not(@id='emailFieldNext')]")
    private WebElement inputSearchEmail;

    @FindBy(xpath = "//*[contains(@id,'ext-id')]")
    private WebElement inputSearchExternalID;

    @FindBy(xpath = "//*[contains(@id,'npi')]")
    private WebElement inputSearchNPI;

    @FindBy(xpath = "//*[contains(@id,'phone')]")
    private WebElement inputSearchPhoneOrFax;

    @FindBy(xpath = "//*[contains(@id,'addr')]")
    private WebElement inputSearchAddressLine1;

    @FindBy(xpath = "//*[contains(@id,'state')][1]")
    private WebElement inputSearchState;

    @FindBy(xpath = "//*[contains(@id,'city')]")
    private WebElement inputSearchCity;

    @FindBy(xpath = "//*[contains(@id,'zip')]")
    private WebElement inputSearchZipCode;

    @FindBy(xpath = "//*[contains(@id,'country')]")
    private WebElement inputSearchCountry;

    @FindBy(xpath = "//div[contains(text(),'No results found')]|//*[text()='No results found']")
    private WebElement messageSearchNoResults;

    @FindBy(xpath = "//div[@class='messageText']")
    private WebElement messageErrorMessageAtSearch;

    @FindBy(xpath = "//input[@class='caseContact']")
    private WebElement checkboxCaseContact;

    @FindBy(xpath = "//input[@class='affiliateAccount']")
    private WebElement checkboxUsernameAddress;

    @FindBy(xpath = "//input[@class='casecontactFaxNumber']")
    private WebElement checkboxCaseContactCaseNumber;

    @FindBy(xpath = "//input[@class='casecontactPhoneNumber']")
    private WebElement checkboxCaseContactPhoneNumber;

    @FindBy(xpath = "//input[@class='casecontactPhoneNumber']")
    private List<WebElement> checkboxCasePhoneNumber;


    @FindBy(xpath = "//*[@class='pbSubsection']/..//*[@type='checkbox']")
    private List<WebElement> checkboxRelationshipCheckbox;


    public void searchRandomFirstName() throws Exception {
        JsonFiles jsonFiles = new JsonFiles();
        jsonFiles.setFileName(Values.KEYVALUE_EXISTINGHCPRECORD);
        switchToFrameByWebElementIndexOrName(iframePageInformation, mediumWait());
        sendKeysElementVisible(inputSearchFirstName, jsonFiles.getRandomFieldArray("HCP"), mediumWait());
        clickAndMoveToElementClickable(buttonSearch, mediumWait());
        switchToParentFrame();
    }

    public HashMap<String, String> getAndSelectCareTeamMemberDetails() throws Exception {
        switchToFrameByWebElementIndexOrName(iframePageInformation, mediumWait());
        if (waitForPresenceOfAllElementsLocatedBy(iconLoadPage, shortWait())) {
            waitForNumberOfElementsToBe(iconLoadPage, 0, mediumWait());
        }
        HashMap<String, String> hcpDetails = new HashMap<>();
        waitForNumberOfElementsToBeMoreThanBy(rowAddressElements, 0, longWait());
        int index = 1;
        for (WebElement el : rowAddress) {
            if (getWebElementText(el).equalsIgnoreCase(Values.REPLACETO_EMPTY)) {
                index++;
            } else {
                break;
            }
        }
        By rows = By.xpath("//table[@id='cust-table']//tbody/tr[" + index + "]//td");
        if (waitForPresenceOfAllElementsLocatedBy(rows, mediumWait())) {
            List<WebElement> list_rows = getWebElementList(rows);
            waitForElementListVisible(list_rows, mediumWait());
            for (WebElement el : list_rows) {
                hcpDetails.put(getWebElementAttribute(el, "data-label"), getWebElementText(el));
            }
            waitForElementClickable(list_rows.get(0), mediumWait());
            clickAndMoveToElementClickable(list_rows.get(0), mediumWait());
        }
        switchToParentFrame();
        return hcpDetails;
    }

    public void selectCareTeamMemberAddressDetails() throws Exception {
        switchToFrameByWebElementIndexOrName(iframePageInformation, mediumWait());
        if (waitForPresenceOfAllElementsLocatedBy(iconLoadPage, shortWait())) {
            waitForNumberOfElementsToBe(iconLoadPage, 0, mediumWait());
        }
        waitForNumberOfElementsToBeMoreThanBy(rowAddressElements, 0, shortWait());
        waitForElementVisibility(checkboxUsernameAddress, shortWait());
        scrollToWebElementJS(checkboxUsernameAddress);
        clickAndMoveToElementClickable(checkboxUsernameAddress, shortWait());
        switchToParentFrame();
    }

    public void selectCaseContactOption() throws Exception {
        switchToFrameByWebElementIndexOrName(iframePageInformation, mediumWait());
        if (waitForPresenceOfAllElementsLocatedBy(iconLoadPage, shortWait())) {
            waitForNumberOfElementsToBe(iconLoadPage, 0, mediumWait());
        }
        if (waitForElementVisibility(checkboxCaseContact, shortWait())) {
            waitForElementClickable(checkboxCaseContact, shortWait());
            scrollToWebElementJS(checkboxCaseContact);
            clickAndMoveToElementClickable(checkboxCaseContact, mediumWait());
            if (waitForElementVisibility(checkboxCaseContactPhoneNumber, shortWait())) {
                clickAndMoveToElementClickable(checkboxCaseContactPhoneNumber, mediumWait());
            }
            if (waitForElementVisibility(checkboxCaseContactCaseNumber, mediumWait()) && !isClickableElementSelected(checkboxCaseContactCaseNumber, shortWait())) {
                clickAndMoveToElementClickable(checkboxCaseContactCaseNumber, mediumWait());
            }
        }
        switchToParentFrame();
    }

    public void selectRelationShipOption() throws Exception {
        switchToFrameByWebElementIndexOrName(iframePageInformation, mediumWait());
        waitForElementVisibility(dropdownRelationship, mediumWait());
        scrollToWebElementJS(dropdownRelationship);
        selectRandomDropDownNotNone(dropdownRelationship);
        waitForPageToLoad();
        switchToParentFrame();
    }

    public void selectRelationshipOption(String option) throws Exception {
        String relationShipValue = "";
        switchToFrameByWebElementIndexOrName(iframePageInformation, mediumWait());
        waitForElementVisibility(dropdownRelationship, mediumWait());
        scrollToWebElementJS(dropdownRelationship);
        selectAndMoveDropdownClickableByText(dropdownRelationship, relationShipValue = ctmRelationShipFilter(option), mediumWait());
        if (waitForPresenceOfAllElementsLocatedBy(iconLoadPage, shortWait())) {
            waitForNumberOfElementsToBe(iconLoadPage, 0, mediumWait());
        }
        switchToParentFrame();
    }

    public void clickCreateCareTeamMember() throws Exception {
        switchToFrameByWebElementIndexOrName(iframePageInformation, longWait());
        if (waitForPresenceOfAllElementsLocatedBy(iconLoadPage, shortWait())) {
            waitForNumberOfElementsToBe(iconLoadPage, 0, mediumWait());
        }
        scrollToWebElementJS(buttonCreateCareTeamMember);
        clickAndMoveToElementClickable(buttonCreateCareTeamMember, mediumWait());
        waitForPageToLoad();
        if (waitForPresenceOfAllElementsLocatedBy(iconLoadPage, shortWait())) {
            waitForNumberOfElementsToBe(iconLoadPage, 0, mediumWait());
        }
        switchToParentFrame();
    }

    public void clickNewAccount() throws Exception {
        waitForPageToLoad();
        waitForElementVisibility(iframePageInformation, mediumWait());
        switchToFrameByWebElementIndexOrName(iframePageInformation, longWait());
        clickAndMoveToElementClickable(buttonNewAccount, mediumWait());
        switchToParentFrame();
    }

    /**
     * Method used to search a CPC by fist name and last name at CustomerLookup search
     *
     * @param firstName it contains a string with the first name used to filled in the first name input at CustomerLooup
     * @param lastName  it contains a string with the last name used to filled in the first name input at CustomerLooup
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void searchCPCByName(String firstName, String lastName) throws Exception {
        if (!checkboxCheckedList.isEmpty()) {
            uncheckCheckbox(checkboxCheckedList);
        }
        filterByCheckbox(Values.TXT_CPC);
        sendKeysElementClickable(inputSearchFirstName, firstName, mediumWait());
        sendKeysElementClickable(inputSearchLastName, lastName, mediumWait());
    }

    /**
     * Method used to search a CPC by fist name and last name at CustomerLookup search
     *
     * @param cpcID it contains the ID of the CPC that will be used to search it at CustomerLookup
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void searchCPCByID(String cpcID) throws Exception {//TBD FROM WHERE WE ARE GOING TO GET THE EXTERNAL ID
        switchToFrameByWebElementIndexOrName(iframePageInformation, longWait());
        if (!checkboxCheckedList.isEmpty()) {
            uncheckCheckbox(checkboxCheckedList);
        }
        filterByCheckbox(Values.TXT_CPC);
    }

    /**
     * Method used to uncheck all the checkboxes with status ckecked at CustomerLookup search
     *
     * @param checkboxCheckedList it a list of all checkedboxes in CustomerLookup
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void uncheckCheckbox(List<WebElement> checkboxCheckedList) throws Exception {
        int counterWE = 0;
        do {
            clickElementClickable(checkboxCheckedList.get(counterWE), mediumWait());
            counterWE++;
        }
        while (counterWE <= (checkboxCheckedList.size() - 1));
    }

    /**
     * Method used to check the type of filter used to search at CustomerLookup search
     *
     * @param filterOption it contains the filter option i.e. cpc that it is related to Consumer/Patient/Caregiver
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void filterByCheckbox(String filterOption) throws Exception {
        switch (filterOption.trim().toLowerCase()) {
            case "hca":
                clickElementVisible(checkboxHCA, mediumWait());
                break;

            case "hcp":
                clickElementVisible(checkboxHCP, mediumWait());
                break;

            case "cpc":
                clickElementVisible(checkboxCPC, mediumWait());
                break;

            case "emp":
                clickElementVisible(checkboxEMP, mediumWait());
                break;

            default:
                logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                break;
        }
    }

    /**
     * Method to click the External ID (AZ ID) found by the search function
     *
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void selectAndClickAZID() throws Exception {
        clickAndMoveToElementClickable(linkAZID, mediumWait());
    }

    public void searchFirstName(String firstName) throws Exception {
        switchToFrameByWebElementIndexOrName(iframePageInformation, mediumWait());
        sendKeysElementVisible(inputSearchFirstName, firstName, mediumWait());
        clickAndMoveToElementClickable(buttonSearch, mediumWait());
        switchToParentFrame();
    }

    public void searchFirstNameByHCA(String firstName) throws Exception {
        switchToFrameByWebElementIndexOrName(iframePageInformation, mediumWait());
        sendKeysElementVisible(inputSearchFirstName, firstName, mediumWait());
        clickAndMoveToElementClickable(checkboxHCA, mediumWait());
        if (waitForPresenceOfAllElementsLocatedBy(iconLoadPage, shortWait())) {
            waitForNumberOfElementsToBe(iconLoadPage, 0, mediumWait());
        }
        sendKeysElementVisible(inputSearchFirstName, firstName, mediumWait());
        clickAndMoveToElementClickable(buttonSearch, mediumWait());
        switchToParentFrame();
    }

    /**
     * Used to do a dummy search to be able to create a New Account
     *
     * @param searchValue it contains the string value to search
     * @param accountType this is to select which type of account it will be used for search i.e. HCA, CPC, HCP, EMP
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void doDummySearch(String searchValue, String accountType) throws Exception {
        waitForPageToLoad();
        switchToFrameByWebElementIndexOrName(iframePageInformation, mediumWait());
        waitForElementVisibility(inputSearchFirstName, mediumWait());
        uncheckCheckbox(checkboxCheckedList);
        filterByCheckbox(accountType);
        switch (accountType.trim().toLowerCase()) {
            case "hca":
                waitForElementClickable(inputSearchFacilityName, mediumWait());
                clickAndMoveToElementVisible(inputSearchEmail, mediumWait());
                if (waitForPresenceOfAllElementsLocatedBy(iconLoadPage, shortWait())) {
                    waitForNumberOfElementsToBe(iconLoadPage, 0, mediumWait());
                }
                sendKeysAndMoveToElementClickable(inputSearchEmail, searchValue, mediumWait());
                break;

            case "hcp":
                clickAndMoveToElementVisible(inputSearchEmail, mediumWait());
                sendKeysAndMoveToElementClickable(inputSearchEmail, searchValue, mediumWait());
                break;

            case "cpc":
                clickAndMoveToElementVisible(inputSearchFirstName, mediumWait());
                sendKeysAndMoveToElementClickable(inputSearchFirstName, searchValue, mediumWait());
                break;

            case "emp":
                clickAndMoveToElementVisible(inputSearchFirstName, mediumWait());
                sendKeysAndMoveToElementClickable(inputSearchFirstName, searchValue, mediumWait());
                break;

            default:
                logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                break;
        }
        clickElementVisible(buttonSearch, mediumWait());
        if (waitForPresenceOfAllElementsLocatedBy(iconLoadPage, shortWait())) {
            waitForNumberOfElementsToBe(iconLoadPage, 0, mediumWait());
        }
        switchToDefaultContentFrame();
    }

    /**
     * This method is used to uncheck and check the filter for search i.e. facility, consumer, hcp, employee
     *
     * @param accountType this is to select which type of account it will be used for search i.e. HCA, CPC, HCP, EMP
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void selectSearchFilter(String accountType) throws Exception {
        switchToFrameByWebElementIndexOrName(iframePageInformation, longWait());
        uncheckCheckbox(checkboxCheckedList);
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
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public HashMap<String, String> createHCADataUseToSearch(String externalID, String npi, String nameHCA, String email, String phoneOrFax, String addressLine1, String state,
                                                            String city, String zipCode, String country, String searchAllFromFile) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(Values.KEYVALUE_HCARECORD);
        HashMap<String, String> hcaDetails = new HashMap<>();
        if (searchAllFromFile.trim().equalsIgnoreCase(Values.TXT_Y_VALUE)) {
            hcaDetails.put(Values.TXT_EXTERNALID, jsonFile.getField(Values.TXT_EXTERNALID));
            hcaDetails.put(Values.TXT_NPI, jsonFile.getField(Values.TXT_NPI));
            hcaDetails.put(Values.TXT_NAMEHCA, jsonFile.getField(Values.TXT_NAMEHCA));
            hcaDetails.put(Values.TXT_EMAIL, jsonFile.getField(Values.TXT_EMAIL));
            hcaDetails.put(Values.TXT_PHONEORFAX, jsonFile.getField(Values.TXT_PHONEORFAX));
            hcaDetails.put(Values.TXT_ADDRESSLINE1, jsonFile.getField(Values.TXT_ADDRESSLINE1));
            hcaDetails.put(Values.TXT_STATECODE, jsonFile.getField(Values.TXT_STATECODE));
            hcaDetails.put(Values.TXT_CITY, jsonFile.getField(Values.TXT_CITY));
            hcaDetails.put(Values.TXT_ZIPCODE, jsonFile.getField(Values.TXT_ZIPCODE));
            hcaDetails.put(Values.TXT_COUNTRY, jsonFile.getField(Values.TXT_COUNTRY));
        } else {
            hcaDetails.put(Values.TXT_EXTERNALID, selectHCADataForSearch(externalID, Values.TXT_EXTERNALID));
            hcaDetails.put(Values.TXT_NPI, selectHCADataForSearch(npi, Values.TXT_NPI));
            hcaDetails.put(Values.TXT_NAMEHCA, selectHCADataForSearch(nameHCA, Values.TXT_NAMEHCA));
            hcaDetails.put(Values.TXT_EMAIL, selectHCADataForSearch(email, Values.TXT_EMAIL));
            hcaDetails.put(Values.TXT_PHONEORFAX, selectHCADataForSearch(phoneOrFax, Values.TXT_PHONEORFAX));
            hcaDetails.put(Values.TXT_ADDRESSLINE1, selectHCADataForSearch(addressLine1, Values.TXT_ADDRESSLINE1));
            hcaDetails.put(Values.TXT_STATECODE, selectHCADataForSearch(state, Values.TXT_STATECODE));
            hcaDetails.put(Values.TXT_CITY, selectHCADataForSearch(city, Values.TXT_CITY));
            hcaDetails.put(Values.TXT_ZIPCODE, selectHCADataForSearch(zipCode, Values.TXT_ZIPCODE));
            hcaDetails.put(Values.TXT_COUNTRY, selectHCADataForSearch(country, Values.TXT_COUNTRY));
        }
        return hcaDetails;
    }

    /**
     * This method is to check and create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param hcaValue    it will contains the data for each field to be populated
     * @param nameOfField it is used to know which field will be evaluated
     * @return it will return the values used to create the Facility Search Operation
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public String selectHCADataForSearch(String hcaValue, String nameOfField) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(Values.KEYVALUE_HCARECORD);
        String returnedValue = "";

        switch (nameOfField) {
            case Values.TXT_EXTERNALID:
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_EXTERNALID);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case Values.TXT_NPI:
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_NPI);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case Values.TXT_NAMEHCA:
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_NAMEHCA);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case Values.TXT_EMAIL:
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_EMAIL);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case Values.TXT_PHONEORFAX:
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_PHONEORFAX);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case Values.TXT_ADDRESSLINE1:
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_ADDRESSLINE1);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case Values.TXT_STATECODE:
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_STATECODE);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case Values.TXT_CITY:
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_CITY);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case Values.TXT_ZIPCODE:
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_ZIPCODE);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case Values.TXT_COUNTRY:
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_COUNTRY);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
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
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void doAFacilitySearch(HashMap<String, String> hcaDetails) throws Exception {
        if (!hcaDetails.get(Values.TXT_EXTERNALID).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchExternalID, hcaDetails.get(Values.TXT_EXTERNALID), mediumWait());
        }

        if (!hcaDetails.get(Values.TXT_NPI).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchNPI, hcaDetails.get(Values.TXT_NPI), mediumWait());
        }

        if (!hcaDetails.get(Values.TXT_NAMEHCA).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchFacilityName, hcaDetails.get(Values.TXT_NAMEHCA), mediumWait());
        }

        if (!hcaDetails.get(Values.TXT_EMAIL).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchEmail, hcaDetails.get(Values.TXT_EMAIL), mediumWait());
        }

        if (!hcaDetails.get(Values.TXT_PHONEORFAX).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchPhoneOrFax, hcaDetails.get(Values.TXT_PHONEORFAX), mediumWait());
        }

        if (!hcaDetails.get(Values.TXT_ADDRESSLINE1).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchAddressLine1, hcaDetails.get(Values.TXT_ADDRESSLINE1), mediumWait());
        }

        if (!hcaDetails.get(Values.TXT_STATECODE).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            selectAndMoveDropdownByOptionAttributeValue(inputSearchState, hcaDetails.get(Values.TXT_STATECODE), mediumWait());
        }

        if (!hcaDetails.get(Values.TXT_CITY).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchCity, hcaDetails.get(Values.TXT_CITY), mediumWait());
        }

        if (!hcaDetails.get(Values.TXT_ZIPCODE).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchZipCode, hcaDetails.get(Values.TXT_ZIPCODE), mediumWait());
        }

        if (!hcaDetails.get(Values.TXT_COUNTRY).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            selectAndMoveDropdownByOptionAttributeValue(inputSearchCountry, hcaDetails.get(Values.TXT_COUNTRY), mediumWait());
        }
    }

    public void clickOnSearch() throws Exception {
        clickAndMoveToElementClickable(buttonSearch, mediumWait());
    }

    /**
     * Used to validate if there were results or not according to the search performed
     *
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void validateSearchResults() throws Exception {
        if (waitForElementVisibility(messageSearchNoResults, mediumWait())) {
            logger.info("No Results Were Found");
        } else {
            waitForElementVisibility(tableResultsTableHeaders, longWait());
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
     * @param searchAllFromFile value used to know if the data for search will be obtained from account json file or from table. If it is Values.TXT_Y_VALUE it will gather all the data from HCPRecord.json file
     * @return HashMap<String, String> with all the Data
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public HashMap<String, String> createHCPDataUseToSearch(String externalID, String npi, String firstName, String middleName, String lastName, String dateOfBirth, String email,
                                                            String phoneOrFax, String addressLine1, String stateCode, String city, String zipCode, String country,
                                                            String searchAllFromFile) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(Values.KEYVALUE_HCPRECORD);
        HashMap<String, String> hcpDetails = new HashMap<>();
        if (searchAllFromFile.trim().equalsIgnoreCase(Values.TXT_Y_VALUE)) {
            hcpDetails.put(Values.TXT_EXTERNALID, jsonFile.getField(Values.TXT_EXTERNALID));
            hcpDetails.put(Values.TXT_NPI, jsonFile.getField(Values.TXT_NPI));
            hcpDetails.put(Values.TXT_FIRSTNAME, jsonFile.getField(Values.TXT_FIRSTNAME));
            hcpDetails.put(Values.TXT_MIDDLENAME, jsonFile.getField(Values.TXT_MIDDLENAME));
            hcpDetails.put(Values.TXT_LASTNAME, jsonFile.getField(Values.TXT_LASTNAME));
            hcpDetails.put(Values.TXT_DATEOFBIRTH, jsonFile.getField(Values.TXT_DATEOFBIRTH));
            hcpDetails.put(Values.TXT_EMAIL, jsonFile.getField(Values.TXT_EMAIL));
            hcpDetails.put(Values.TXT_PHONEORFAX, jsonFile.getField(Values.TXT_PHONEORFAX));
            hcpDetails.put(Values.TXT_ADDRESSLINE1, jsonFile.getField(Values.TXT_ADDRESSLINE1));
            hcpDetails.put(Values.TXT_STATECODE, jsonFile.getField(Values.TXT_STATECODE));
            hcpDetails.put(Values.TXT_CITY, jsonFile.getField(Values.TXT_CITY));
            hcpDetails.put(Values.TXT_ZIPCODE, jsonFile.getField(Values.TXT_ZIPCODE));
            hcpDetails.put(Values.TXT_COUNTRY, jsonFile.getField(Values.TXT_COUNTRY));
        } else {
            hcpDetails.put(Values.TXT_EXTERNALID, selectHCPDataForSearch(externalID, Values.TXT_EXTERNALID));
            hcpDetails.put(Values.TXT_NPI, selectHCPDataForSearch(npi, Values.TXT_NPI));
            hcpDetails.put(Values.TXT_FIRSTNAME, selectHCPDataForSearch(firstName, Values.TXT_FIRSTNAME));
            hcpDetails.put(Values.TXT_MIDDLENAME, selectHCPDataForSearch(middleName, Values.TXT_MIDDLENAME));
            hcpDetails.put(Values.TXT_LASTNAME, selectHCPDataForSearch(lastName, Values.TXT_LASTNAME));
            hcpDetails.put(Values.TXT_DATEOFBIRTH, selectHCPDataForSearch(dateOfBirth, Values.TXT_DATEOFBIRTH));
            hcpDetails.put(Values.TXT_EMAIL, selectHCPDataForSearch(email, Values.TXT_EMAIL));
            hcpDetails.put(Values.TXT_PHONEORFAX, selectHCPDataForSearch(phoneOrFax, Values.TXT_PHONEORFAX));
            hcpDetails.put(Values.TXT_ADDRESSLINE1, selectHCPDataForSearch(addressLine1, Values.TXT_ADDRESSLINE1));
            hcpDetails.put(Values.TXT_STATECODE, selectHCPDataForSearch(stateCode, Values.TXT_STATECODE));
            hcpDetails.put(Values.TXT_CITY, selectHCPDataForSearch(city, Values.TXT_CITY));
            hcpDetails.put(Values.TXT_ZIPCODE, selectHCPDataForSearch(zipCode, Values.TXT_ZIPCODE));
            hcpDetails.put(Values.TXT_COUNTRY, selectHCPDataForSearch(country, Values.TXT_COUNTRY));
        }
        return hcpDetails;
    }

    /**
     * This method is to check and create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param hcpValue    it will contains the data for each field to be populated
     * @param nameOfField it is used to know which field will be evaluated
     * @return it will return the values used to create the HCP Search Operation
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public String selectHCPDataForSearch(String hcpValue, String nameOfField) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(Values.KEYVALUE_HCPRECORD);
        String returnedValue = "";

        switch (nameOfField) {
            case Values.TXT_EXTERNALID:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_EXTERNALID);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_NPI:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_NPI);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_FIRSTNAME:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_FIRSTNAME);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_MIDDLENAME:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_MIDDLENAME);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_LASTNAME:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_LASTNAME);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_DATEOFBIRTH:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_DATEOFBIRTH).replace(Values.TXT_SLASH, Values.REPLACETO_EMPTY);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_EMAIL:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_EMAIL);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_PHONEORFAX:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_PHONEORFAX);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_ADDRESSLINE1:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_ADDRESSLINE1);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_STATECODE:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_STATECODE);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_CITY:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_CITY);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_ZIPCODE:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_ZIPCODE);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case Values.TXT_COUNTRY:
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_COUNTRY);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            default:
                logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                break;
        }
        return returnedValue;
    }

    /**
     * Used to fill all the data on each field that will be used to create a HCP search
     *
     * @param hcpDetails it contains all the data related to HCP
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void doAHCPSearch(HashMap<String, String> hcpDetails) throws Exception {

        if (!hcpDetails.get(Values.TXT_EXTERNALID).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchExternalID, hcpDetails.get(Values.TXT_EXTERNALID), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_NPI).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchNPI, hcpDetails.get(Values.TXT_NPI), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_FIRSTNAME).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchFirstName, hcpDetails.get(Values.TXT_FIRSTNAME), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_MIDDLENAME).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchMiddleName, hcpDetails.get(Values.TXT_MIDDLENAME), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_LASTNAME).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchLastName, hcpDetails.get(Values.TXT_LASTNAME), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_DATEOFBIRTH).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchDOB, hcpDetails.get(Values.TXT_DATEOFBIRTH).replace(Values.TXT_SLASH, Values.REPLACETO_EMPTY), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_EMAIL).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchEmail, hcpDetails.get(Values.TXT_EMAIL), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_PHONEORFAX).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchPhoneOrFax, hcpDetails.get(Values.TXT_PHONEORFAX), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_ADDRESSLINE1).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchAddressLine1, hcpDetails.get(Values.TXT_ADDRESSLINE1), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_STATECODE).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            selectAndMoveDropdownByOptionAttributeValue(inputSearchState, hcpDetails.get(Values.TXT_STATECODE), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_CITY).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchCity, hcpDetails.get(Values.TXT_CITY), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_ZIPCODE).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchZipCode, hcpDetails.get(Values.TXT_ZIPCODE), mediumWait());
        }

        if (!hcpDetails.get(Values.TXT_COUNTRY).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            selectAndMoveDropdownByOptionAttributeValue(inputSearchCountry, hcpDetails.get(Values.TXT_COUNTRY), mediumWait());
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
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public HashMap<String, String> createCPCDataUseToSearch(String externalID, String firstName, String middleName, String lastName, String dateOfBirth, String careGiver,
                                                            String email, String phoneOrFax, String addressLine1, String stateCode, String city, String zipCode, String country,
                                                            String searchAllFromFile) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(Values.KEYVALUE_CPCRECORD);
        HashMap<String, String> cpcDetails = new HashMap<>();
        if (searchAllFromFile.trim().equalsIgnoreCase(Values.TXT_Y_VALUE)) {
            cpcDetails.put(Values.TXT_EXTERNALID, jsonFile.getField(Values.TXT_EXTERNALID));
            cpcDetails.put(Values.TXT_FIRSTNAME, jsonFile.getField(Values.TXT_FIRSTNAME));
            cpcDetails.put(Values.TXT_MIDDLENAME, jsonFile.getField(Values.TXT_MIDDLENAME));
            cpcDetails.put(Values.TXT_LASTNAME, jsonFile.getField(Values.TXT_LASTNAME));
            cpcDetails.put(Values.TXT_DATEOFBIRTH, jsonFile.getField(Values.TXT_DATEOFBIRTH));
            cpcDetails.put(Values.TXT_CAREGIVER, jsonFile.getField(Values.TXT_CAREGIVER));
            cpcDetails.put(Values.TXT_EMAIL, jsonFile.getField(Values.TXT_EMAIL));
            cpcDetails.put(Values.TXT_PHONEORFAX, jsonFile.getField(Values.TXT_PHONEORFAX));
            cpcDetails.put(Values.TXT_ADDRESSLINE1, jsonFile.getField(Values.TXT_ADDRESSLINE1));
            cpcDetails.put(Values.TXT_STATECODE, jsonFile.getField(Values.TXT_STATECODE));
            cpcDetails.put(Values.TXT_CITY, jsonFile.getField(Values.TXT_CITY));
            cpcDetails.put(Values.TXT_ZIPCODE, jsonFile.getField(Values.TXT_ZIPCODE));
            cpcDetails.put(Values.TXT_COUNTRY, jsonFile.getField(Values.TXT_COUNTRY));
        } else {
            cpcDetails.put(Values.TXT_EXTERNALID, selectCPCDataForSearch(externalID, Values.TXT_EXTERNALID));
            cpcDetails.put(Values.TXT_FIRSTNAME, selectCPCDataForSearch(firstName, Values.TXT_FIRSTNAME));
            cpcDetails.put(Values.TXT_MIDDLENAME, selectCPCDataForSearch(middleName, Values.TXT_MIDDLENAME));
            cpcDetails.put(Values.TXT_LASTNAME, selectCPCDataForSearch(lastName, Values.TXT_LASTNAME));
            cpcDetails.put(Values.TXT_DATEOFBIRTH, selectCPCDataForSearch(dateOfBirth, Values.TXT_DATEOFBIRTH));
            cpcDetails.put(Values.TXT_CAREGIVER, selectCPCDataForSearch(careGiver, Values.TXT_CAREGIVER));
            cpcDetails.put(Values.TXT_EMAIL, selectCPCDataForSearch(email, Values.TXT_EMAIL));
            cpcDetails.put(Values.TXT_PHONEORFAX, selectCPCDataForSearch(phoneOrFax, Values.TXT_PHONEORFAX));
            cpcDetails.put(Values.TXT_ADDRESSLINE1, selectCPCDataForSearch(addressLine1, Values.TXT_ADDRESSLINE1));
            cpcDetails.put(Values.TXT_STATECODE, selectCPCDataForSearch(stateCode, Values.TXT_STATECODE));
            cpcDetails.put(Values.TXT_CITY, selectCPCDataForSearch(city, Values.TXT_CITY));
            cpcDetails.put(Values.TXT_ZIPCODE, selectCPCDataForSearch(zipCode, Values.TXT_ZIPCODE));
            cpcDetails.put(Values.TXT_COUNTRY, selectCPCDataForSearch(country, Values.TXT_COUNTRY));
        }
        return cpcDetails;
    }

    /**
     * This method is to check and create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param cpcValue    it will contains the data for each field to be populated
     * @param nameOfField it is used to know which field will be evaluated
     * @return it will return the values used to create the CPC Search Operation
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public String selectCPCDataForSearch(String cpcValue, String nameOfField) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(Values.KEYVALUE_CPCRECORD);
        String returnedValue = "";

        switch (nameOfField) {
            case Values.TXT_EXTERNALID:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_EXTERNALID);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_FIRSTNAME:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_FIRSTNAME);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_MIDDLENAME:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_MIDDLENAME);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_LASTNAME:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_LASTNAME);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_DATEOFBIRTH:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_DATEOFBIRTH);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_CAREGIVER:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_CAREGIVER);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_EMAIL:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_EMAIL);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_PHONEORFAX:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_PHONEORFAX);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_ADDRESSLINE1:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_ADDRESSLINE1);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_STATECODE:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_STATECODE);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_CITY:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_CITY);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_ZIPCODE:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_ZIPCODE);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case Values.TXT_COUNTRY:
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_COUNTRY);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            default:
                logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                break;
        }
        return returnedValue;
    }

    /**
     * Used to fill all the data on each field that will be used to create a CPC search
     *
     * @param cpcDetails it contains all the data related to CPC
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void doACPCSearch(HashMap<String, String> cpcDetails) throws Exception {

        if (!cpcDetails.get(Values.TXT_EXTERNALID).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchExternalID, cpcDetails.get(Values.TXT_EXTERNALID), mediumWait());
        }

        if (!cpcDetails.get(Values.TXT_FIRSTNAME).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchFirstName, cpcDetails.get(Values.TXT_FIRSTNAME), mediumWait());
        }

        if (!cpcDetails.get(Values.TXT_MIDDLENAME).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchMiddleName, cpcDetails.get(Values.TXT_MIDDLENAME), mediumWait());
        }

        if (!cpcDetails.get(Values.TXT_LASTNAME).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchLastName, cpcDetails.get(Values.TXT_LASTNAME), mediumWait());
        }

        if (!cpcDetails.get(Values.TXT_DATEOFBIRTH).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchDOB, cpcDetails.get(Values.TXT_DATEOFBIRTH).replace(Values.TXT_SLASH, Values.REPLACETO_EMPTY), mediumWait());
        }
        if (!cpcDetails.get(Values.TXT_EMAIL).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchEmail, cpcDetails.get(Values.TXT_EMAIL), mediumWait());
        }

        if (!cpcDetails.get(Values.TXT_PHONEORFAX).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchPhoneOrFax, cpcDetails.get(Values.TXT_PHONEORFAX), mediumWait());
        }

        if (!cpcDetails.get(Values.TXT_ADDRESSLINE1).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchAddressLine1, cpcDetails.get(Values.TXT_ADDRESSLINE1), mediumWait());
        }

        if (!cpcDetails.get(Values.TXT_STATECODE).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            selectAndMoveDropdownByOptionAttributeValue(inputSearchState, cpcDetails.get(Values.TXT_STATECODE), mediumWait());
        }

        if (!cpcDetails.get(Values.TXT_CITY).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchCity, cpcDetails.get(Values.TXT_CITY), mediumWait());
        }

        if (!cpcDetails.get(Values.TXT_ZIPCODE).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchZipCode, cpcDetails.get(Values.TXT_ZIPCODE), mediumWait());
        }

        if (!cpcDetails.get(Values.TXT_COUNTRY).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            selectAndMoveDropdownByOptionAttributeValue(inputSearchCountry, cpcDetails.get(Values.TXT_COUNTRY), mediumWait());
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
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public HashMap<String, String> createEmployeeDataUseToSearch(String externalID, String firstName, String
            middleName, String lastName, String searchAllFromFile) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(Values.KEYVALUE_EMPLOYEERECORD);
        HashMap<String, String> employeeDetails = new HashMap<>();
        if (searchAllFromFile.trim().equalsIgnoreCase(Values.TXT_Y_VALUE)) {
            employeeDetails.put(Values.TXT_EXTERNALID, jsonFile.getField(Values.TXT_EXTERNALID));
            employeeDetails.put(Values.TXT_FIRSTNAME, jsonFile.getField(Values.TXT_FIRSTNAME));
            employeeDetails.put(Values.TXT_MIDDLENAME, jsonFile.getField(Values.TXT_MIDDLENAME));
            employeeDetails.put(Values.TXT_LASTNAME, jsonFile.getField(Values.TXT_LASTNAME));
        } else {
            employeeDetails.put(Values.TXT_EXTERNALID, selectEmployeeDataForSearch(externalID, Values.TXT_EXTERNALID));
            employeeDetails.put(Values.TXT_FIRSTNAME, selectEmployeeDataForSearch(firstName, Values.TXT_FIRSTNAME));
            employeeDetails.put(Values.TXT_MIDDLENAME, selectEmployeeDataForSearch(middleName, Values.TXT_MIDDLENAME));
            employeeDetails.put(Values.TXT_LASTNAME, selectEmployeeDataForSearch(lastName, Values.TXT_LASTNAME));
        }
        return employeeDetails;
    }

    /**
     * This method is to check and create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param employeeValue it will contains the data for each field to be populated
     * @param nameOfField   it is used to know which field will be evaluated
     * @return it will return the values used to create the Employee Search Operation
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public String selectEmployeeDataForSearch(String employeeValue, String nameOfField) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(Values.KEYVALUE_EMPLOYEERECORD);
        String returnedValue = "";

        switch (nameOfField) {
            case Values.TXT_EXTERNALID:
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_EXTERNALID);
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            case Values.TXT_FIRSTNAME:
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_FIRSTNAME);
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            case Values.TXT_MIDDLENAME:
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_MIDDLENAME);
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            case Values.TXT_LASTNAME:
                if (!employeeValue.trim().isEmpty() && employeeValue.trim().equalsIgnoreCase(Values.TXT_SEARCHFROMFILE)) {
                    returnedValue = jsonFile.getField(Values.TXT_LASTNAME);
                } else {
                    if (employeeValue.trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
                        returnedValue = employeeValue;
                    } else {
                        returnedValue = employeeValue;
                    }
                }
                break;

            default:
                logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                break;
        }
        return returnedValue;
    }

    /**
     * Used to fill all the data on each field that will be used to create a Employee search
     *
     * @param employeeDetails it contains all the data related to Employee
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public void doAEmployeeSearch(HashMap<String, String> employeeDetails) throws Exception {

        if (!employeeDetails.get(Values.TXT_EXTERNALID).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchExternalID, employeeDetails.get(Values.TXT_EXTERNALID), mediumWait());
        }

        if (!employeeDetails.get(Values.TXT_FIRSTNAME).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchFirstName, employeeDetails.get(Values.TXT_FIRSTNAME), mediumWait());
        }

        if (!employeeDetails.get(Values.TXT_MIDDLENAME).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchMiddleName, employeeDetails.get(Values.TXT_MIDDLENAME), mediumWait());
        }

        if (!employeeDetails.get(Values.TXT_LASTNAME).trim().equalsIgnoreCase(Values.TXT_NOTAPPLY)) {
            sendKeysAndMoveToElementVisible(inputSearchLastName, employeeDetails.get(Values.TXT_LASTNAME), mediumWait());
        }
    }

    /**
     * method to filter which option is being selected for relationship when creating a CTM
     *
     * @param ctmRelationship it contains the code for each ralationship CTM available
     * @return the string option for CTM relationship
     * @throws Exception selenium exception related
     * @author J.Ruano
     */
    public String ctmRelationShipFilter(String ctmRelationship) throws Exception {
        switch (ctmRelationship.trim().toUpperCase()) {
            case "PP":
                ctmRelationship = Values.CTM_PRESCRIBINGPHYSICIAN;
                break;
            case "PF":
                ctmRelationship = Values.CTM_PRESCRIBINGFACILITY;
                break;
            case "TP":
                ctmRelationship = Values.CTM_TREATINGPHYSICIAN;
                break;
            case "TF":
                ctmRelationship = Values.CTM_TREATINGFACILITY;
                break;
            case "PH":
                ctmRelationship = Values.CTM_HCAPHARMACIST;
                break;
            case "PM":
                ctmRelationship = Values.CTM_PHARMACIST;
                break;
            case "OS":
                ctmRelationship = Values.CTM_OFFICESTAFF;
                break;
            case "OT":
                ctmRelationship = Values.CTM_OTHER;
                break;
            case "OF":
                ctmRelationship = Values.CTM_HCAOTHERFACILITY;
                break;

            default:
                logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                break;
        }
        return ctmRelationship;
    }
}