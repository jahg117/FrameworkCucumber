package pageObject.pages.salesforce;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import base.functions.CommonFunctions;
import utils.FileReading;
import utils.Values;

import java.util.List;
import java.util.Map;


public class LoginPage extends CommonFunctions {
    @FindBy(id = "username")
    private WebElement input_username;

    @FindBy(css = "#password")
    private WebElement input_password;

    @FindBy(css = "#Login")
    private WebElement button_login;

    Class<?> myClass;

    {
        try {
            myClass = Class.forName(Values.REFLECTION_COMMONFUNCTIONSCLASSPATH);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void enterUserPassword(String salesforceUser) throws Exception {
        FileReading fileReading = new FileReading();
        fileReading.setFileName("SalesforceCredentials.properties");
        waitForPageToLoad();
        String environment = fileReading.getField("environment").toUpperCase();
        if(!environment.equalsIgnoreCase("UAT")&&!environment.equalsIgnoreCase("INT")){
            environment = "UAT";
        }
        if (salesforceUser.trim().startsWith(Values.TXT_UAT)) {
            environment = Values.TXT_UAT;
            salesforceUser = salesforceUser.replaceAll(Values.REGEX_STARTSWITH,Values.REPLACETO_EMPTY);
        }else{
            if (salesforceUser.trim().startsWith(Values.TXT_INT)) {
                environment = Values.TXT_INT;
                salesforceUser = salesforceUser.replaceAll(Values.REGEX_STARTSWITH, Values.REPLACETO_EMPTY);
            }
        }
        String usr = fileReading.getField(salesforceUser + "" + environment);
        String pass = fileReading.getField(salesforceUser + "Password" + environment);
        sendKeysElementVisible(input_username, usr, shortWait());
        sendKeysElementVisible(input_password, pass, shortWait());
        submitByWebElement(input_password);
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
