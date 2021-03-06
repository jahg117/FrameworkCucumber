package pageObject.pages.accessServices.productEnrollment;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.JsonFiles;
import utils.Values;
import java.util.List;
import java.util.Map;

public class CreateNewEnrollmentPage extends CommonFunctions {

    //@FindBy(xpath = "//iframe[@title='New Program Enrollment']")
    @FindBy(xpath = "//iframe[@title='accessibility title'][not(@cd_frame_id_)]")
    private WebElement iframeNewProgramEnrollment;

    @FindBy(xpath = "(//iframe[@title='accessibility title'])[last()]")
    private WebElement iframeEnrollButton;

    @FindBy(xpath = "//span[@class='lookupInput']//input")
    private WebElement inputProduct;

    @FindBy(xpath = "//img[@class='lookupIcon']")
    private WebElement iconLookUp;

    @FindBy(xpath = "//span[@class='dateInput dateOnlyInput']")
    private WebElement inputProgramEndDate;

    @FindBy(xpath = "//input[@value='Enroll']")
    private WebElement buttonEnroll;

    @FindBy(xpath = "//*[@title='New Care Team Member']")
    private WebElement buttonNewCareTeamMember;

    private By iconLoadPage = By.xpath("//span[contains(@id,'actionstatus.stop') and @style='display: none;']");

    @FindBy(xpath = "//span[normalize-space(text())='Logged out']")
    private WebElement buttonLoggedOut;

    protected FileReading fileReading = new FileReading();
    private final Logger logger = Logger.getLogger(CommonFunctions.class);
    public static int maxNumberOfTries = 0;

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

    public boolean isProductEnrollmentPageDisplayed() throws Exception {
        return waitForElementVisibility(iframeNewProgramEnrollment, longWait());
    }

    public String fillProductEnrollmentForm(String productType) throws Exception {
        String statusOperation = "";
        if (productType.equalsIgnoreCase(Values.REPLACETO_EMPTY)) {
            productType = Values.TXT_AZ;
        }
        if (productType.equalsIgnoreCase(Values.TXT_AZ)
                || productType.equalsIgnoreCase(Values.TXT_DSI)) {
            JsonFiles file = new JsonFiles();
            file.setFileName(Values.TXT_1372FILENAME);
            statusOperation = file.getRandomFieldArray(productType);
        } else {
            statusOperation = productType;
        }
        switchToFrameByWebElementIndexOrName(iframeNewProgramEnrollment, mediumWait());
        waitForElementVisibility(inputProduct, mediumWait());
        sendKeysAndMoveToElementVisible(inputProduct, statusOperation, mediumWait());
        clickElementVisible(inputProgramEndDate, mediumWait());
        switchToParentFrame();
        return statusOperation;
    }

    public void clickEnrollButton() throws Exception {
        switchToFrameByWebElementIndexOrName(iframeEnrollButton, shortWait());
        waitForNumberOfElementsToBe(iconLoadPage, 0, 5);
        waitForElementClickable(buttonEnroll, mediumWait());
        try {
            scrollMethodToWebElement(buttonEnroll);
            if (!buttonEnroll.isDisplayed()) {
                scrollMethodToWebElement(buttonEnroll);
            }
            if (isClickableElementEnabled(buttonEnroll,shortWait())) {
                buttonEnroll.click();
            }else {
                scrollMethodToWebElement(buttonEnroll);
                buttonEnroll.click();
            }
        } catch (Exception e) {
            clickMethodsWebElement(buttonEnroll);
        }
        switchToParentFrame();
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
                if (consentTypeList.get(counter).get(Values.TXT_CONSENTTYPE).equalsIgnoreCase(Values.TXT_RANDOM)) {
                    if (consentTypeList.get(counter).get("useThisAccount").equalsIgnoreCase(Values.TXT_Y_VALUE)) {
                        statusOperation = consentTypeList.get(getRandomNumberByLimits(1, consentTypeList.size())).get(Values.TXT_CONSENTTYPE);
                        rndSelected = true;
                        break;
                    }
                }
                counter++;
            }
            counter = 0;
            if (!rndSelected) {
                for (Map<String, String> consentType : consentTypeList) {
                    if (consentTypeList.get(counter).get("useThisAccount").equalsIgnoreCase(Values.TXT_Y_VALUE)) {
                        statusOperation = consentTypeList.get(counter).get(Values.TXT_CONSENTTYPE);
                        break;
                    }
                    counter++;
                }
            }
        } catch (Exception e) {
            logger.info(Values.TXT_EXCREFLECTION);
        }
        return statusOperation;
    }
}