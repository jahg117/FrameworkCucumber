package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.JsonFiles;
import utils.Values;
import java.util.List;
import java.util.Map;

public class NewAccountPage extends CommonFunctions {

    @FindBy(xpath = "//div[@class='bPageTitle']")
    private WebElement labelNewAccountTitle;

    @FindBy(xpath = "//select[contains(@name,'CreateAccount')]")
    private WebElement dropdownRecordType;

    @FindBy(xpath = "//table//input[contains(@id,'ACS_CreateAccount')]")
    private WebElement buttonContinue;

    @FindBy(xpath = "//iframe[@title='accessibility title']")
    private List<WebElement> iframeCustomerLookup;

    protected FileReading fileReading = new FileReading();
    private final Logger logger = Logger.getLogger(CommonFunctions.class);

    Class<?> myClass;
    {
        try {
            fileReading.setLog4jFile();
            fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
            maxNumberOfTries = Integer.parseInt(fileReading.getField(Values.TXT_RETRYWHILE));
            myClass = Class.forName(Values.REFLECTION_COMMONFUNCTIONSCLASSPATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void selectRecordType(String dropdownOption) throws Exception {
        switchToFrameByWebElementIndexOrName(iframeCustomerLookup.get(iframeCustomerLookup.size() - 1), mediumWait());
        waitForElementVisibility(labelNewAccountTitle, mediumWait());
        selectAndMoveDropdownByText(dropdownRecordType, dropdownOption, mediumWait());
        clickAndMoveToElementVisible(buttonContinue, mediumWait());
        switchToParentFrame();
    }

    public String randomSelectionJSONFile(String keyName, String fileName) throws Exception {
        String selectedElement = "";
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(fileName);
        try {
            selectedElement = jsonFile.getRandomFieldArray(keyName);
        } catch (Exception e) {
        }
        return selectedElement;
    }

    /**
     * Used to assign the correct string value for the dropdown option
     *
     * @param accountType contains the abbreviation of the account type the available optios are:
     *                    hca (Facility)
     *                    hcp (Health Care Provider)
     *                    cpc (Consumer/Patient/Caregiver)
     *                    emp (Employee)
     * @throws Exception related to selenium
     * @author J.Ruano
     */
    public String assignCorrectAccountTypeValue(String accountType) throws Exception {
        String statusOperation = "";
            switch (accountType.trim().toLowerCase()) {
                case "hca":
                    statusOperation = Values.TXT_HEALTHCAREACCOUNT;
                    break;

                case "hcp":
                    statusOperation = Values.TXT_HEALTHCAREPROVIDER;
                    break;

                case "cpc":
                    statusOperation = Values.TXT_PATIENTCPC;
                    break;

                case "emp":
                    statusOperation = Values.TXT_EMPLOYEE;
                    break;

                default:
                    logger.warn(Values.TXT_SWITCHDEFAULTMESSAGE);
                    break;
            }
        return statusOperation;
    }

    /**
     * Use to select a random accountType or an specific accountType from table
     *
     * @param accountTypeList it contains all the accounts type
     * @return it returns the account type to be used
     * @throws Exception related to selenium
     * @author J.Ruano
     */
    public String selectAccountTypeFromList(List<Map<String, String>> accountTypeList) throws Exception {
        int counter = 0;
        String statusOperation = "";
        boolean rndSelected = false;
        try {
            for (Map<String, String> accountType : accountTypeList) {
                if (accountTypeList.get(counter).get(Values.TXT_ACCOUNTTYPE).equalsIgnoreCase(Values.TXT_RANDOM)) {
                    if (accountTypeList.get(counter).get("useThisAccount").equalsIgnoreCase(Values.TXT_Y_VALUE)) {
                        statusOperation = accountTypeList.get(getRandomNumberByLimits(1, accountTypeList.size())).get(Values.TXT_ACCOUNTTYPE);
                        rndSelected = true;
                        break;
                    }
                }
                counter++;
            }
            counter = 0;
            if (!rndSelected) {
                for (Map<String, String> accountType : accountTypeList) {
                    if (accountTypeList.get(counter).get("useThisAccount").equalsIgnoreCase(Values.TXT_Y_VALUE)) {
                        statusOperation = accountTypeList.get(counter).get(Values.TXT_ACCOUNTTYPE);
                        break;
                    }
                    counter++;
                }
            }
        } catch (Exception e) {

        }
        return statusOperation;
    }
}