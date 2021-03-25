package pageObject.pages.accessServices.wizard;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.util.HashMap;
import java.util.List;

public class NewHCPWizardPage extends CommonFunctions {

    @FindBy(xpath = "//input[@name='Type']")
    private WebElement dropdown_type;

    @FindBy(xpath = "//*[contains(@id,'input')][@role='option'][@data-value]")
    private List<WebElement> dropdown_TypeList;

    @FindBy(xpath = "//input[contains(@name,'SubType')]")
    private WebElement dropdown_subType;

    @FindBy(xpath = "//*[contains(@data-api,'SubType')]//*[@data-value]")
    private List<WebElement> dropdown_subTypeList;

    @FindBy(xpath = "//input[contains(@name,'Email_Address')]")
    private WebElement input_email;

    @FindBy(xpath = "//input[contains(@name,'Phone')][1]")
    private WebElement input_phoneOrFax;

    @FindBy(xpath = "//input[@placeholder='Search Places']/following::input[@name='Name']")
    private WebElement input_addressLine1;

    @FindBy(xpath = "//input[contains(@name,'State')][1]")
    private WebElement dropdown_state;

    @FindBy(xpath = "//input[contains(@name,'City')]")
    private WebElement input_city;

    @FindBy(xpath = "//input[contains(@name,'Country')]")
    private WebElement dropdown_country;

    @FindBy(xpath = "//input[contains(@name,'Postal_Code')]")
    private WebElement input_zipCode;

    @FindBy(xpath = "//button[@title='Save']")
    private WebElement button_saveAccount;

    @FindBy(xpath = "//*[contains(@name,'_State_')]/following::*[@role='option']/following::*[string-length(@data-value)=2]")
    private List<WebElement> elementList_stateCodesList;

    @FindBy(xpath = "//*[contains(@name,'_Country_')]/following::*[@role='option']/following::*[@data-value]")
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

    @FindBy(xpath = "//*[contains(@name,'FirstName')]")
    private WebElement input_firstName;

    @FindBy(xpath = "//*[contains(@name,'MiddleName')]")
    private WebElement input_middleName;

    @FindBy(xpath = "//*[contains(@name,'LastName')]")
    private WebElement input_lastName;

    @FindBy(xpath = "//*[contains(@name,'License_Expiration_Date')]")
    private WebElement datePicker_DOB;

    @FindBy(xpath = "//input[contains(@name,'National_Provider_Identifier')]")
    private WebElement input_npi;

    @FindBy(xpath = "//input[@placeholder='Search Places']")
    private WebElement input_googleAddress;

    /**
     * This method is use only to ensure that the element has been displayed correctly, more use to know if a page is ready
     *
     * @return it returns a boolean value in case the form has been displayed
     * @author J.Ruano
     */
    public boolean isNewHCPWizardFormDisplayed() throws Exception {
        return waitForElementVisibility(dropdown_type, longWait());
    }

    /**
     * It validates if the new Account will be created completely random or specific data at columns in ConfigurableCustomerLookup.feature tables, can contains
     * specific data or "RND" if that columns requires random data, or "N_A" if it does not need to be filled
     *
     * @param identifier   it is used to add a label at the begining of the first name, use to identify the records created by Automation, it can have any value
     * @param npi          it contains the National Provider Identifier
     * @param firstName    it contains the first name for the new HCP account (required)
     * @param middleName   it contains the middle name for the new HCP account (optional)
     * @param lastName     it contains the last name for the new HCP account (required)
     * @param dateOfBirth   it contains the DOB for the new HCP account (required)
     * @param email        it contains the email for the new HCP account (optional)
     * @param phoneOrFax   it contains the phone or fax for the new HCP account (required)
     * @param addressLine1 it contains the address for the new HCP account (optional)
     * @param state        it contains the state for the new HCP account (optional)
     * @param city         it contains the city for the new HCP account (optional)
     * @param zipCode      it contains the zipcode for the new HCP account (optional)
     * @param country      it contains the country for the new HCP account (optional)
     * @param randomRecord value used to know if the account will be fill with random data "RND" when it is random
     * @throws Exception
     * @author J.Ruano
     */
    public void validateAndCreateHCP(String identifier, String npi, String firstName, String middleName, String lastName, String dateOfBirth, String email, String phoneOrFax, String addressLine1, String state, String city, String zipCode, String country, String randomRecord) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("HCPRecord");
        HashMap<String, String> hcpDetailsStoreData = new HashMap<String, String>();

