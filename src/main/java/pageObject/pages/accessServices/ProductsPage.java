package pageObject.pages.accessServices;

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
    int shortTimeOutInSeconds = 10;
    @FindBy(xpath = "//span[@title='Product Name'] | //span[@title='Product Enrollment Number']")
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
     * It will select the product view especified i.e. "All Products"
     *
     * @param productView it contains the name of the view
     * @throws Exception
     * @author J.Ruano
     */
    public void selectProductView(String productView) throws Exception {
        do {
            waitForElementClickable(toggle_pmProductNameColumn, shortTimeOutInSeconds);
        } while (!waitForElementClickable(toggle_pmProductNameColumn, shortTimeOutInSeconds));
        try {
            if (getWebElementText(linkButton_pmCurrentView).trim().equalsIgnoreCase(productView)) {
                logger.info("Already At: " + productView);
            } else {
                clickAndMoveToElementClickable(linkButton_pmCurrentView, shortTimeOutInSeconds);
                waitForElementVisibility(label_pmListViews, shortTimeOutInSeconds);
                clickAndMoveToElementClickableFromListByText(toggleList_pmViewList, productView);
                logger.info("It Worked: " + productView);
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
        clickAndMoveToElementClickable(input_pmSearchList, shortTimeOutInSeconds);
        sendKeysAndMoveToElementClickable(input_pmSearchList, productName, shortTimeOutInSeconds);
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
            waitForElementTextPresent(tableRow_pmFirstRow, productName, 10);
            List<WebElement> productNamesFound = getWebElementList(labelList_pmServicesProvidedList);
            if (!productNamesFound.isEmpty()) {
                for (WebElement product : productNamesFound) {
                    if (product.getAttribute("title").trim().equalsIgnoreCase(productName.trim())) {
                        waitForElementVisibility(product, shortTimeOutInSeconds);
                        clickAndMoveToElementClickable(product, shortTimeOutInSeconds);
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
        waitForElementVisibility(label_pmProductNameLabel, shortTimeOutInSeconds);
        scrollMethodToWebElement(labelList_pmServicesProvidedList);
        return servicesProvidedList = splitRegex(getWebElementText(labelList_pmServicesProvidedList), "[;]");
    }
}