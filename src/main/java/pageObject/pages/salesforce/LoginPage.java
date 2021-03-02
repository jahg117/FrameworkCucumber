package pageObject.pages.salesforce;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.functions.CommonFunctions;
import utils.FileReading;

import java.util.List;
import java.util.Map;


public class LoginPage extends CommonFunctions {
    @FindBy(id = "username")
    private WebElement input_username;

    @FindBy(css = "#password")
    private WebElement input_password;

    @FindBy(css = "#Login")
    private WebElement button_login;

    public void enterUserPassword(String salesforceUser) throws Exception {
        FileReading fileReading = new FileReading();
        fileReading.setFileName("SalesforceCredentials.properties");
        waitForPageToLoad();
        sendKeysElementVisible(input_username, fileReading.getField(salesforceUser), 20);
        sendKeysElementVisible(input_password, fileReading.getField(salesforceUser + "Password"), 10);
        clickElementVisible(button_login, 10);
    }

    /**
     * Use to select a random user account or an specific user account from table
     *
     * @param userAccountList it contains all the accounts type
     * @return it returns the user account to be used to login at salesforce
     * @throws Exception
     * @author J.Ruano
     */
    public String selectUserFromList(List<Map<String, String>> userAccountList) throws Exception {
        int counter = 0;
        String userAccountSelected = "";
        boolean rndSelected = false;
        for (Map<String, String> userAccount : userAccountList) {
            if (userAccountList.get(counter).get("userAccount").equalsIgnoreCase("RND")) {
                if (userAccountList.get(counter).get("useThisAccount").equalsIgnoreCase("Y")) {
                    userAccountSelected = userAccountList.get(getRandomNumberByLimits(0, userAccountList.size())).get("userAccount");
                    rndSelected = true;
                    break;
                }
            }
            counter++;
        }
        counter = 0;
        if (!rndSelected) {
            for (Map<String, String> userAccount : userAccountList) {
                if (userAccountList.get(counter).get("useThisAccount").equalsIgnoreCase("Y")) {
                    userAccountSelected = userAccountList.get(counter).get("userAccount");
                    break;
                }
                counter++;
            }
        }
        return userAccountSelected;
    }
}
