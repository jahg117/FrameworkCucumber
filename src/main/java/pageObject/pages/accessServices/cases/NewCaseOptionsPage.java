package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;
import java.util.List;

public class NewCaseOptionsPage extends CommonFunctions {

    @FindBy(xpath = "//div[@class='forceChangeRecordType']")
    private WebElement formCaseOptionsPage;

    @FindBy(xpath = "//div[@class='forceChangeRecordType']//span[contains(@class,'topdown-radio--label')]")
    private List<WebElement> listCaseOptions;

    @FindBy(xpath = "//*[contains(text(),'New Case')]/following::*[./text()='Next']")
    private WebElement button_next;

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

    public boolean isFormCaseOptionsPageDisplayed() throws Exception {
        return waitForElementVisibility(formCaseOptionsPage, mediumWait());
    }

    public void selectCaseOption(String caseOption) throws Exception {
        waitForElementListVisible(listCaseOptions, mediumWait());
        if (!caseOption.equalsIgnoreCase(Values.TXT_RANDOM)) {
            for (WebElement el : listCaseOptions) {
                if (getWebElementText(el).equalsIgnoreCase(caseOption)) {
                    clickAndMoveToElementClickable(el, mediumWait());
                    break;
                }
            }
        } else {
            clickAndMoveToElementClickable(getRandomWebElementFromList(listCaseOptions, mediumWait()), mediumWait());
        }
        clickAndMoveToElementClickable(button_next, mediumWait());
        }
}