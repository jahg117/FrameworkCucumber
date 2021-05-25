package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.FileReading;
import utils.Values;

import java.lang.reflect.Method;

public class NewPatientInsuranceNoPI extends CommonFunctions {
    @FindBy(xpath = "//*[@name='SaveEdit']")
    private WebElement button_save;

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

    /**
     * It will create a No Patient Insurance only saving since it is no necessary to put any data
     *
     * @return
     * @throws Exception
     */
    public void clickOnSaveNoPI() throws Exception {
        clickAndMoveToElementClickable(button_save, mediumWait());
    }
}