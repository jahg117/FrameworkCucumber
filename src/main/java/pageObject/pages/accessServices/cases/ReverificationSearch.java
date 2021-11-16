package pageObject.pages.accessServices.cases;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Values;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class ReverificationSearch extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "//*[@id='banner']")
    private WebElement bannerReverificationSearch;

    @FindBy(xpath = "//label[normalize-space(text())='Case Address']/following::select")
    private List<WebElement> caseRVFilters;

    @FindBy(xpath = "//img[contains(@src,'right')]")
    private List<WebElement> caseRVArrowFilters;

    @FindBy(xpath = "//*[@value='Search']")
    private WebElement buttonSearch;

    @FindBy(xpath = "//th/div[normalize-space(text())='A360 Patient ID']/following::input[@type='checkbox'][not(@disabled='disabled')]")
    private List<WebElement> checkboxListRV;

    @FindBy(xpath = "//input[@value='AssociateToCase']")
    private WebElement buttonAssociateToCase;

    @FindBy(xpath = "//iframe[@class='cACS_ReverificationFilter']")
    private WebElement iframeReverificationScreen;

    @FindBy(xpath = "//th/div[normalize-space(text())='A360 Patient ID']/following::td/a")
    private List<WebElement> patientListRV;

    @FindBy(xpath = "//span[normalize-space(text())='Processing...']")
    private WebElement imgProcessing;

    public boolean isReverificationScreenDisplay() throws Exception {
        return switchToFrameByWebElementIndexOrName(iframeReverificationScreen, mediumWait());
    }

    public void searchFiltersRV(String filterSelection) throws Exception {
        executeRVSearch(filterSelection);
    }

    public void executeRVSearch(String filterSelection) throws Exception {
        List<String> filterList = splitRegex(filterSelection, Values.REGEX_COMMA);
        int counterForFilter = 0;
        int idxCounter = 0;
        try {
            for (String filter : filterList) {
                counterForFilter++;
                switch (filter.toUpperCase().trim()) {
                    case Values.TXT_RANDOM:
                        clickAndMoveMethodsWebElement(getRandomWebElementFromList(By.xpath("//label[normalize-space(text())='Case Address']/following::select[" + counterForFilter + "]//option"), shortWait()));
                        clickMethod(caseRVArrowFilters.get(idxCounter));
                        break;

                    case Values.TXT_NOTAPPLY:
                        logger.info(Values.TXT_RVFILTERNOTREQUIRED);
                        break;

                    default:
                        clickAndMoveMethodsWebElement(getWebElement(By.xpath("//label[normalize-space(text())='Case Address']/following::select[" + counterForFilter + "]//option[" + Integer.parseInt(filter) + "]")));
                        clickMethod(caseRVArrowFilters.get(idxCounter));
                        break;
                }
                counterForFilter++;
                idxCounter++;
            }
            clickMethod(buttonSearch);
            waitForElementNotVisible(imgProcessing, shortWait());
        } catch (Exception e) {

        }
    }

    public List<String> associatePatientsToRVCase(String ammountOfPatients) throws Exception {
        List<String> patientListSelected = new ArrayList<>();
        WebElement patienSelected = null;
        if (checkboxListRV.isEmpty()) {
            logger.info(Values.TXT_RVNOPATIENTFOUND);
        } else {
            int intAmmountOfPatients = 0;
            try {
                intAmmountOfPatients = Integer.parseInt(ammountOfPatients);
            } catch (Exception e) {
                intAmmountOfPatients = 0;
            }
            scrollBottom();
            for (int i = 0; i < checkboxListRV.size(); i++) {
                if (i < intAmmountOfPatients) {
                    waitForElementClickable(checkboxListRV.get(i), shortWait());
                    clickMethod(checkboxListRV.get(i));
                    if (imgProcessing.isDisplayed()) {
                        waitForElementNotVisible(imgProcessing, shortWait());
                    }
                    patientListSelected.add(patientListRV.get(i).getText());
                } else {
                    break;
                }
            }
        }
        clickMethod(buttonAssociateToCase);
        return patientListSelected;
    }
}