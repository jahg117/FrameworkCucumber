package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import io.cucumber.core.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.util.HashMap;
import java.util.List;

public class NewCPCWizardPage extends CommonFunctions {
    private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "//select[@data-name='subtype']")
    private WebElement dropdown_subType;

    @FindBy(xpath = "//select[@data-name='subtype']//option")
    private List<WebElement> dropdown_subTypeList;

    @FindBy(xpath = "//input[contains(@data-name,'email')]")
    private WebElement input_email;

    @FindBy(xpath = "//input[@data-name='number'][1]")
    private WebElement input_phoneOrFax;

    @FindBy(xpath = "//select[contains(@data-name,'State')]")
    private WebElement dropdown_state;

    @FindBy(xpath = "//select[contains(@data-name,'Country')]")
    private WebElement dropdown_country;

    @FindBy(xpath = "//input[contains(@data-name,'Zip')]")
    private WebElement input_zipCode;

    @FindBy(xpath = "//select[contains(@data-name,'State')]/option")
    private List<WebElement> elementList_stateCodesList;

    @FindBy(xpath = "//select[contains(@data-name,'Country')]/option")
    private List<WebElement> elementList_countriesList;

    @FindBy(xpath = "//a[@data-label='System Info']")
    private WebElement label_systemInfo;

    @FindBy(xpath = "//a[@data-label='Account History']")
    private WebElement label_accountHistory;

    @FindBy(xpath = "(//*[contains(text(),'Account ID')]//..)[1]")
    private WebElement label_externalID;

    @FindBy(xpath = "//span[normalize-space(text())='Logged out']")
    private WebElement button_loggedOut;

    @FindBy(xpath = "//*[contains(text(),\"Last Modified By\")]/following::a[1]")
    private WebElement linkButton_lastModifiedBy;

    @FindBy(xpath = "//input[@data-name='first']")
    private WebElement input_firstName;

    @FindBy(xpath = "//input[@data-name='last']")
    private WebElement input_lastName;

    @FindBy(xpath = "//input[@data-name='middle']")
    private WebElement input_middleName;

    @FindBy(xpath = "//input[@data-name='dob']")
    private WebElement datePicker_DOB;

    @FindBy(xpath = "//input[contains(@placeholder,'Search Accounts')]")
    private WebElement input_searchAccounts;

    @FindBy(xpath = "//*[contains(@id,'input')][@role='option']//*[@title]")
    private List<WebElement> dropdown_searchAccountsList;

    @FindBy(xpath = "//*[contains(@name,'Relationship')]")
    private WebElement dropdown_careGiverRelationship;

    @FindBy(xpath = "//*[contains(@data-api,'Relationship')]//span[@title]")
    private List<WebElement> dropdown_careGiverRelationshipList;

    @FindBy(xpath = "//input[@data-name='email']")
    private WebElement input_emailAddress;

    @FindBy(xpath = "//input[@data-name='street1']")
    private WebElement input_addressLine1;

    @FindBy(xpath = "//input[@data-name='city']")
    private WebElement input_city;

    @FindBy(xpath = "//footer[@class='slds-modal__footer']//button[@type='submit']")
    private WebElement button_saveAccount;

    @FindBy(xpath = "//input[@placeholder='Search Places']")
    private WebElement input_googleAddress;


    /**
     * This method is use only to ensure that the element has been displayed correctly, more use to know if a page is ready
     *
     * @return it returns a boolean value in case the form has been displayed
     * @author J.Ruano
     */
    public boolean isNewCPCWizardFormDisplayed() throws Exception {
        return waitForElementVisibility(dropdown_subType, longWait());
    }

    /**
     * It validates if the new Account will be created completely random or specific data at columns in ConfigurableCustomerLookup.feature tables, can contains
     * specific data or "RND" if that columns requires random data, or "N_A" if it does not need to be filled
     *
     * @param identifier   it is used to add a label at the begining of the first name, use to identify the records created by Automation, it can have any value
     * @param firstName    it contains the first name for the new CPC account (required)
     * @param middleName   it contains the middle name for the new CPC account (optional)
     * @param lastName     it contains the last name for the new CPC account (required)
     * @param dateOfBirth   it contains the DOB for the new CPC account (required)
     * @param careGiver    this is used in case there is a caregiver into the new CPC account so it can be selected (optional)
     * @param email        it contains the email for the new CPC account (optional)
     * @param phoneOrFax   it contains the phone or fax for the new CPC account (required)
     * @param addressLine1 it contains the address for the new CPC account (optional)
     * @param state        it contains the state for the new CPC account (optional)
     * @param city         it contains the city for the new CPC account (optional)
     * @param zipCode      it contains the zipcode for the new CPC account (optional)
     * @param country      it contains the country for the new CPC account (optional)
     * @param randomRecord value used to know if the account will be fill with random data "RND" when it is random
     * @throws Exception
     * @author J.Ruano
     */
    public void validateAndCreateCPC(String identifier, String firstName, String middleName, String lastName, String dateOfBirth, String careGiver, String email, String phoneOrFax, String addressLine1, String state, String city, String zipCode, String country, String randomRecord) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("CPCRecord");
        HashMap<String, String> cpcDetailsStoreData = new HashMap<String, String>();

        boolean allDataRND = false;
        if (randomRecord.trim().equalsIgnoreCase("RND")) {
            cpcDetailsStoreData = fullCPCFormRND(identifier, careGiver);
        } else {
            cpcDetailsStoreData = hibrydCPCForm(identifier, firstName, middleName, lastName, dateOfBirth, careGiver, email, phoneOrFax, addressLine1, state, city, zipCode, country);
            fillingHybridCPCForm(cpcDetailsStoreData);
        }
        scrollMethodToWebElement(button_saveAccount);
        clickSaveButton();
        cpcDetailsStoreData.put("externalID", getExternalID());
        jsonFile.storeDataIntoJSON(cpcDetailsStoreData);
    }

    /**
     * This method is to create a new CPC aacount completely with random data
     *
     * @param identifier it is used to add a label at the begining of the first name, use to identify the records created by Automation, it can have any value
     * @param careGiver  this is used in case there is a caregiver into the new CPC account so it can be selected
     * @return it returns a HashMap with string data used to fill the CPC form
     * @throws Exception
     * @author J.Ruano
     */
    public HashMap<String, String> fullCPCFormRND(String identifier, String careGiver) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("statesUSCodes");
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        Faker faker = new Faker();

        //============Storing Data Into cpcDetails
        HashMap<String, String> cpcDetails = new HashMap<String, String>();
        cpcDetails.put("firstName", identifier + faker.name().firstName());
        cpcDetails.put("middleName", faker.name().firstName());
        cpcDetails.put("lastName", faker.name().lastName() + underScore + generateTimeStamp(dateFormat));
        cpcDetails.put("careGiver", "");
        cpcDetails.put("dateOfBirth", getRandomDate().replace("/", ""));
        cpcDetails.put("email", cpcDetails.get("lastName") + "@sharklasers.com");
        cpcDetails.put("phoneOrFax", faker.phoneNumber().cellPhone().replace(".", "").replace("-", ""));
        cpcDetails.put("addressLine1", faker.address().streetName());
        cpcDetails.put("city", faker.address().cityName());
        cpcDetails.put("zipCode", String.valueOf(faker.number().randomNumber(5, true)));

        //============Populating The CPC Data
        selectAndMoveDropDownVisibleRandomOption(dropdown_subType, mediumWait());
        clickAndMoveToElementVisible(input_firstName, mediumWait());
        input_firstName.clear();
        sendKeysAndMoveToElementVisible(input_firstName, cpcDetails.get("firstName"), mediumWait());
        clickAndMoveToElementVisible(input_middleName, mediumWait());
        input_middleName.clear();
        sendKeysAndMoveToElementVisible(input_middleName, cpcDetails.get("middleName"), mediumWait());
        clickAndMoveToElementVisible(input_lastName, mediumWait());
        input_lastName.clear();
        sendKeysAndMoveToElementVisible(input_lastName, cpcDetails.get("lastName"), mediumWait());
        try {
            if (input_searchAccounts.getAttribute("disabled").equalsIgnoreCase(null)) {
                logger.info("CareGiver Not Need It");
            }
        } catch (Exception e) {
            clickAndMoveToElementVisible(input_searchAccounts, mediumWait());
            input_searchAccounts.clear();
            cpcDetails.put("careGiver", selectCareGiver(careGiver));
        }
        clickAndMoveToElementVisible(datePicker_DOB, mediumWait());
        datePicker_DOB.clear();
        sendKeysElementVisible(datePicker_DOB, cpcDetails.get("dateOfBirth"), mediumWait());
        clickAndMoveToElementVisible(input_phoneOrFax, mediumWait());
        input_phoneOrFax.clear();
        sendKeysAndMoveToElementVisible(input_phoneOrFax, cpcDetails.get("phoneOrFax"), mediumWait());
        clickAndMoveToElementVisible(input_addressLine1, mediumWait());
        input_addressLine1.clear();
        sendKeysAndMoveToElementVisible(input_addressLine1, cpcDetails.get("addressLine1"), mediumWait());
        clickAndMoveToElementVisible(input_city, mediumWait());
        input_city.clear();
        sendKeysAndMoveToElementVisible(input_city, cpcDetails.get("city"), 10);
        scrollMethodToWebElement(dropdown_state);
        cpcDetails.put("stateCode", getRandomWebElementFromList(elementList_stateCodesList, mediumWait()).getAttribute("value"));
        selectAndMoveDropdownClickableByText(dropdown_state, cpcDetails.get("stateCode"), mediumWait());
        clickAndMoveToElementVisible(input_zipCode, mediumWait());
        input_zipCode.clear();
        sendKeysAndMoveToElementVisible(input_zipCode, cpcDetails.get("zipCode"), mediumWait());
        scrollMethodToWebElement(dropdown_country);
        cpcDetails.put("country", (getRandomWebElementFromList(elementList_countriesList, mediumWait())).getAttribute("value"));
        selectAndMoveDropdownByText(dropdown_country, cpcDetails.get("country"), mediumWait());
        clickAndMoveToElementVisible(input_email, mediumWait());
        input_email.clear();
        sendKeysAndMoveToElementVisible(input_email, cpcDetails.get("email"), mediumWait());
        return cpcDetails;
    }

    /**
     * This method is used to prepare the data for the CPC account creation, it handle if the data will be generated randomly or it will contains an specific
     * data from the ConfigurableCustomerLookup feature
     *
     * @param identifier   it is used to add a label at the begining of the first name, use to identify the records created by Automation, it can have any value
     * @param firstName    it contains the first name for the new CPC account (required)
     * @param middleName   it contains the middle name for the new CPC account (optional)
     * @param lastName     it contains the last name for the new CPC account (required)
     * @param dateOfBirth   it contains the DOB for the new CPC account (required)
     * @param careGiver    this is used in case there is a caregiver into the new CPC account so it can be selected (optional)
     * @param email        it contains the email for the new CPC account (optional)
     * @param phoneOrFax   it contains the phone or fax for the new CPC account (required)
     * @param addressLine1 it contains the address for the new CPC account (optional)
     * @param state        it contains the state for the new CPC account (optional)
     * @param city         it contains the city for the new CPC account (optional)
     * @param zipCode      it contains the zipcode for the new CPC account (optional)
     * @param country      it contains the country for the new CPC account (optional)
     * @return it returns a HashMap with string data used to fill the CPC form
     * @throws Exception
     * @author J.Ruano
     */
    private HashMap<String, String> hibrydCPCForm(String identifier, String firstName, String middleName, String lastName, String dateOfBirth, String careGiver, String email, String phoneOrFax, String addressLine1, String state, String city, String zipCode, String country) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("statesUSCodes");
        Faker faker = new Faker();
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        //============Storing Data Into cpcDetails
        HashMap<String, String> cpcDetails = new HashMap<String, String>();
        cpcDetails.put("firstName", identifier + hibrydCPCFormFilter(firstName, "firstName"));
        cpcDetails.put("middleName", hibrydCPCFormFilter(middleName, "middleName"));
        cpcDetails.put("lastName", hibrydCPCFormFilter(lastName, "lastName") + underScore + generateTimeStamp(dateFormat));
        cpcDetails.put("careGiver", "");
        cpcDetails.put("dateOfBirth", hibrydCPCFormFilter(dateOfBirth, "dateOfBirth").replace("/", ""));
        cpcDetails.put("email", hibrydCPCFormFilter(email, "email"));
        cpcDetails.put("phoneOrFax", hibrydCPCFormFilter(phoneOrFax, "phoneOrFax"));
        cpcDetails.put("addressLine1", hibrydCPCFormFilter(addressLine1, "addressLine1"));
        cpcDetails.put("stateCode", hibrydCPCFormFilter(state, "stateCode"));
        cpcDetails.put("city", hibrydCPCFormFilter(city, "city"));
        cpcDetails.put("zipCode", hibrydCPCFormFilter(zipCode, "zipCode"));
        cpcDetails.put("country", hibrydCPCFormFilter(country, "country"));
        return cpcDetails;
    }

    /**
     * This method is to check anc create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param cpcValue    it will contains the data for each field to be populated
     * @param nameOfField it is used to know which field will be evaluated
     * @return it will return the valued used to fill the CPC form
     * @throws Exception
     * @author J.Ruano
     */
    public String hibrydCPCFormFilter(String cpcValue, String nameOfField) throws Exception {
        WebElement backUpWElement = null;
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        String notApply = "N_A";
        String randomOption = "RND";
        String returnedValue = "";
        Faker faker = new Faker();
        switch (nameOfField) {
            case "firstName":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.name().firstName();
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "middleName":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.funnyName().name();
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "lastName":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.name().lastName() + underScore + generateTimeStamp(dateFormat);
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "dateOfBirth":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = getRandomDate();
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "email":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.name().lastName() + underScore + generateTimeStamp(dateFormat) + "@sharklasers.com";
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "phoneOrFax":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.phoneNumber().cellPhone().replace(".", "").replace("-", "");
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "addressLine1":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.address().streetName();
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "stateCode":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().numberBetween(0, elementList_stateCodesList.size() - 1));
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "city":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.address().cityName();
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "zipCode":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().randomNumber(5, true));
                } else {
                    if (cpcValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = cpcValue;
                    } else {
                        returnedValue = cpcValue;
                    }
                }
                break;

            case "country":
                if (!cpcValue.trim().isEmpty() && cpcValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().numberBetween(0, elementList_countriesList.size() - 1));
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
     * It is used to fulfill the CPC New Account form, validating which elements are going to be fill
     *
     * @param cpcDetails it contains the list of all the fields with their respective data
     * @throws Exception
     * @author J.Ruano
     */
    public void fillingHybridCPCForm(HashMap<String, String> cpcDetails) throws Exception {
        String notApply = "N_A";
        Faker faker = new Faker();
        selectAndMoveDropDownVisibleRandomOption(dropdown_subType, mediumWait());
        clickAndMoveToElementVisible(input_firstName, mediumWait());
        input_firstName.clear();
        sendKeysAndMoveToElementVisible(input_firstName, cpcDetails.get("firstName"), mediumWait());

        if (!cpcDetails.get("middleName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_middleName, cpcDetails.get("middleName"), mediumWait());
        }
        clickAndMoveToElementVisible(input_lastName, mediumWait());
        input_lastName.clear();
        sendKeysAndMoveToElementVisible(input_lastName, cpcDetails.get("lastName"), mediumWait());

        if (!cpcDetails.get("dateOfBirth").trim().equalsIgnoreCase(notApply)) {
            clickAndMoveToElementVisible(datePicker_DOB, mediumWait());
            datePicker_DOB.clear();
            sendKeysAndMoveToElementVisible(datePicker_DOB, cpcDetails.get("dateOfBirth"), mediumWait());
        }
        clickAndMoveToElementVisible(input_phoneOrFax, mediumWait());
        input_phoneOrFax.clear();
        sendKeysAndMoveToElementVisible(input_phoneOrFax, cpcDetails.get("phoneOrFax"), mediumWait());

        if (!cpcDetails.get("addressLine1").trim().equalsIgnoreCase(notApply)) {
            clickAndMoveToElementVisible(input_addressLine1, mediumWait());
            input_addressLine1.clear();
            sendKeysAndMoveToElementVisible(input_addressLine1, cpcDetails.get("addressLine1"), mediumWait());
        }

        if (!cpcDetails.get("city").trim().equalsIgnoreCase(notApply)) {
            clickAndMoveToElementVisible(input_city, mediumWait());
            input_city.clear();
            sendKeysAndMoveToElementVisible(input_city, cpcDetails.get("city"), mediumWait());
        }

        if (!cpcDetails.get("stateCode").trim().equalsIgnoreCase(notApply)) {
            scrollMethodToWebElement(dropdown_state);
            selectAndMoveDropdownByText(dropdown_state, cpcDetails.get("stateCode"), mediumWait());
        }

        if (!cpcDetails.get("zipCode").trim().equalsIgnoreCase(notApply)) {
            clickAndMoveToElementVisible(input_zipCode, mediumWait());
            input_zipCode.clear();
            sendKeysAndMoveToElementVisible(input_zipCode, cpcDetails.get("zipCode"), mediumWait());
        }

        if (!cpcDetails.get("country").trim().equalsIgnoreCase(notApply)) {

            scrollMethodToWebElement(dropdown_country);
            selectAndMoveDropdownByText(dropdown_country, cpcDetails.get("country"), mediumWait());
        }
    }

    /**
     * It clicks the save button after all the data required for the CPC account is populated
     *
     * @throws Exception
     */
    public void clickSaveButton() throws Exception {
        clickAndMoveToElementClickable(button_saveAccount, mediumWait());
    }

    /**
     * It retrieves the Account ID generated for the new CPC account created
     *
     * @return the Account ID added to the new CPC account
     * @throws Exception
     * @author J.Ruano
     */
    public String getExternalID() throws Exception {
        waitForElementVisibility(label_systemInfo, longWait());
        clickMethod(label_systemInfo);
        if (waitForElementClickable(linkButton_lastModifiedBy, mediumWait())) {
            scrollMethodToWebElement(linkButton_lastModifiedBy);
        } else {
            waitForElementVisibility(linkButton_lastModifiedBy, mediumWait());
            scrollMethodToWebElement(linkButton_lastModifiedBy);
        }
        clickMethod(label_accountHistory);
        clickAndMoveToElementClickable(label_systemInfo,shortWait());
        By azID = By.xpath("(//*[contains(text(),'Account ID')]//..)[1]");
        waitForElementPresenceBy(azID,shortWait());
        scrollToElementByCoordinates(label_externalID);
        return label_externalID.getText().replace("Account ID", "").trim();
    }

    /**
     * It will select randomly the careGiver in case a CareGiver type of account is selected
     *
     * @param careGiver it contains value used to know if the value will be selected randomly
     * @return it will return the name of the caregiver selected
     * @throws Exception
     * @author J.Ruano
     */
    public String selectCareGiver(String careGiver) throws Exception {
        WebElement selectedOption = null;
        if (careGiver.equalsIgnoreCase("RND")) {
            clickAndMoveToElementClickable(selectedOption = getRandomWebElementFromList(dropdown_searchAccountsList, mediumWait()), mediumWait());
            selectedOption.getAttribute("title");
        } else {
            if (!careGiver.isEmpty()) {
                By elementLocator = By.xpath("//*[contains(@id,'input')][@role='option']//*[@title])");
                clickAndMoveToElementClickableFromListByAttribute(getWebElementList(elementLocator), "title", careGiver);
                selectedOption = clickAndMoveToElementClickableFromListByAttribute(getWebElementList(elementLocator), "title", careGiver);
            }
        }
        clickWhileCondition(dropdown_careGiverRelationship, "aria-expanded", "false", mediumWait());
        clickAndMoveToElementClickable(getRandomWebElementIgnoreText(dropdown_careGiverRelationshipList, "--None--"), mediumWait());
        return selectedOption.getAttribute("title");
    }
}