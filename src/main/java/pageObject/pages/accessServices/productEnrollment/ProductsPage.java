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
import utils.Values;

import java.util.List;

public class ProductsPage extends CommonFunctions {
    private Logger logger = Logger.getLogger(CommonFunctions.class);

    @FindBy(xpath = "//span[@title='Product Name'] | //span[@title='Product Enrollment Number'] | //span[text()='Name']")
    private WebElement togglePmProductNameColumn;

    @FindBy(xpath = "//span[contains(@class,'triggerLinkText')]")
    private WebElement linkButtonPmCurrentView;

    @FindBy(xpath = "//div[text()='List Views'] | //div[text()='Recent List Views']")
    private WebElement labelPmListViews;

    @FindBy(xpath = "//div[contains(text(),'List Views')]/following::li")
    private List<WebElement> toggleListPmViewList;

    @FindBy(xpath = "//input[contains(@placeholder,'Search this')]")
    private WebElement inputPmSearchList;

    @FindBy(xpath = "//*[contains(text(),'No items to display')]")
    private WebElement labelPmNoItemsMessage;

    @FindBy(xpath = "//span[text()='Product Name'][not(@title)]")
    private WebElement labelPmProductNameLabel;

    @FindBy(xpath = "//span[text()='Services Provided']/following::span[1][not (@class='assistiveText')]")
    private WebElement labelListPmServicesProvidedList;

    @FindBy(xpath = "//*[@id='brandBand_1']//tbody/tr[1]")
    private WebElement tableRowPmFirstRow;

    /**
     * It will select the product view specified i.e. "All Products"
     *
     * @param filterView it contains the name of the view
     * @throws Exception
     * @author J.Ruano
     */
    public void selectProductView(String filterView, boolean selectAgain) throws Exception {
        By toogleListSecondOption = By.xpath("//li//a//*[text()='" + filterView + "']");
        do {
            waitForElementClickable(togglePmProductNameColumn, shortWait());
        } while (!waitForElementClickable(togglePmProductNameColumn, shortWait()));
        try {
            if (getWebElementText(linkButtonPmCurrentView).trim().equalsIgnoreCase(filterView) && selectAgain) {
                logger.info("Already At: " + filterView);
            } else {
                clickAndMoveToElementClickable(linkButtonPmCurrentView, shortWait());
                waitForElementVisibility(labelPmListViews, shortWait());
                WebElement filterElement = clickAndMoveToElementClickableFromListByText(toggleListPmViewList, filterView);
                if (filterElement == null) {
                    clickMethodsWebElement(getWebElement(toogleListSecondOption));
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
        clickAndMoveToElementClickable(inputPmSearchList, shortWait());
        sendKeysAndMoveToElementClickable(inputPmSearchList, productName, shortWait());
        inputPmSearchList.sendKeys(Keys.ENTER);
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
        By labelListPmServicesProvidedList = By.xpath("//tr//a[@title='" + productName.toUpperCase() + "']|//tr//a[@title='" + productName + "']");
        try {
            waitForElementTextPresent(tableRowPmFirstRow, productName, mediumWait());
            List<WebElement> productNamesFound = getWebElementList(labelListPmServicesProvidedList);
            for (int i = 0; i < productNamesFound.size(); i++) {
                if (getWebElementAttribute(productNamesFound.get(i), "title").equalsIgnoreCase(productName)) {
                    clickMethodsWebElement(productNamesFound.get(i));
                    while (!waitForElementInvisibilityOfElementLocatedBy(labelListPmServicesProvidedList, shortWait())) {
                        productNamesFound = getWebElementList(labelListPmServicesProvidedList);
                        clickMethodsWebElement(productNamesFound.get(i));
                    }
                    logger.info("The Product Element was found");
                    statusOperation = true;
                    break;
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
        waitForElementVisibility(labelPmProductNameLabel, longWait());
        scrollToElement(labelListPmServicesProvidedList);
        return servicesProvidedList = splitRegex(getWebElementText(labelListPmServicesProvidedList), Values.REGEX_SEMICOLON);
    }
}