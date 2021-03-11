package pageObject.pages.accessServices;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountsRecentlyViewedPage extends CommonFunctions {

    @FindBy(xpath = "//iframe[@title='accessibility title']")
    private List<WebElement> iframe_pageInformation;

    @FindBy(xpath = "//h1//*[normalize-space(text())='Recently Viewed']")
    private WebElement label_accountsRecentlyViewed;

    @FindBy(xpath = "//*[@role='gridcell'][3]//*[@title]")
    private List<WebElement> gridCell_gridCellPAF;

    /**
     * Method used to validate the value of the Valid PAF column after creating the consent
     *
     * @param validPAF contains the value that shall have the Valid PAF column after creating the consent i.e. AstraZeneca
     * @return a boolean value if the PAF value matches
     * @throws Exception
     */
    public boolean validateValidPAFValue(String validPAF) throws Exception {
        boolean result = false;


        if (!waitForElementVisibility(label_accountsRecentlyViewed, mediumWait())) {
            By locator = By.xpath("//h1//*[normalize-space(text())='Recently Viewed']");
            retryingFindElementByLocator(locator,2);
        }
        if (gridCell_gridCellPAF.get(0).getAttribute("title").isEmpty()) {
            result = false;
        } else if (gridCell_gridCellPAF.get(0).getAttribute("title").equalsIgnoreCase(validPAF)) {
            result = true;
        }
        return result;
    }
}