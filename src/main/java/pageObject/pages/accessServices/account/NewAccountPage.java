package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.JsonFiles;
import utils.Values;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class NewAccountPage extends CommonFunctions {

    @FindBy(xpath = "//div[@class='bPageTitle']")
    private WebElement label_newAccountTitle;

    @FindBy(xpath = "//select[contains(@name,'CreateAccount')]")
    private WebElement dropdown_recordType;

    @FindBy(xpath = "//table//input[contains(@id,'ACS_CreateAccount')]")
    private WebElement button_continue;

    @FindBy(xpath = "//iframe[@title='accessibility title']")
    private List<WebElement> iframe_customerLookup;

    protected FileReading fileReading = new FileReading();
    private final Logger logger = Logger.getLogger(CommonFunctions.class);
    public static int maxNumberOfTries = 0;

    Class<?> myClass;

    {
        try {
            fileReading.setLog4jFile();
            fileReading.setFileName(Values.TXT_GLOBAL_PROPERTIES);
            maxNumberOfTries = Integer.parseInt(fileReading.getField(Values.TXT_RETRYWHILE));
            myClass = Class.forName("base.functions" + "." + "CommonFunctions");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean selectRecordType(String dropdownOption) throws Exception {
        boolean statusOperation = false;
        try {
            switchToFrameByWebElementIndexOrName(iframe_customerLookup.get(iframe_customerLookup.size() - 1), 20);
            waitForElementVisibility(label_newAccountTitle, 20);
            selectAndMoveDropdownByText(dropdown_recordType, dropdownOption, 20);
            clickAndMoveToElementVisible(button_continue, 15);
            switchToParentFrame();
            statusOperation = true;
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("selectRecordType")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "selectRecordType");
                        statusOperation = (boolean) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), dropdownOption);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    public String randomSelectionJSONFile(String keyName, String fileName) throws Exception {
        String selectedElement = "";
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(fileName);
        try {
            selectedElement = jsonFile.getRandomFieldArray(keyName);
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("randomSelectionJSONFile")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "randomSelectionJSONFile");
                        selectedElement = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), keyName, fileName);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
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
     * @throws Exception
     * @author J.Ruano
     */
    public String assignCorrectAccountTypeValue(String accountType) throws Exception {
        String statusOperation = "";
        try {
            switch (accountType.trim().toLowerCase()) {
                case "hca":
                    statusOperation = "Health Care Account";
                    break;

                case "hcp":
                    statusOperation = "Health Care Provider";
                    break;

                case "cpc":
                    statusOperation = "Consumer/Patient/Caregiver";
                    break;

                case "emp":
                    statusOperation = "Internal AZ";
                    break;
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("assignCorrectAccountTypeValue")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "assignCorrectAccountTypeValue");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), accountType);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }

    /**
     * Use to select a random accountType or an specific accountType from table
     *
     * @param accountTypeList it contains all the accounts type
     * @return it returns the account type to be used
     * @throws Exception
     * @author J.Ruano
     */
    public String selectAccountTypeFromList(List<Map<String, String>> accountTypeList) throws Exception {
        int counter = 0;
        String statusOperation = "";
        boolean rndSelected = false;
        try {
            for (Map<String, String> accountType : accountTypeList) {
                if (accountTypeList.get(counter).get("accountType").equalsIgnoreCase("RND")) {
                    if (accountTypeList.get(counter).get("useThisAccount").equalsIgnoreCase("Y")) {
                        statusOperation = accountTypeList.get(getRandomNumberByLimits(1, accountTypeList.size())).get("accountType");
                        rndSelected = true;
                        break;
                    }
                }
                counter++;
            }
            counter = 0;
            if (!rndSelected) {
                for (Map<String, String> accountType : accountTypeList) {
                    if (accountTypeList.get(counter).get("useThisAccount").equalsIgnoreCase("Y")) {
                        statusOperation = accountTypeList.get(counter).get("accountType");
                        break;
                    }
                    counter++;
                }
            }
        } catch (Exception e) {
            if (Values.globalCounter < maxNumberOfTries) {
                Values.globalCounter++;
                Method[] arrayDeclaredMethods = myClass.getDeclaredMethods();
                for (int j = 0; j < arrayDeclaredMethods.length; j++) {
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("selectAccountTypeFromList")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "selectAccountTypeFromList");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), accountTypeList);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }
}