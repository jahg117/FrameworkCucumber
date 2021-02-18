package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import com.github.javafaker.Faker;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

import java.util.HashMap;
import java.util.List;

public class NewHCAWizardPage extends CommonFunctions {

    @FindBy(xpath = "//input[@name='Name'][1]")
    private WebElement input_hcaName;

    @FindBy(xpath = "//input[@name='Type']")
    private WebElement dropdown_type;

    @FindBy(xpath = "//*[contains(@id,'input')][@role='option'][@data-value]")
    private List<WebElement> dropdown_TypeList;

    @FindBy(xpath = "//input[contains(@aria-activedescendant,'input')]")
    private List<WebElement> dropdown_optionsType;

    @FindBy(xpath = "//input[contains(@name,'SubType')]")
    private WebElement dropdown_subType;

    @FindBy(xpath = "//*[contains(@data-api,'SubType')]//*[@data-value]")
    private List<WebElement> dropdown_subTypeList;

    @FindBy(xpath = "//input[contains(@name,'National_Provider_Identifier')]")
    private WebElement input_npi;

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

    @FindBy(xpath = "//input[@placeholder='Search Places']")
    private WebElement input_googleAddress;

    @FindBy(xpath = "//*[contains(text(),'Email Type')]/following::*[@name='optionSelect']")
    private WebElement dropdown_emailType;

    @FindBy(xpath = "//button[@title='Save']")
    private WebElement button_saveAccount;

    @FindBy(xpath = "//*[contains(@name,'_State_')]/following::*[@role='option']/following::*[string-length(@data-value)=2]")
    private List<WebElement> elementList_stateCodesList;

    @FindBy(xpath = "//*[contains(@name,'_Country_')]/following::*[@role='option']/following::*[@data-value]")
    private List<WebElement> elementList_countriesList;

    @FindBy(xpath = "//a[@data-label='System Info']")
    private WebElement label_systemInfo;

    @FindBy(xpath = "//*[contains(text(),'Account ID')]//..")
    private WebElement label_externalID;

    @FindBy(xpath = "//*[contains(text(),'Account ID')]/ancestor::div[1]")
    private WebElement label_externalID2;

    @FindBy(xpath = "//span[normalize-space(text())='Logged out']")
    private WebElement button_loggedOut;

    @FindBy(xpath = "//*[contains(text(),'Last Modified By')]/following::a[1]")
    private WebElement linkButton_lastModifiedBy;



    public boolean isNewHCAWizardFormDisplayed() {
        return waitForElementVisibility(dropdown_type, 30);
    }

    /**
     * It validates if the new Account will be created completely random or specific data at columns in ConfigurableCustomerLookup.feature tables, can contains
     * specific data or "RND" if that columns requires random data, or "N_A" if it does not need to be filled
     *
     * @param identifier   it is used to add a label at the begining of the first name, use to identify the records created by Automation, it can have any value
     * @param npi          it contains the National Provider Identifier
     * @param nameHCA      it contains the name for the new HCA account (required)
     * @param email        it contains the email for the new HCA account (optional)
     * @param phoneOrFax   it contains the phone or fax for the new HCA account (required)
     * @param addressLine1 it contains the address for the new HCA account (optional)
     * @param state        it contains the state for the new HCA account (optional)
     * @param city         it contains the city for the new HCA account (optional)
     * @param zipCode      it contains the zipcode for the new HCA account (optional)
     * @param country      it contains the country for the new HCA account (optional)
     * @param randomRecord value used to know if the account will be fill with random data "RND" when it is random
     * @throws Exception
     * @author J.Ruano
     */
    public void validateAndCreateHCA(String identifier, String npi, String nameHCA, String email, String phoneOrFax, String addressLine1, String state, String city, String zipCode, String country, String randomRecord) throws Exception {
        HashMap<String, String> hcaDetailsStoreData = new HashMap<String, String>();
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("HCARecord");
        boolean allDataRND = false;
        if (randomRecord.trim().equalsIgnoreCase("RND")) {
            hcaDetailsStoreData = fullHCAFormRND(identifier);
        } else {
            hcaDetailsStoreData = hibrydHCAForm(identifier, npi, nameHCA, email, phoneOrFax, addressLine1, state, city, zipCode, country);
            fillingHybridHCAForm(hcaDetailsStoreData);
        }
        clickSaveButton();
        hcaDetailsStoreData.put("externalID", getExternalID());
        jsonFile.storeDataIntoJSON(hcaDetailsStoreData);
    }