        boolean allDataRND = false;
        if (randomRecord.trim().equalsIgnoreCase("RND")) {
            hcpDetailsStoreData = fullHCPFormRND(identifier);
        } else {
            hcpDetailsStoreData = hibrydHCPForm(identifier, npi, firstName, middleName, lastName, dateOfBirth, email, phoneOrFax, addressLine1, state, city, zipCode, country);
            fillingHybridHCPForm(hcpDetailsStoreData);
        }
        scrollMethodToWebElement(button_saveAccount);
        clickSaveButton();
        hcpDetailsStoreData.put("externalID", getExternalID());
        jsonFile.storeDataIntoJSON(hcpDetailsStoreData);
    }

    /**
     * This method is to create a new HCP account completely with random data
     *
     * @param identifier it is used to add a label at the beginning of the first name, use to identify the records created by Automation, it can have any value
     * @return it returns a HashMap with string data used to fill the HCP form
     * @throws Exception
     * @author J.Ruano
     */
    public HashMap<String, String> fullHCPFormRND(String identifier) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("statesUSCodes");
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        WebElement backUpWElement = null;
        Faker faker = new Faker();
        //============Storing Data Into hcpDetails
        HashMap<String, String> hcpDetails = new HashMap<String, String>();
        hcpDetails.put("npi", String.valueOf(faker.number().randomNumber(10, true)));
        hcpDetails.put("firstName", identifier + faker.name().firstName());
        hcpDetails.put("middleName", faker.name().firstName());
        hcpDetails.put("lastName", faker.name().lastName() + underScore + generateTimeStamp(dateFormat));
        hcpDetails.put("dateOfBirth", getRandomDate());
        hcpDetails.put("email", hcpDetails.get("lastName") + "@sharklasers.com");
        hcpDetails.put("phoneOrFax", faker.phoneNumber().cellPhone().replace(".", "").replace("-", ""));
        hcpDetails.put("addressLine1", faker.address().streetName());
        hcpDetails.put("city", faker.address().cityName());
        hcpDetails.put("zipCode", String.valueOf(faker.number().randomNumber(5, true)));
        //============Populating The HCP Data
        clickWhileCondition(dropdown_type, "aria-expanded", "false", mediumWait());
        clickAndMoveToElementClickable(getRandomWebElementIgnoreText(dropdown_TypeList, "--None--"), mediumWait());
        clickWhileCondition(dropdown_subType, "aria-expanded", "false", mediumWait());
        clickAndMoveToElementClickable(getRandomWebElementIgnoreIdexValue(dropdown_subTypeList, 0), mediumWait());
        clickAndMoveToElementVisible(input_firstName, mediumWait());
        input_firstName.clear();
        sendKeysAndMoveToElementVisible(input_firstName, hcpDetails.get("firstName"), mediumWait());
        clickAndMoveToElementVisible(input_middleName, mediumWait());
        input_middleName.clear();
        sendKeysAndMoveToElementVisible(input_middleName, hcpDetails.get("middleName"), mediumWait());
        clickAndMoveToElementVisible(input_lastName, mediumWait());
        input_lastName.clear();
        sendKeysAndMoveToElementVisible(input_lastName, hcpDetails.get("lastName"), mediumWait());
        clickAndMoveToElementVisible(input_npi, mediumWait());
        input_npi.clear();
        sendKeysAndMoveToElementVisible(input_npi, hcpDetails.get("npi"), mediumWait());
        clickAndMoveToElementVisible(datePicker_DOB, mediumWait());
        datePicker_DOB.clear();
        sendKeysAndMoveToElementVisible(datePicker_DOB, hcpDetails.get("dateOfBirth"), mediumWait());
        clickAndMoveToElementVisible(input_phoneOrFax, mediumWait());
        input_phoneOrFax.clear();
        sendKeysAndMoveToElementVisible(input_phoneOrFax, hcpDetails.get("phoneOrFax"), mediumWait());
        clickAndMoveToElementVisible(input_addressLine1, mediumWait());
        input_addressLine1.clear();
        sendKeysAndMoveToElementVisible(input_addressLine1, hcpDetails.get("addressLine1"), mediumWait());
        clickAndMoveToElementVisible(input_city, mediumWait());
        input_city.clear();
        sendKeysAndMoveToElementVisible(input_city, hcpDetails.get("city"), mediumWait());
        clickWhileCondition(dropdown_state, "aria-expanded", "false", mediumWait());
        hcpDetails.put("stateCode", (backUpWElement = getRandomWebElementFromList(elementList_stateCodesList, mediumWait())).getAttribute("data-value"));
        clickAndMoveToElementClickable(backUpWElement, mediumWait());
        clickAndMoveToElementVisible(input_zipCode, mediumWait());
        input_zipCode.clear();
        sendKeysAndMoveToElementVisible(input_zipCode, hcpDetails.get("zipCode"), mediumWait());
        clickWhileCondition(dropdown_country, "aria-expanded", "false", mediumWait());
        hcpDetails.put("country", (backUpWElement = getRandomWebElementFromList(elementList_countriesList, mediumWait())).getAttribute("data-value"));
        clickAndMoveToElementClickable(backUpWElement, mediumWait());
        clickAndMoveToElementVisible(input_email, mediumWait());
        input_email.clear();
        sendKeysAndMoveToElementVisible(input_email, hcpDetails.get("email"), mediumWait());
        return hcpDetails;
    }

    /**
     * This method is used to prepare the data for the HCP account creation, it handle if the data will be generated randomly or it will contains an specific
     * data from the ConfigurableCustomerLookup feature
     *
     * @param identifier   it is used to add a label at the begining of the first name, use to identify the records created by Automation, it can have any value
     * @param npi          it contains the National Provider Identifier
     * @param firstName    it contains the first name for the new HCP account (required)
     * @param middleName   it contains the middle name for the new HCP account (optional)
     * @param lastName     it contains the last name for the new HCP account (required)
     * @param dateOfBirth   it contains the DOB for the new HCP account (required)
     * @param email        it contains the email for the new HCP account (optional)
     * @param phoneOrFax   it contains the phone or fax for the new HCP account (required)
     * @param addressLine1 it contains the address for the new HCP account (optional)
     * @param state        it contains the state for the new HCP account (optional)
     * @param city         it contains the city for the new HCP account (optional)
     * @param zipCode      it contains the zipcode for the new HCP account (optional)
     * @param country      it contains the country for the new HCP account (optional)
     * @throws Exception
     * @author J.Ruano
     */
    private HashMap<String, String> hibrydHCPForm(String identifier, String npi, String firstName, String middleName, String lastName, String dateOfBirth, String email, String phoneOrFax, String addressLine1, String state, String city, String zipCode, String country) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("statesUSCodes");
        Faker faker = new Faker();
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        //============Storing Data Into hcpDetails
        HashMap<String, String> hcpDetails = new HashMap<String, String>();
        hcpDetails.put("npi", hibrydHCPFormFilter(npi, "npi"));
        hcpDetails.put("firstName", identifier + hibrydHCPFormFilter(firstName, "firstName"));
        hcpDetails.put("middleName", hibrydHCPFormFilter(middleName, "middleName"));
        hcpDetails.put("lastName", hibrydHCPFormFilter(lastName, "lastName") + underScore + generateTimeStamp(dateFormat));
        hcpDetails.put("dateOfBirth", hibrydHCPFormFilter(dateOfBirth, "dateOfBirth"));
        hcpDetails.put("email", hibrydHCPFormFilter(email, "email"));
        hcpDetails.put("phoneOrFax", hibrydHCPFormFilter(phoneOrFax, "phoneOrFax"));
        hcpDetails.put("addressLine1", hibrydHCPFormFilter(addressLine1, "addressLine1"));
        hcpDetails.put("stateCode", hibrydHCPFormFilter(state, "stateCode"));
        hcpDetails.put("city", hibrydHCPFormFilter(city, "city"));
        hcpDetails.put("zipCode", hibrydHCPFormFilter(zipCode, "zipCode"));
        hcpDetails.put("country", hibrydHCPFormFilter(country, "country"));
        return hcpDetails;
    }

    /**
     * This method is to check anc create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param hcpValue    it will contains the data for each field to be populated
     * @param nameOfField it is used to know which field will be evaluated
     * @return it will return the valued used to fill the HCP form
     * @throws Exception
     * @author J.Ruano
     */
    public String hibrydHCPFormFilter(String hcpValue, String nameOfField) throws Exception {
        WebElement backUpWElement = null;
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        String notApply = "N_A";
        String randomOption = "RND";
        String returnedValue = "";
        Faker faker = new Faker();
        switch (nameOfField) {
            case "npi":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().randomNumber(10, true));
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "firstName":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.name().firstName();
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "middleName":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.funnyName().name();
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "lastName":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.name().lastName() + underScore + generateTimeStamp(dateFormat);
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "dateOfBirth":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = getRandomDate();
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "email":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.name().lastName() + underScore + generateTimeStamp(dateFormat) + "@sharklasers.com";
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "phoneOrFax":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.phoneNumber().cellPhone().replace(".", "").replace("-", "");
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "addressLine1":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.address().streetName();
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "stateCode":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().numberBetween(0, elementList_stateCodesList.size() - 1));
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "city":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.address().cityName();
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "zipCode":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().randomNumber(5, true));
                } else {
                    if (hcpValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcpValue;
                    } else {
                        returnedValue = hcpValue;
                    }
                }
                break;

            case "country":
                if (!hcpValue.trim().isEmpty() && hcpValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().numberBetween(0, elementList_countriesList.size() - 1));
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
     * It is used to fulfill the HCP New Account form, validating which elements are going to be fill
     *
     * @param hcpDetails it contains the list of all the fields with their respective data
     * @throws Exception
     * @author J.Ruano
     */
    public void fillingHybridHCPForm(HashMap<String, String> hcpDetails) throws Exception {
        String notApply = "N_A";
        Faker faker = new Faker();

        clickWhileCondition(dropdown_type, "aria-expanded", "false", mediumWait());
        clickAndMoveToElementClickable(getRandomWebElementIgnoreText(dropdown_TypeList, "--None--"), mediumWait());
        clickWhileCondition(dropdown_subType, "aria-expanded", "false", mediumWait());
        clickAndMoveToElementClickable(getRandomWebElementIgnoreIdexValue(dropdown_subTypeList, 0), mediumWait());
        clickAndMoveToElementVisible(input_firstName, mediumWait());
        input_firstName.clear();
        sendKeysAndMoveToElementVisible(input_firstName, hcpDetails.get("firstName"), mediumWait());

        if (!hcpDetails.get("middleName").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_middleName, hcpDetails.get("middleName"), mediumWait());
        }
        clickAndMoveToElementVisible(input_lastName, mediumWait());
        input_lastName.clear();
        sendKeysAndMoveToElementVisible(input_lastName, hcpDetails.get("lastName"), mediumWait());

        if (!hcpDetails.get("npi").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_npi, hcpDetails.get("npi"), mediumWait());
        }

        if (!hcpDetails.get("dateOfBirth").trim().equalsIgnoreCase(notApply)) {
            clickAndMoveToElementVisible(datePicker_DOB, mediumWait());
            datePicker_DOB.clear();
            sendKeysAndMoveToElementVisible(datePicker_DOB, hcpDetails.get("dateOfBirth"), mediumWait());
        }
        clickAndMoveToElementVisible(input_phoneOrFax, mediumWait());
        input_phoneOrFax.clear();
        sendKeysAndMoveToElementVisible(input_phoneOrFax, hcpDetails.get("phoneOrFax"), mediumWait());

        if (!hcpDetails.get("addressLine1").trim().equalsIgnoreCase(notApply)) {
            clickAndMoveToElementVisible(input_addressLine1, mediumWait());
            input_addressLine1.clear();
            sendKeysAndMoveToElementVisible(input_addressLine1, hcpDetails.get("addressLine1"), mediumWait());
        }

        if (!hcpDetails.get("city").trim().equalsIgnoreCase(notApply)) {
            clickAndMoveToElementVisible(input_city, mediumWait());
            input_city.clear();
            sendKeysAndMoveToElementVisible(input_city, hcpDetails.get("city"), mediumWait());
        }

        if (!hcpDetails.get("stateCode").trim().equalsIgnoreCase(notApply)) {
            clickWhileCondition(dropdown_state, "aria-expanded", "false", mediumWait());
            clickAndMoveToElementClickableFromListByAttribute(elementList_stateCodesList, "data-value", hcpDetails.get("stateCode"));
        }

        if (!hcpDetails.get("zipCode").trim().equalsIgnoreCase(notApply)) {
            clickAndMoveToElementVisible(input_zipCode, mediumWait());
            input_zipCode.clear();
            sendKeysAndMoveToElementVisible(input_zipCode, hcpDetails.get("zipCode"), mediumWait());
        }

        if (!hcpDetails.get("country").trim().equalsIgnoreCase(notApply)) {
            clickWhileCondition(dropdown_country, "aria-expanded", "false", mediumWait());
            clickAndMoveToElementClickableFromListByAttribute(elementList_countriesList, "data-value", hcpDetails.get("country"));
        }
    }

    /**
     * It clicks the save button after all the data required for the HCP account is populated
     *
     * @throws Exception
     */
    public void clickSaveButton() throws Exception {
        clickAndMoveToElementClickable(button_saveAccount, mediumWait());
    }

    /**
     * It retrieves the Account ID generated for the new HCP account created
     *
     * @return the Account ID added to the new HCP account
     * @throws Exception
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
}