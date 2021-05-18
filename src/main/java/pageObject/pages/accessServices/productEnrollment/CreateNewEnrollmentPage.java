package pageObject.pages.accessServices.productEnrollment;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.JsonFiles;
import utils.Values;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class CreateNewEnrollmentPage extends CommonFunctions {

    @FindBy(xpath = "//iframe[@title='New Program Enrollment']")
    private WebElement iframe_newProgramEnrollment;

    @FindBy(xpath = "//span[@class='lookupInput']")
    private WebElement input_product;

    @FindBy(xpath = "//img[@class='lookupIcon']")
    private WebElement icon_lookUp;

    @FindBy(xpath = "//span[@class='dateInput dateOnlyInput']")
    private WebElement input_programEndDate;

    @FindBy(xpath = "//td[@class='pbButtonb ']//input[@value='Enroll']")
    private WebElement button_enroll;

    @FindBy(xpath = "//*[@title='New Care Team Member']")
    private WebElement button_newCareTeamMember;

    private By icon_loadPage = By.xpath("//span[contains(@id,'actionstatus.stop') and @style='display: none;']");

    @FindBy(xpath = "//span[normalize-space(text())='Logged out']")
    private WebElement button_loggedOut;

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

    public boolean isProductEnrollmentPageDisplayed() throws Exception {
        return waitForElementVisibility(iframe_newProgramEnrollment, longWait());
    }

    public String fillProductEnrollmentForm(String productType) throws Exception {
        String statusOperation = "";
        waitForElementVisibility(button_loggedOut, mediumWait());
        if (productType.equalsIgnoreCase("")) {
            productType = "AZ";
        }
        if (productType.equalsIgnoreCase("AZ")
                || productType.equalsIgnoreCase("DSI")) {
            JsonFiles file = new JsonFiles();
            file.setFileName("1372_EnrollmentProducts");
            statusOperation = file.getRandomFieldArray(productType);
        } else {
            statusOperation = productType;
        }
        switchToFrameByWebElementIndexOrName(iframe_newProgramEnrollment, mediumWait());
        waitForElementVisibility(input_product, mediumWait());
        sendKeysAndMoveToElementVisible(input_product, statusOperation, mediumWait());
        clickElementVisible(input_programEndDate, mediumWait());
        switchToParentFrame();
        return statusOperation;
    }

    public void clickEnrollButton() throws Exception {
        switchToFrameByWebElementIndexOrName(iframe_newProgramEnrollment, mediumWait());
        waitForNumberOfElementsToBe(icon_loadPage, 0, shortWait());
        waitForElementClickable(button_enroll, mediumWait());
        scrollMethodToWebElement(button_enroll);
        if (!button_enroll.isDisplayed()) {
            scrollMethodToWebElement(button_enroll);
        }
        clickElementJS(button_enroll);
    }


    /**
     * Use to select a random consent type or an specific accountType from table
     *
     * @param consentTypeList it contains all the consents type in the config table
     * @return it returns the consent type to be used
     * @throws Exception
     * @author J.Ruano
     */
    public String selectConsentTypeFromList(List<Map<String, String>> consentTypeList) throws Exception {
        int counter = 0;
        String statusOperation = "";
        boolean rndSelected = false;
        try {
            for (Map<String, String> consentType : consentTypeList) {
                if (consentTypeList.get(counter).get("consentType").equalsIgnoreCase("RND")) {
                    if (consentTypeList.get(counter).get("useThisAccount").equalsIgnoreCase("Y")) {
                        statusOperation = consentTypeList.get(getRandomNumberByLimits(1, consentTypeList.size())).get("consentType");
                        rndSelected = true;
                        break;
                    }
                }
                counter++;
            }
            counter = 0;
            if (!rndSelected) {
                for (Map<String, String> consentType : consentTypeList) {
                    if (consentTypeList.get(counter).get("useThisAccount").equalsIgnoreCase("Y")) {
                        statusOperation = consentTypeList.get(counter).get("consentType");
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
                    if (arrayDeclaredMethods[j].getName().equalsIgnoreCase("selectConsentTypeFromList")) {
                        logger.warn(Values.TXT_RETRYMSG001 + "selectConsentTypeFromList");
                        statusOperation = (String) arrayDeclaredMethods[j].invoke(this.myClass.getConstructor().newInstance(), consentTypeList);
                        break;
                    }
                }
            }
        }
        Values.globalCounter = 0;
        return statusOperation;
    }
}