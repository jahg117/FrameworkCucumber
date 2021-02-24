package pageObject.pages.accessServices.Cases;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class NewCasePage extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    int shortTimeOutInSeconds = 10;
    @FindBy(xpath = "//div[@class='forceChangeRecordType']")
    private WebElement form_newCase;

    @FindBy(xpath = "//label[contains(@class,'radio topdown')]//span[contains(@class,'label')]")
    private List<WebElement> list_labelCases;

    @FindBy(xpath = "//label[contains(@class,'radio topdown')]//span[contains(@class,'radio--faux')]")
    private List<WebElement> list_radioCases;

    @FindBy(xpath = "//button//span[contains(text(),'Next')]")
    private WebElement button_next;

    @FindBy(xpath = "//input[@name='selectRecType']")
    private WebElement dropdown_recordType;

    @FindBy(xpath = "//*[@role='option']//span[@title]")
    private List<WebElement> list_recordTypeList;


    public boolean isNewCaseFormDisplayed() {
        return waitForElementVisibility(form_newCase, 20);
    }

    public void selectCaseOption(String caseOption) throws Exception {
        int i = 0;
        for (WebElement el : list_labelCases) {
            if (getWebElementText(el).equalsIgnoreCase(caseOption)) {
                clickAndMoveToElementClickable(list_radioCases.get(i), 10);
                break;
            }
            i++;
        }
    }

    public void clickNextButton() throws Exception {
        clickAndMoveToElementClickable(button_next, 10);
    }

    /**
     * It compares the list of services provided of the product (drug) with the current available list when a new case is
     * created with that product (drug)
     *
     * @param serviceProvidedList contains the services provided by the product (drug)
     * @return a boolean value if Services Provided matches
     * @throws Exception
     * @author J.Ruano
     */
    public boolean validateServicesProvidedAvailable(List<String> serviceProvidedList) throws Exception {
        ArrayList<String> listOfLists = new ArrayList<String>();
        List<String> list_ServicesProvidedMatched = new ArrayList<String>();
        List<String> list_ServicesProvidedNotMatched = new ArrayList<String>();
        int counter = 0;
        boolean serviceFound = false;
        boolean statusOperation = false;
        clickAndMoveToElementClickable(dropdown_recordType, shortTimeOutInSeconds);
        for (WebElement recordType : list_recordTypeList) {
            serviceFound = false;
            while (counter <= (serviceProvidedList.size() - 1)) {
                if (recordType.getAttribute("title").trim().equalsIgnoreCase(serviceProvidedList.get(counter))) {
                    list_ServicesProvidedMatched.add(recordType.getAttribute("title").trim());
                    serviceFound = true;
                    break;
                }
                counter++;
            }
            counter = 0;
            if (!serviceFound) {
                list_ServicesProvidedNotMatched.add(recordType.getAttribute("title").trim());
            }
        }
        listOfLists.addAll(list_ServicesProvidedMatched);
        listOfLists.addAll(list_ServicesProvidedNotMatched);
        if (list_ServicesProvidedMatched.size() == list_recordTypeList.size()) {
            statusOperation = true;
            logger.info("============ALL THE PRODUCT SERVICES MATCHED============");
            for (String product : list_ServicesProvidedMatched) {
                logger.info("= Service Product: " + product);
                logger.info("========================================================");
            }
        } else {
            logger.error("============NOT ALL THE PRODUCT SERVICES MATCHED============");
            for (String product : list_ServicesProvidedNotMatched) {
                logger.info("= Service Product: " + product);
                logger.info("============================================================");
            }
        }
        return statusOperation;
    }
}
