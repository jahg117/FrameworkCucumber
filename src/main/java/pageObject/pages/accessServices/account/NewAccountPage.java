package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.JsonFiles;

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

    public void selectRecordType(String dropdownOption) throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_customerLookup.get(iframe_customerLookup.size() - 1), 20);
        waitForElementVisibility(label_newAccountTitle, 20);
        selectAndMoveDropdownByText(dropdown_recordType, dropdownOption, 20);
        clickAndMoveToElementVisible(button_continue, 15);
        switchToParentFrame();
    }

    public String randomSelectionJSONFile(String keyName, String fileName) throws Exception {
        String selectedElement = "";
        JsonFiles jsonFile = new JsonFiles();
        jsonFile.setFileName(fileName);
        return selectedElement = jsonFile.getRandomFieldArray(keyName);
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
        String dropdownOption = "";
        switch (accountType.trim().toLowerCase()) {
            case "hca":
                dropdownOption = "Health Care Account";
                break;

            case "hcp":
                dropdownOption = "Health Care Provider";
                break;

            case "cpc":
                dropdownOption = "Consumer/Patient/Caregiver";
                break;

            case "emp":
                dropdownOption = "Internal AZ";
                break;
        }
        return dropdownOption;
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
        String accountTypeSelected = "";
        boolean rndSelected = false;
        for (Map<String, String> accountType : accountTypeList) {
            if (accountTypeList.get(counter).get("accountType").equalsIgnoreCase("RND")) {
                if (accountTypeList.get(counter).get("useThisAccount").equalsIgnoreCase("Y")) {
                    accountTypeSelected = accountTypeList.get(getRandomNumberByLimits(1, accountTypeList.size())).get("accountType");
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
                    accountTypeSelected = accountTypeList.get(counter).get("accountType");
                    break;
                }
                counter++;
            }
        }
        return accountTypeSelected;
    }
}
