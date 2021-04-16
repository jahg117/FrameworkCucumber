package pageObject.pages.accessServices.cases;


import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class NewCasePage extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);
    int shortTimeOutInSeconds = 10;

    @FindBy(xpath = "//button[@title='Record Type Selection']")
    private WebElement button_continue;

    @FindBy(xpath = "//input[@name='selectRecType']")
    private WebElement dropdown_recordType;

    @FindBy(xpath = "//*[@role='option']//span[@title]")
    private List<WebElement> list_recordTypeList;

    @FindBy(xpath = "(//div[contains(@class,'forceDetailPanelDesktop')])[last()]//*[contains(text(),'New Case')]")
    private WebElement form_caseOptions;


    public boolean isNewCaseFormDisplayed() throws Exception {
        waitForPageToLoad();
        return waitForElementVisibility(dropdown_recordType, 20);
    }

    public void selectCaseOption(String caseOption) throws Exception {
        clickElementClickable(dropdown_recordType, 10);
        if(caseOption.equalsIgnoreCase("random")){
            clickAndMoveToElementClickable(getRandomWebElementFromList(list_recordTypeList, 10),10);
        }else{
            for (WebElement el : list_recordTypeList) {
                if (getWebElementAttribute(el,"title").equalsIgnoreCase(caseOption)) {
                    clickAndMoveToElementClickable(el, 10);
                    break;
                }
            }
        }
    }

    public void clickContinueButton() throws Exception {
        waitForPageToLoad();
        waitForElementClickable(dropdown_recordType, 10);
        waitForElementClickable(button_continue, 10);
        clickAndMoveToElementClickable(button_continue, 10);
        if(!waitForElementVisibility(form_caseOptions, 10)){
            clickAndMoveToElementClickable(button_continue, 10);
        }
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
