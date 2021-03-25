package pageObject.pages.accessServices.general;

import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class GlobalCommonGeneralStepsPage extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "//span[@title='Product Name'] | //span[@title='Product Enrollment Number'] | //span[text()='Name']")
    private WebElement toggle_pmProductNameColumn;

    @FindBy(xpath = "//span[contains(@class,'triggerLinkText')]")
    private WebElement linkButton_pmCurrentView;

    @FindBy(xpath = "//div[text()='List Views']")
    private WebElement label_pmListViews;

    @FindBy(xpath = "//div[contains(text(),'List Views')]/following::li")
    private List<WebElement> toggleList_pmViewList;

    @FindBy(xpath = "//input[contains(@placeholder,'Search this')]")
    private WebElement input_pmSearchList;

    @FindBy(xpath = "//*[contains(text(),'No items to display')]")
    private WebElement label_pmNoItemsMessage;

    @FindBy(xpath = "//span[text()='Product Name'][not(@title)]")
    private WebElement label_pmProductNameLabel;

    @FindBy(xpath = "//span[text()='Services Provided']/following::span[1][not (@class='assistiveText')]")
    private WebElement labelList_pmServicesProvidedList;

    @FindBy(xpath = "//*[@id='brandBand_1']//tbody/tr[1]")
    private WebElement tableRow_pmFirstRow;

    /**
     * It will select the product view specified i.e. "All Products"
     *
     * @param filterView it contains the name of the view
     * @throws Exception
     * @author J.Ruano
     */
    public void selectFilterView(String filterView) throws Exception {
        do {
            waitForElementClickable(toggle_pmProductNameColumn, mediumWait());
        } while (!waitForElementClickable(toggle_pmProductNameColumn, mediumWait()));
        try {
            if (getWebElementText(linkButton_pmCurrentView).trim().equalsIgnoreCase(filterView)) {
                logger.info("Already At: " + filterView);
            } else {
                clickAndMoveToElementClickable(linkButton_pmCurrentView, mediumWait());
                waitForElementVisibility(label_pmListViews, mediumWait());
                clickAndMoveToElementClickableFromListByText(toggleList_pmViewList, filterView);
                logger.info("It Worked: " + filterView);
            }
        } catch (Exception e) {
            logger.error("The WebElement was not found");
            new NoSuchElementException("The WebElement was not found");
        }
    }
}
