package pageObject.pages.accessServices.account;

import base.functions.CommonFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountsRecentlyViewedPage extends CommonFunctions {

    @FindBy(xpath = "//h1//*[normalize-space(text())='Recently Viewed']")
    private WebElement labelAccountsRecentlyViewed;

    @FindBy(xpath = "//*[@role='gridcell'][3]//*[@title]")
    private List<WebElement> gridCellPAF;

    /**
     * Method used to validate the value of the Valid PAF column after creating the consent
     *
     * @param validPAF contains the value that shall have the Valid PAF column after creating the consent i.e. AstraZeneca
     * @return a boolean value if the PAF value matches
     * @throws Exception related to selenium
     */
    public boolean validateValidPAFValue(String validPAF) throws Exception {
        boolean statusOperation = false;
        if (!waitForElementVisibility(labelAccountsRecentlyViewed, mediumWait())) {
            By locator = By.xpath("//h1//*[normalize-space(text())='Recently Viewed']");
            retryingFindElementByLocator(locator, 2);
        }
        if (gridCellPAF.get(0).getAttribute("title").trim().equalsIgnoreCase(validPAF.trim())) {
            statusOperation = true;
        }
        return statusOperation;
    }
}