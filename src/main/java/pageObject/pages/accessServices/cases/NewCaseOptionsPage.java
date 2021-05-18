package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;
import java.util.List;

public class NewCaseOptionsPage extends CommonFunctions {

    @FindBy(xpath = "//div[@class='forceChangeRecordType']")
    private WebElement form_caseOptionsPage;

    @FindBy(xpath = "//div[@class='forceChangeRecordType']//span[contains(@class,'topdown-radio--label')]")
    private List<WebElement> list_caseOptions;

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
            myClass = Class.forName("base.functions" + "." + "CommonFunctions");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean isFormCaseOptionsPageDisplayed() throws Exception {
        return waitForElementVisibility(form_caseOptionsPage, mediumWait());
    }

    public void selectCaseOption(String caseOption) throws Exception {
        waitForElementListVisible(list_caseOptions, mediumWait());
        if (!caseOption.equalsIgnoreCase("random")) {
            for (WebElement el : list_caseOptions) {
                if (getWebElementText(el).equalsIgnoreCase(caseOption)) {
                    clickAndMoveToElementClickable(el, mediumWait());
                    break;
                }
            }
        } else {
            clickAndMoveToElementClickable(getRandomWebElementFromList(list_caseOptions, mediumWait()), mediumWait());
        }
        clickAndMoveToElementClickable(button_next, mediumWait());
        }
}