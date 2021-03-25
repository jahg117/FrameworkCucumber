package pageObject.pages.accessServices.productEnrollment;

import base.driverInitialize.DriverFactory;
import base.functions.CommonFunctions;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductsPage extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "//span[@title='Product Name'] | //span[@title='Product Enrollment Number'] | //span[text()='Name']")
    private WebElement toggle_pmProductNameColumn;

    @FindBy(xpath = "//span[contains(@class,'triggerLinkText')]")
    private WebElement linkButton_pmCurrentView;

    @FindBy(xpath = "//div[text()='List Views'] | //div[text()='Recent List Views']")
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
    public void selectProductView(String filterView) throws Exception {
        By toogleListSecondOption = By.xpath("//li//a//*[text()='" + filterView + "']");
        do {
            waitForElementClickable(toggle_pmProductNameColumn, shortWait());
        } while (!waitForElementClickable(toggle_pmProductNameColumn, shortWait()));
        try {
            if (getWebElementText(linkButton_pmCurrentView).trim().equalsIgnoreCase(filterView)) {
                logger.info("Already At: " + filterView);
            } else {
                clickAndMoveToElementClickable(linkButton_pmCurrentView, shortWait());
                waitForElementVisibility(label_pmListViews, shortWait());
                WebElement filterElement = clickAndMoveToElementClickableFromListByText(toggleList_pmViewList, filterView);
                if (filterElement == null) {
                    clickElementJS(getWebElement(toogleListSecondOption));
                }
                logger.info("It Worked: " + filterView);
            }
        } catch (Exception e) {
            logger.error("The WebElement was not found");
            new NoSuchElementException("The WebElement was not found");
        }
    }

    /**
     * It perform a search of the product or product enrollment specified so create a search
     *
     * @param productName name what will be search
     * @throws Exception
     * @author J.Ruano
     */
    public void searchProductOrPE(String productName) throws Exception {
        Actions actions = new Actions(DriverFactory.getDriver());
        clickAndMoveToElementClickable(input_pmSearchList, shortWait());
        sendKeysAndMoveToElementClickable(input_pmSearchList, productName, shortWait());
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    /**
     * It search and clicked the product from the search results
     *
     * @param productName it contains the name of the product i.e. Calquence
     * @return a boolean value if the whole operation is successfully returns true
     * @throws Exception
     * @author J.Ruano
     */
    public boolean searchAndClickProductFromResults(String productName) throws Exception {
        boolean statusOperation = false;
        By labelList_pmServicesProvidedList = By.xpath("//tr//a[@title='" + productName + "']");
        try {
            waitForElementTextPresent(tableRow_pmFirstRow, productName, mediumWait());
            List<WebElement> productNamesFound = getWebElementList(labelList_pmServicesProvidedList);
            if (!productNamesFound.isEmpty()) {
                for (WebElement product : productNamesFound) {
                    if (product.getAttribute("title").trim().equalsIgnoreCase(productName.trim())) {
                        waitForElementVisibility(product, shortWait());
                        clickAndMoveToElementClickable(product, shortWait());
                        logger.info("The Product Element was found");
                        statusOperation = true;
                        break;
                    }
                }
            } else {
                if (label_pmNoItemsMessage.isDisplayed()) {
                    logger.error("No items to display");
                }
            }
        } catch (Exception e) {
            logger.error("The Product WebElement was not found");
            new NoSuchElementException("The WebElement was not found");
        }
        return statusOperation;
    }

    /**
     * It is used to get the Service Provided List from the product selected
     *
     * @return a list of strings with all the services provided by the product
     * @throws Exception
     * @author J.Ruano
     */
    public List<String> getServicesProvidedList() throws Exception {
        List<String> servicesProvidedList = null;
        waitForElementVisibility(label_pmProductNameLabel, shortWait());
        scrollMethodToWebElement(labelList_pmServicesProvidedList);
        return servicesProvidedList = splitRegex(getWebElementText(labelList_pmServicesProvidedList), "[;]");
    }
}