    /**
     * This method is to create a new HCA account completely with random data
     *
     * @param identifier it is used to add a label at the beginning of the first name, use to identify the records created by Automation, it can have any value
     * @return it returns a HashMap with string data used to fill the HCA form
     * @throws Exception
     * @author J.Ruano
     */
    public HashMap<String, String> fullHCAFormRND(String identifier) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("statesUSCodes");
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        WebElement backUpWElement = null;
        Faker faker = new Faker();
        HashMap<String, String> hcaDetails = new HashMap<String, String>();
        List<String> stateNames = splitRegex(jsonFile.getRandomFieldArray("statesUS"), "[|]");
        hcaDetails.put("npi", String.valueOf(faker.number().randomNumber(10, true)));
        hcaDetails.put("nameHCA", identifier + faker.name().firstName() + underScore + generateTimeStamp(dateFormat));
        hcaDetails.put("email", hcaDetails.get("nameHCA") + "@sharklasers.com");
        hcaDetails.put("phoneOrFax", faker.phoneNumber().cellPhone().replace(".", "").replace("-", ""));
        hcaDetails.put("addressLine1", faker.address().streetName());
        hcaDetails.put("city", faker.address().cityName());
        hcaDetails.put("zipCode", String.valueOf(faker.number().randomNumber(5, true)));
        //=====================================Populating The HCA Data
        clickAndMoveToElementVisible(input_hcaName, 10);
        input_hcaName.clear();
        sendKeysAndMoveToElementVisible(input_hcaName, hcaDetails.get("nameHCA"), 10);
        clickWhileCondition(dropdown_type, "aria-expanded", "false", 10);
        clickAndMoveToElementClickable(getRandomWebElementIgnoreText(dropdown_TypeList, "--None--"), 10);
        clickWhileCondition(dropdown_subType, "aria-expanded", "false", 10);
        clickAndMoveToElementClickable(getRandomWebElementIgnoreIdexValue(dropdown_subTypeList, 0), 10);
        sendKeysAndMoveToElementVisible(input_npi, hcaDetails.get("npi"), 10);
        sendKeysAndMoveToElementVisible(input_phoneOrFax, hcaDetails.get("phoneOrFax"), 10);
        sendKeysAndMoveToElementVisible(input_addressLine1, hcaDetails.get("addressLine1"), 10);
        clickWhileCondition(dropdown_state, "aria-expanded", "false", 10);
        hcaDetails.put("stateCode", (backUpWElement = getRandomWebElementFromList(elementList_stateCodesList, 10)).getAttribute("data-value"));
        clickAndMoveToElementClickable(backUpWElement, 10);
        sendKeysAndMoveToElementVisible(input_city, hcaDetails.get("city"), 10);
        sendKeysAndMoveToElementVisible(input_zipCode, hcaDetails.get("zipCode"), 10);
        clickWhileCondition(dropdown_country, "aria-expanded", "false", 10);
        hcaDetails.put("country", (backUpWElement = getRandomWebElementFromList(elementList_countriesList, 10)).getAttribute("data-value"));
        clickAndMoveToElementClickable(backUpWElement, 10);
        sendKeysAndMoveToElementVisible(input_email, hcaDetails.get("email"), 10);
        return hcaDetails;
    }

    /**
     * This method is used to prepare the data for the HCA account creation, it handle if the data will be generated randomly or it will contains an specific
     * data from the ConfigurableCustomerLookup feature
     *
     * @param identifier   it is used to add a label at the beginning of the first name, use to identify the records created by Automation, it can have any value
     * @param npi          it contains the National Provider Identifier
     * @param nameHCA      it contains the name for the new HCA account (required)
     * @param email        it contains the email for the new HCA account (optional)
     * @param phoneOrFax   it contains the phone or fax for the new HCA account (required)
     * @param addressLine1 it contains the address for the new HCA account (optional)
     * @param state        it contains the state for the new HCA account (optional)
     * @param city         it contains the city for the new HCA account (optional)
     * @param zipCode      it contains the zipcode for the new HCA account (optional)
     * @param country      it contains the country for the new HCA account (optional)
     * @return it returns a HashMap with string data used to fill the HCA form
     * @throws Exception
     * @author J.Ruano
     */
    public HashMap<String, String> hibrydHCAForm(String identifier, String npi, String nameHCA, String email, String phoneOrFax, String addressLine1, String state, String city, String zipCode, String country) throws Exception {
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName("statesUSCodes");
        Faker faker = new Faker();
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";

        HashMap<String, String> hcaDetails = new HashMap<String, String>();
        List<String> stateNames = splitRegex(jsonFile.getRandomFieldArray("statesUS"), "[|]");
        hcaDetails.put("npi", hibrydHCAFormFilter(npi, "npi"));
        hcaDetails.put("nameHCA", identifier + hibrydHCAFormFilter(nameHCA, "nameHCA") + underScore + generateTimeStamp(dateFormat));
        hcaDetails.put("email", hibrydHCAFormFilter(email, "email"));
        hcaDetails.put("phoneOrFax", hibrydHCAFormFilter(phoneOrFax, "phoneOrFax"));
        hcaDetails.put("addressLine1", hibrydHCAFormFilter(addressLine1, "addressLine1"));
        hcaDetails.put("stateCode", hibrydHCAFormFilter(state, "stateCode"));
        hcaDetails.put("city", hibrydHCAFormFilter(city, "city"));
        hcaDetails.put("zipCode", hibrydHCAFormFilter(zipCode, "zipCode"));
        hcaDetails.put("country", hibrydHCAFormFilter(country, "country"));
        return hcaDetails;
    }

    /**
     * This method is to check anc create the data that could be coming from the table in the ConfigurableCustomerLookup.feature or if it is selected randomly
     *
     * @param hcaValue    it will contains the data for each field to be populated
     * @param nameOfField it is used to know which field will be evaluated
     * @return it will return the valued used to fill the HCA form
     * @throws Exception
     * @author J.Ruano
     */
    public String hibrydHCAFormFilter(String hcaValue, String nameOfField) throws Exception {
        WebElement backUpWElement = null;
        String underScore = "_";
        String dateFormat = "MMM.dd.HH.mm";
        String notApply = "N_A";
        String randomOption = "RND";
        String returnedValue = "";
        Faker faker = new Faker();
        switch (nameOfField) {
            case "npi":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().randomNumber(10, true));
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "nameHCA":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.name().firstName() + underScore + generateTimeStamp(dateFormat);
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "email":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.name().firstName() + underScore + generateTimeStamp(dateFormat) + "@sharklasers.com";
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "phoneOrFax":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.phoneNumber().cellPhone().replace(".", "").replace("-", "");
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "addressLine1":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.address().streetName();
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "stateCode":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().numberBetween(0, elementList_stateCodesList.size() - 1));
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "city":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = faker.address().cityName();
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "zipCode":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().randomNumber(5, true));
                } else {
                    if (hcaValue.trim().equalsIgnoreCase(notApply)) {
                        returnedValue = hcaValue;
                    } else {
                        returnedValue = hcaValue;
                    }
                }
                break;

            case "country":
                if (!hcaValue.trim().isEmpty() && hcaValue.trim().equalsIgnoreCase(randomOption)) {
                    returnedValue = String.valueOf(faker.number().numberBetween(0, elementList_countriesList.size() - 1));
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
     * It is used to fulfill the HCA New Account form, validating which elements are going to be fill
     *
     * @param hcaDetails it contains the list of all the fields with their respective data
     * @throws Exception
     * @author J.Ruano
     */
    public void fillingHybridHCAForm(HashMap<String, String> hcaDetails) throws Exception {
        String notApply = "N_A";
        Faker faker = new Faker();
        clickAndMoveToElementVisible(input_hcaName, 10);
        input_hcaName.clear();
        sendKeysAndMoveToElementVisible(input_hcaName, hcaDetails.get("nameHCA"), 10);
        clickWhileCondition(dropdown_type, "aria-expanded", "false", 10);
        clickAndMoveToElementClickable(getRandomWebElementIgnoreText(dropdown_TypeList, "--None--"), 10);
        clickWhileCondition(dropdown_subType, "aria-expanded", "false", 10);
        clickAndMoveToElementClickable(getRandomWebElementIgnoreIdexValue(dropdown_subTypeList, 0), 10);
        if (!hcaDetails.get("npi").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_npi, hcaDetails.get("npi"), 10);
        }
        sendKeysAndMoveToElementVisible(input_phoneOrFax, hcaDetails.get("phoneOrFax"), 10);
        if (!hcaDetails.get("addressLine1").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_addressLine1, hcaDetails.get("addressLine1"), 10);
        }

        if (!hcaDetails.get("stateCode").trim().equalsIgnoreCase(notApply)) {
            clickWhileCondition(dropdown_state, "aria-expanded", "false", 10);
            clickAndMoveToElementClickableFromListByAttribute(elementList_stateCodesList, "data-value", hcaDetails.get("stateCode"));
        }

        if (!hcaDetails.get("city").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_city, hcaDetails.get("city"), 10);
        }

        if (!hcaDetails.get("zipCode").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_zipCode, hcaDetails.get("zipCode"), 10);
        }

        if (!hcaDetails.get("country").trim().equalsIgnoreCase(notApply)) {
            clickWhileCondition(dropdown_country, "aria-expanded", "false", 10);
            clickAndMoveToElementClickableFromListByAttribute(elementList_countriesList, "data-value", hcaDetails.get("country"));
        }

        if (!hcaDetails.get("email").trim().equalsIgnoreCase(notApply)) {
            sendKeysAndMoveToElementVisible(input_email, hcaDetails.get("email"), 10);
        }
    }

    /**
     * It clicks the save button after all the data required for the HCA account is populated
     *
     * @throws Exception
     */
    public void clickSaveButton() throws Exception {
        clickAndMoveToElementClickable(button_saveAccount, 10);
    }

    /**
     * It retrieves the Account ID generated for the new HCA account created
     *
     * @return the Account ID added to the new HCA account
     * @throws Exception
     */
    public String getExternalID() throws Exception {
        waitForElementVisibility(label_systemInfo, 20);
        clickMethod(label_systemInfo);
        if (waitForElementClickable(linkButton_lastModifiedBy, 10)) {
            scrollMethodToWebElement(linkButton_lastModifiedBy);
        } else {
            waitForElementVisibility(linkButton_lastModifiedBy, 10);
            scrollMethodToWebElement(linkButton_lastModifiedBy);
        }
        return label_externalID.getText().replace("Account ID", "").trim();
    }
}
